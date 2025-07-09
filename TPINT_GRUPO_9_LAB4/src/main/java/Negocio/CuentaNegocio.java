package Negocio;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import DAO.CuentaDAO;
import Modelo.Cuenta;

public class CuentaNegocio {
    private CuentaDAO cuentaDAO;
    private MovimientoNegocio movimientoNegocio;

    public CuentaNegocio() throws SQLException {
        this.cuentaDAO = new CuentaDAO();
        this.movimientoNegocio = new MovimientoNegocio();
    }

    public boolean agregarCuenta(Cuenta cu) throws SQLException {
        int idCliente = cu.getCliente().getIdCliente();
        int idTipoCuenta = cu.getTipoCuenta().getIdTipoCuenta();
        double saldo = cu.getSaldo();
        Timestamp fechaCreacion = Timestamp.valueOf(java.time.LocalDateTime.now());
        return cuentaDAO.agregarCuenta(idCliente, idTipoCuenta, saldo, fechaCreacion);
    }

    public boolean borrarCuentaPorNroCuenta(String nroCuenta) throws SQLException {
        if(cuentaDAO.borrarCuentaPorNroCuenta(nroCuenta))
            return true;
        return false;
    }

    // ========== MÉTODOS DE BÚSQUEDA - AMBAS VERSIONES ==========
    
    // Versión original (devuelve lista con String)
    public List<Cuenta> buscarCuentasPorId(String id) throws SQLException {
        return cuentaDAO.buscarCuentasPorId(id);
    }
    
    // Versión nueva (devuelve una cuenta con int)
    public Cuenta buscarCuentaPorId(int idCuenta) throws SQLException {
        return cuentaDAO.buscarCuentaPorId(idCuenta);
    }

    // ========== MÉTODOS DE LISTADO - AMBAS VERSIONES ==========
    
    // Versión original (para paginación)
    public List<Cuenta> listarRegistros(String nroCuenta, int pagina, int tamañoPagina) throws SQLException {
        int offset = (pagina - 1) * tamañoPagina;
        return cuentaDAO.listarRegistros(nroCuenta, offset, tamañoPagina);
    }
    
    // Versión nueva (por cliente)
    public List<Cuenta> listarCuentasPorCliente(int idCliente) throws SQLException {
        return cuentaDAO.listarCuentasPorCliente(idCliente);
    }

    // ========== MÉTODOS DE CONTEO ==========
    
    public int contarRegistrosActivos(String nroCuenta) throws SQLException {
        return cuentaDAO.contarRegistrosActivos(nroCuenta);
    }

    // ========== MÉTODOS DE OBTENCIÓN ==========
    
    public Cuenta obtenerCuentaPorNroCuenta(String nroCuenta) throws SQLException {
        return cuentaDAO.obtenerCuentaPorNroCuenta(nroCuenta);
    }

    // ========== MÉTODOS DE MODIFICACIÓN ==========
    
    public boolean modificarCuenta(Cuenta cu) throws SQLException {
        return cuentaDAO.modificarCuenta(cu);
    }

    // ========== MÉTODOS DE TRANSFERENCIA - AMBAS VERSIONES ==========
    
    // Versión original (método simple)
    public boolean TransferirDinero(Cuenta cuentaOrigen, String cbuCuentaDestino, double monto) throws SQLException {
        Cuenta cuentaDestino = cuentaDAO.obtenerCuentaPorCbu(cbuCuentaDestino);
        if (cuentaDAO.actualizarSaldo(cuentaOrigen.getIdCuenta(), -monto)) {
            if(cuentaDAO.actualizarSaldo(cuentaDestino.getIdCuenta(), monto)) {
                return true;
            }
            else
                cuentaDAO.actualizarSaldo(cuentaOrigen.getIdCuenta(), monto);
        }
        return false;
    }
    
    // Versión nueva (con validaciones completas y movimientos)
    public boolean transferirDinero(int idCuentaOrigen, int idCliente, String cbuCuentaDestino, double monto, String detalle) throws SQLException {
        // Validar monto
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a 0.");
        }

        // Obtener cuenta origen
        Cuenta cuentaOrigen = cuentaDAO.buscarCuentaPorId(idCuentaOrigen);
        if (cuentaOrigen == null || !cuentaOrigen.isActivo() || cuentaOrigen.getCliente().getIdCliente() != idCliente) {
            throw new IllegalArgumentException("La cuenta de origen no es válida o no pertenece al cliente.");
        }

        // Validar saldo suficiente
        if (cuentaOrigen.getSaldo() < monto) {
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta de origen.");
        }

        // Obtener cuenta destino por CBU
        Cuenta cuentaDestino = cuentaDAO.obtenerCuentaPorCbu(cbuCuentaDestino);
        if (cuentaDestino == null || !cuentaDestino.isActivo()) {
            throw new IllegalArgumentException("La cuenta destino no existe o no está activa.");
        }

        // Validar que no sea la misma cuenta
        if (cuentaOrigen.getIdCuenta() == cuentaDestino.getIdCuenta()) {
            throw new IllegalArgumentException("La cuenta de origen y destino no pueden ser la misma.");
        }

        // Registrar la transferencia con movimientos
        String numeroReferencia = java.util.UUID.randomUUID().toString();
        return movimientoNegocio.registrarTransferencia(
            cuentaOrigen.getIdCuenta(),
            cuentaDestino.getIdCuenta(),
            monto,
            detalle,
            numeroReferencia
        );
    }
}
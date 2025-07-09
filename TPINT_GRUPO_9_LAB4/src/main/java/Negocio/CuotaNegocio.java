package Negocio;

import DAO.CuotaDAO;
import DAO.MovimientoDAO;
import DAO.Conexion;
import DAO.CuentaDAO;
import Modelo.Cuota;
import Modelo.Cuenta;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CuotaNegocio {
    private CuotaDAO cuotaDAO;
    private MovimientoDAO movimientoDAO;
    private CuentaDAO cuentaDAO;

    public CuotaNegocio() throws SQLException {
        this.cuotaDAO = new CuotaDAO();
        this.movimientoDAO = new MovimientoDAO();
        this.cuentaDAO = new CuentaDAO();
    }

    public Cuota obtenerProximaCuotaNoPagada(int idPrestamo) throws SQLException {
        return cuotaDAO.obtenerProximaCuotaNoPagada(idPrestamo);
    }

    public int obtenerTotalCuotas(int idPrestamo) throws SQLException {
        return cuotaDAO.obtenerTotalCuotas(idPrestamo);
    }

    public boolean pagarCuota(int idCuota, int idCuenta, int idPrestamo) throws SQLException {
        Connection conn = null;
        try {
            conn = Conexion.obtenerConexion();
            conn.setAutoCommit(false);

            // Obtener cuota
            Cuota cuota = cuotaDAO.obtenerProximaCuotaNoPagada(idPrestamo);
            if (cuota == null || cuota.getIdCuota() != idCuota) {
                throw new SQLException("Cuota no encontrada o ya pagada.");
            }

            // Obtener cuenta
            Cuenta cuenta = cuentaDAO.buscarCuentaPorId(idCuenta);
            if (cuenta == null) {
                throw new SQLException("Cuenta no encontrada.");
            }

            // Validar saldo
            if (cuenta.getSaldo() < cuota.getMontoCuota()) {
                throw new SQLException("Saldo insuficiente en la cuenta seleccionada.");
            }

            // Registrar movimiento
            double saldoAnterior = cuenta.getSaldo();
            double saldoPosterior = saldoAnterior - cuota.getMontoCuota();
            boolean exitoMovimiento = movimientoDAO.registrarMovimiento(
                idCuenta,
                10, // Pago de Cuota
                cuota.getMontoCuota(),
                "Pago de cuota " + cuota.getNumeroCuota() + " de préstamo " + idPrestamo,
                saldoAnterior,
                saldoPosterior,
                null,
                "CUOTA" + idCuota
            );

            // Actualizar saldo de la cuenta
            boolean exitoSaldo = cuentaDAO.actualizarSaldo(idCuenta, -cuota.getMontoCuota());

            // Registrar pago de cuota
            boolean exitoCuota = cuotaDAO.registrarPagoCuota(idCuota, idCuenta, LocalDateTime.now());

            if (exitoMovimiento && exitoSaldo && exitoCuota) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}
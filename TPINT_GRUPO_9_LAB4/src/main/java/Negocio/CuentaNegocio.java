package Negocio;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import DAO.CuentaDAO;
import Modelo.Cuenta;

public class CuentaNegocio {
	private CuentaDAO cuentaDAO;
	
	public CuentaNegocio() throws SQLException {
        this.cuentaDAO = new CuentaDAO();  
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
	
	public List<Cuenta> buscarCuentasPorId(String id) throws SQLException {
		return cuentaDAO.buscarCuentasPorId(id);
	}
	
	public List<Cuenta> listarRegistros(String nroCuenta, int pagina, int tamañoPagina) throws SQLException {
	    int offset = (pagina - 1) * tamañoPagina;
	    return cuentaDAO.listarRegistros(nroCuenta, offset, tamañoPagina);
	}
	
	public int contarRegistrosActivos(String nroCuenta) throws SQLException {
	    return cuentaDAO.contarRegistrosActivos(nroCuenta);
	}
	
	public Cuenta obtenerCuentaPorNroCuenta(String nroCuenta) throws SQLException {
        return cuentaDAO.obtenerCuentaPorNroCuenta(nroCuenta);
    }
	
	public boolean modificarCuenta(Cuenta cu) throws SQLException {
        return cuentaDAO.modificarCuenta(cu);
    }
	
	public boolean TransferirDinero(Cuenta cuentaOrigen, String cbuCuentaDestino, double monto) throws SQLException {
		Cuenta cuentaDestino = cuentaDAO.obtenerCuentaPorCbu(cbuCuentaDestino);
		if (cuentaDAO.descontarSaldo(cuentaOrigen.getIdCuenta(), monto)) {
			if(cuentaDAO.actualizarSaldo(cuentaDestino.getIdCuenta(), monto)) {
				return true;
			}
			cuentaDAO.actualizarSaldo(cuentaOrigen.getIdCuenta(), monto);
		}
		return false;
	}

}

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
	
	public List<Cuenta> listarRegistros(String dni, int pagina, int tamañoPagina) throws SQLException {
	    int offset = (pagina - 1) * tamañoPagina;
	    return cuentaDAO.listarRegistros(dni, offset, tamañoPagina);
	}
	
	public int contarRegistrosActivos(String dni) throws SQLException {
	    return cuentaDAO.contarRegistrosActivos(dni);
	}

}

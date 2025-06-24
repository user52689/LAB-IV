package Negocio;

import java.sql.SQLException;
import java.util.List;

import DAO.CuentaDAO;
import Modelo.Cuenta;

public class CuentaNegocio {
	private CuentaDAO cuentaDAO;
	
	public CuentaNegocio() throws SQLException {
        this.cuentaDAO = new CuentaDAO();  
    }
	
	public List<Cuenta> listarCuentas() throws SQLException {
        return cuentaDAO.listarCuentas();
    }
	
	public List<Cuenta> listarCuentasPaginadas(int pagina, int tamañoPagina) throws SQLException {
	    int offset = (pagina - 1) * tamañoPagina;
	    return cuentaDAO.listarCuentasPaginado(offset, tamañoPagina);
	}
	
	public int obtenerCantidadTotalCuentas() throws SQLException {
	    return cuentaDAO.contarCuentasActivas();
	}

}

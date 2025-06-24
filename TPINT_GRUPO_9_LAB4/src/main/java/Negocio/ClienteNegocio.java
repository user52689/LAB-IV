package Negocio;

import DAO.ClienteDAO;
import Modelo.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteNegocio {

    private ClienteDAO clienteDAO;

    public ClienteNegocio() throws SQLException {
        this.clienteDAO = new ClienteDAO();  
    }

    public boolean agregarCliente(Cliente c) throws SQLException {
        if (c.getDni() == null || c.getDni().isEmpty()) {
            throw new IllegalArgumentException("DNI no puede estar vacío");
        }
        c.setFechaAlta(java.time.LocalDateTime.now());
        c.setActivo(true);

        return clienteDAO.agregarCliente(c);
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.listarClientes();
    }

    public boolean modificarCliente(Cliente c) throws SQLException {
    	return clienteDAO.modificarCliente(c);
    }

    public Cliente buscarClientePorDniExacto(String dni) throws SQLException {
        return clienteDAO.obtenerClientePorDni(dni);
    }

    public boolean borrarClientePorDni(String dni) throws SQLException {
        return clienteDAO.borrarClientePorDni(dni);
    }

	public void resetearContrasena(int idCliente, String string) {
		// TODO Auto-generated method stub
		
	}
	
	// Paginacion
	
	public List<Cliente> listarRegistros(String dni, int pagina, int tamañoPagina) throws SQLException {
	    int offset = (pagina - 1) * tamañoPagina;
	    return clienteDAO.listarRegistros(dni, offset, tamañoPagina);
	}
	
	public int contarRegistrosActivos(String dni) throws SQLException {
	    return clienteDAO.contarRegistrosActivos(dni);
	}
}

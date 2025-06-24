package Servlet;

import Modelo.Cliente;

import Negocio.ClienteNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cliente/listar")
public class ClienteListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteNegocio clienteNegocio;

    @Override
    public void init() throws ServletException {
        try {
            clienteNegocio = new ClienteNegocio();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	if (req.getParameter("mostrarTodos") != null) {
    	    res.sendRedirect(req.getContextPath() + "/cliente/listar");
    	    return;
    	}
    	
    	String dni = req.getParameter("dni");
        if(dni == null) {
        	dni = "";
        }
        
        List<Cliente> clientes = new ArrayList<>();
        int pagina = 1;
	    int tamañoPagina = 10;
	    int totalPaginas = 1;
	    
	    String paginaParam = req.getParameter("pagina");
	    if (paginaParam != null) {
	    	pagina = Integer.parseInt(paginaParam);
	    }
        
        try {
        	clientes = clienteNegocio.listarRegistros(dni, pagina, tamañoPagina);
			int totalCuentas = clienteNegocio.contarRegistrosActivos(dni);
	        totalPaginas = (int) Math.ceil((double) totalCuentas / tamañoPagina);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        req.setAttribute("listaClientes", clientes);
        req.setAttribute("paginaActual", pagina);
	    req.setAttribute("totalPaginas", totalPaginas);
    	req.setAttribute("dni", dni);
        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ListadoCliente.jsp").forward(req, res);
    }
}

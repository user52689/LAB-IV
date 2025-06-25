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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dni = req.getParameter("dni");
        String mostrarTodos = req.getParameter("mostrarTodos");
        List<Cliente> clientes = null;

        try {
            if ("true".equals(mostrarTodos)) {
                clientes = clienteNegocio.listarClientes();
                dni = null;
            } else if (dni != null && !dni.trim().isEmpty()) {
                Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni.trim());
                clientes = new ArrayList<>();
                if (cliente != null) {
                    clientes.add(cliente);
                }
            } else {
                clientes = clienteNegocio.listarClientes(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("dni", dni); 
        req.setAttribute("listaClientes", clientes);
        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ListadoCliente.jsp").forward(req, resp);
    }
}


//Metodo para paginacion en listado


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//    	if (req.getParameter("mostrarTodos") != null) {
//    	    res.sendRedirect(req.getContextPath() + "/cliente/listar");
//    	    return;
//    	}
//    	
//    	String dni = req.getParameter("dni");
//        if(dni == null) {
//        	dni = "";
//        }
//        
//        List<Cliente> clientes = new ArrayList<>();
//        int pagina = 1;
//	    int tamañoPagina = 10;
//	    int totalPaginas = 1;
//	    
//	    String paginaParam = req.getParameter("pagina");
//	    if (paginaParam != null) {
//	    	pagina = Integer.parseInt(paginaParam);
//	    }
//        
//        try {
//        	clientes = clienteNegocio.listarRegistros(dni, pagina, tamañoPagina);
//			int totalCuentas = clienteNegocio.contarRegistrosActivos(dni);
//	        totalPaginas = (int) Math.ceil((double) totalCuentas / tamañoPagina);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//        req.setAttribute("listaClientes", clientes);
//        req.setAttribute("paginaActual", pagina);
//	    req.setAttribute("totalPaginas", totalPaginas);
//    	req.setAttribute("dni", dni);
//        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ListadoCliente.jsp").forward(req, res);
//    }


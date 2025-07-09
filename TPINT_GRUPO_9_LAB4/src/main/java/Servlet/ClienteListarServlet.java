package Servlet;

import Modelo.Cliente;
import Modelo.Provincia;
import Negocio.ClienteNegocio;
import Negocio.ProvinciaNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cliente/listar")
public class ClienteListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteNegocio clienteNegocio;
	private ProvinciaNegocio provinciaNegocio;
	
    @Override
    public void init() throws ServletException {
        try {
            clienteNegocio = new ClienteNegocio();
            provinciaNegocio = new ProvinciaNegocio();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String nombreApellido = req.getParameter("nombreApellido");
    	String dni = req.getParameter("dni");
    	String orden = req.getParameter("orden");
    	String provincia = req.getParameter("provincia");
        if (nombreApellido == null) nombreApellido = "";
        if (dni == null) dni = "";
        if (orden == null) orden = "";
        if (provincia == null) provincia = "";

        int pagina = 1;
        final int registrosPorPagina = 10;

        String paginaParam = req.getParameter("pagina");
        if (paginaParam != null) {
            try {
                pagina = Integer.parseInt(paginaParam);
            } catch (NumberFormatException e) {
                pagina = 1;
            }
        }

        try {
            List<Cliente> clientes = clienteNegocio.listarRegistros(nombreApellido, dni, provincia, orden, pagina, registrosPorPagina);
            List<Provincia> provincias = provinciaNegocio.listarProvincias();
            int totalRegistros = clienteNegocio.contarRegistrosActivos(nombreApellido, dni, provincia);
            int totalPaginas = (int) Math.ceil((double) totalRegistros / registrosPorPagina);

            req.setAttribute("listaClientes", clientes);
            req.setAttribute("listaProvincias", provincias);
            req.setAttribute("paginaActual", pagina);
            req.setAttribute("totalPaginas", totalPaginas);
            req.setAttribute("nombreApellido", nombreApellido);
            req.setAttribute("dni", dni);
            req.setAttribute("provincia", provincia);
            req.setAttribute("orden", orden);;
            
            StringBuilder queryParams = new StringBuilder();

            if (!nombreApellido.isEmpty()) {
                queryParams.append("&nombreApellido=").append(nombreApellido);
            }
            if (!dni.isEmpty()) {
                queryParams.append("&dni=").append(dni);
            }
            if (!orden.isEmpty()) {
                queryParams.append("&orden=").append(orden);
            }
            if (!provincia.isEmpty()) {
                queryParams.append("&provincia=").append(provincia);
            }

            req.setAttribute("queryParams", queryParams.toString());

            req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ListadoCliente.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "No se pudo obtener la lista de clientes.");

            req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ListadoCliente.jsp").forward(req, resp);
        }
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accion = req.getParameter("accion");
    	String nombreApellido = req.getParameter("nombreApellido");
    	String dni = req.getParameter("dni");
    	String orden = req.getParameter("orden");
    	String provincia = req.getParameter("provincia");
	    
	    StringBuilder redirectStr = new StringBuilder("/cliente/listar?pagina=1");

	    if ("buscar".equals(accion)) {
	        if (!nombreApellido.isEmpty()) redirectStr.append("&nombreApellido=").append(nombreApellido);
	        if (!dni.isEmpty()) redirectStr.append("&dni=").append(dni);
	        if (!orden.isEmpty()) redirectStr.append("&orden=").append(orden);
	        if (!provincia.isEmpty()) redirectStr.append("&provincia=").append(provincia);
	    } else if ("todos".equals(accion)) {
	    	dni = "";
	    }

	    res.sendRedirect(req.getContextPath() + redirectStr);
	    return;
    }
}


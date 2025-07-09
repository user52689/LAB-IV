package Servlet;

import Modelo.Usuario;
import Negocio.UsuarioNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/usuario/listar")
public class UsuarioListarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioNegocio usuarioNegocio;

    @Override
    public void init() throws ServletException {
        try {
            usuarioNegocio = new UsuarioNegocio();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreUsuario = req.getParameter("nombreUsuario");
        String dni = req.getParameter("dni");
        String rol = req.getParameter("rol");
        String orden = req.getParameter("orden");
        if (nombreUsuario == null) nombreUsuario = "";
        if (dni == null) dni = "";
        if (rol == null) rol = "";
        if (orden == null) orden = "";
        
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
        	List<Usuario> usuarios = usuarioNegocio.listarRegistros(nombreUsuario, dni, rol, orden, pagina, registrosPorPagina);
            int totalRegistros = usuarioNegocio.contarRegistrosActivos(nombreUsuario, dni, rol);
            int totalPaginas = (int) Math.ceil((double) totalRegistros / registrosPorPagina);

            req.setAttribute("listaUsuarios", usuarios);
            req.setAttribute("nombreUsuario", nombreUsuario);
            req.setAttribute("dni", dni);
            req.setAttribute("rol", rol);
            req.setAttribute("orden", orden);
            req.setAttribute("paginaActual", pagina);
            req.setAttribute("totalPaginas", totalPaginas);
            
            StringBuilder queryParams = new StringBuilder();

            if (!nombreUsuario.isEmpty()) {
                queryParams.append("&nombreUsuario=").append(nombreUsuario);
            }
            if (!dni.isEmpty()) {
                queryParams.append("&dni=").append(dni);
            }
            if (!rol.isEmpty()) {
                queryParams.append("&rol=").append(rol);
            }
            if (!orden.isEmpty()) {
                queryParams.append("&orden=").append(orden);
            }

            req.setAttribute("queryParams", queryParams.toString());

            req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/ListadoUsuario.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace(); 
            req.setAttribute("error", "No se pudo obtener la lista de usuarios.");
            req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/ListadoUsuario.jsp").forward(req, resp);
        }
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accion = req.getParameter("accion");
	    String nombreUsuario = req.getParameter("nombreUsuario");
	    String dni = req.getParameter("dni");
        String rol = req.getParameter("rol");
        String orden = req.getParameter("orden");
	    
	    StringBuilder redirectStr = new StringBuilder("/usuario/listar?pagina=1");

	    if ("buscar".equals(accion)) {
	    	if (!nombreUsuario.isEmpty()) redirectStr.append("&nombreUsuario=").append(nombreUsuario);
	    	if (!dni.isEmpty()) redirectStr.append("&dni=").append(dni);
	    	if (!rol.isEmpty()) redirectStr.append("&rol=").append(rol);
	    	if (!orden.isEmpty()) redirectStr.append("&orden=").append(orden);
	    } else if ("todos".equals(accion)) {
	    	nombreUsuario = "";
	    	dni = "";
	    }

	    resp.sendRedirect(req.getContextPath() + redirectStr);
	    return;
    }

}

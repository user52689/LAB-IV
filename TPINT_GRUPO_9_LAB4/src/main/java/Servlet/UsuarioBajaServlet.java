package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Usuario;
import Negocio.UsuarioNegocio;

@WebServlet("/usuario/baja")
public class UsuarioBajaServlet extends HttpServlet {
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
        String dni = req.getParameter("dni");
        if (dni != null && !dni.trim().isEmpty()) {
            Usuario usuario = null;
            try {
                usuario = usuarioNegocio.buscarUsuarioPorDniExacto(dni.trim());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("usuario", usuario);
        }
        
        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/BajaUsuario.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dni = req.getParameter("dni");
        boolean exito = false;
        try {
            exito = usuarioNegocio.borrarUsuarioPorDni(dni.trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (exito) {
            req.setAttribute("mensajeExito", "Usuario eliminado.");
        } else {
            req.setAttribute("mensajeError", "No se pudo eliminar.");
        }
        doGet(req, resp);
    }
}

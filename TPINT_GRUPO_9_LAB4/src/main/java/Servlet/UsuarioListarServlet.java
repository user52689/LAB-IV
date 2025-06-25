package Servlet;

import Modelo.Usuario;
import Negocio.UsuarioNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String dni = req.getParameter("dni");
        List<Usuario> usuarios = null;

        if (dni != null && !dni.trim().isEmpty()) {
            Usuario usuario = null;
            try {
                usuario = usuarioNegocio.buscarUsuarioPorDniExacto(dni.trim());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            usuarios = new ArrayList<>();
            if (usuario != null) usuarios.add(usuario);
        } else {
            try {
                usuarios = usuarioNegocio.listarUsuarios();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("listaUsuarios", usuarios);
        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/ListadoUsuario.jsp").forward(req, resp);
    }
}

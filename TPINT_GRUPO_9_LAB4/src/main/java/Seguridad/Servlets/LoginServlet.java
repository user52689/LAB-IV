package Seguridad.Servlets;

import Modelo.Usuario;
import Seguridad.Negocio.AutenticacionFallidaException;
import Seguridad.Negocio.AutenticacionService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AutenticacionService autenticacionService;

    @Override
    public void init() throws ServletException {
        try {
            autenticacionService = new AutenticacionService();
        } catch (SQLException e) {
            throw new ServletException("Error inicializando AutenticacionService", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");

        try {
            Usuario usuario = autenticacionService.login(nombreUsuario, contrasena);
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);

            if (usuario.esAdministrador()) {
                response.sendRedirect(request.getContextPath() + "/Vistas/Administrador/MenuPrincipal/MenuAdministrador.jsp");
            } else if (usuario.esCliente()) {
                response.sendRedirect(request.getContextPath() + "/Vistas/Clientes/MenuPrincipal/MenuCliente.jsp");
            }
        } catch (AutenticacionFallidaException e) {
            request.setAttribute("mensajeError", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("/Vistas/Inicio/login.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("mensajeError", "Error en la base de datos: " + e.getMessage());
            request.getRequestDispatcher("/Vistas/Inicio/Login.jsp").forward(request, response);
        }
    }
}
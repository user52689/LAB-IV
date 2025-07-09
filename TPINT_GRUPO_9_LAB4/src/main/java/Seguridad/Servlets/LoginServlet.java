package Seguridad.Servlets;

import Modelo.Usuario;
import Modelo.Cliente;
import Seguridad.Negocio.AutenticacionService;
import Seguridad.Negocio.AuthResponse;
import Seguridad.Negocio.AutenticacionFallidaException;
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
            // Autenticar usuario y obtener cliente (si aplica)
            AuthResponse authResponse = autenticacionService.login(nombreUsuario, contrasena);
            Usuario usuario = authResponse.getUsuario();
            Cliente cliente = authResponse.getCliente();

            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioLogueado", usuario);

            // Si es cliente, verificar y almacenar clienteLogueado
            if ("cliente".equalsIgnoreCase(usuario.getRol())) {
                if (cliente != null) {
                    session.setAttribute("clienteLogueado", cliente);
                    System.out.println("LoginServlet: idCliente = " + cliente.getIdCliente() + " almacenado para usuario " + nombreUsuario);
                } else {
                    System.out.println("LoginServlet: No se encontró cliente para dni " + usuario.getDni());
                    request.setAttribute("mensajeError", "No se encontró información de cliente asociada.");
                    request.getRequestDispatcher("/Vistas/Inicio/Login.jsp").forward(request, response);
                    return;
                }
            }

            // Redirigir según rol
            String redirectUrl = "cliente".equalsIgnoreCase(usuario.getRol())
                    ? "/Vistas/Clientes/MenuPrincipal/MenuCliente.jsp"
                    : "/Vistas/Administrador/MenuPrincipal/MenuAdministrador.jsp";
            response.sendRedirect(request.getContextPath() + redirectUrl);

        } catch (AutenticacionFallidaException e) {
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("/Vistas/Inicio/Login.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("mensajeError", "Error de base de datos: " + e.getMessage());
            request.getRequestDispatcher("/Vistas/Inicio/Login.jsp").forward(request, response);
        }
    }
}
package Servlet;

import Modelo.Usuario;
import Negocio.UsuarioNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/usuario/modificar")
public class UsuarioModificarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioNegocio usuarioNegocio;

    @Override
    public void init() throws ServletException {
        try {
            usuarioNegocio = new UsuarioNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar UsuarioNegocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        try {
            if (dni != null && !dni.isEmpty()) {
                Usuario usuario = usuarioNegocio.buscarUsuarioPorDniExacto(dni);
                if (usuario == null) {
                    request.setAttribute("mensajeError", "Usuario no encontrado.");
                    request.removeAttribute("mensajeExito"); // Limpio el otro mensaje
                } else {
                    request.setAttribute("usuario", usuario);
                    request.removeAttribute("mensajeError");
                    request.removeAttribute("mensajeExito");
                }
            } else {
                // Si no viene dni, limpio mensajes para que no aparezcan por defecto
                request.removeAttribute("mensajeError");
                request.removeAttribute("mensajeExito");
            }
        } catch (SQLException e) {
            throw new ServletException("Error al buscar usuario", e);
        }
        request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/ModificacionUsuario.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String correoElectronico = request.getParameter("correoElectronico");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        if (dni == null || dni.isEmpty() || correoElectronico == null || correoElectronico.isEmpty()) {
            request.setAttribute("mensajeError", "Datos incompletos o inválidos.");
            doGet(request, response);
            return;
        }

        try {
            Usuario usuario = usuarioNegocio.buscarUsuarioPorDniExacto(dni);
            if (usuario == null) {
                request.setAttribute("mensajeError", "Usuario no encontrado.");
                request.getRequestDispatcher("/Vistas/Error.jsp").forward(request, response);
                return;
            }

            usuario.setCorreoElectronico(correoElectronico);
            usuario.setTelefono(telefono);
            usuario.setDireccion(direccion);

            boolean actualizado = usuarioNegocio.modificarUsuario(usuario);

            if (actualizado) {
                request.setAttribute("mensajeExito", "Perfil actualizado correctamente.");
            } else {
                request.setAttribute("mensajeError", "No se pudo actualizar el perfil.");
            }

            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/ModificacionUsuario.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al modificar usuario", e);
        }
    }
}

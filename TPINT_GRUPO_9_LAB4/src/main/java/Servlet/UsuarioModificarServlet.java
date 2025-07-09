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
                } else {
                    request.setAttribute("usuario", usuario);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Error al buscar usuario", e);
        }

        request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Perfil/PerfilUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String action = request.getParameter("action");
        String field = request.getParameter("field");

        if (dni == null || dni.isEmpty() || field == null || field.isEmpty()) {
            request.setAttribute("mensajeError", "Parámetros action o field no proporcionados.");
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

            boolean actualizado = false;

            switch (field) {
                case "nombre":
                    String nuevoNombre = request.getParameter("nombre");
                    if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                        usuario.setNombre(nuevoNombre);
                        actualizado = usuarioNegocio.modificarUsuario(usuario);
                    } else {
                        request.setAttribute("mensajeError", "El nombre no puede estar vacío.");
                    }
                    break;

                case "correoElectronico":
                    String nuevoCorreo = request.getParameter("correoElectronico");
                    if (nuevoCorreo != null && !nuevoCorreo.isEmpty()) {
                        usuario.setCorreoElectronico(nuevoCorreo);
                        actualizado = usuarioNegocio.modificarUsuario(usuario);
                    } else {
                        request.setAttribute("mensajeError", "El correo no puede estar vacío.");
                    }
                    break;

                case "telefono":
                    String nuevoTelefono = request.getParameter("telefono");
                    if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
                        usuario.setTelefono(nuevoTelefono);
                        actualizado = usuarioNegocio.modificarUsuario(usuario);
                    } else {
                        request.setAttribute("mensajeError", "El teléfono no puede estar vacío.");
                    }
                    break;

                case "direccion":
                    String nuevaDireccion = request.getParameter("direccion");
                    if (nuevaDireccion != null && !nuevaDireccion.isEmpty()) {
                        usuario.setDireccion(nuevaDireccion);
                        actualizado = usuarioNegocio.modificarUsuario(usuario);
                    } else {
                        request.setAttribute("mensajeError", "La dirección no puede estar vacía.");
                    }
                    break;

                case "contrasena":
                    String nuevaContrasena = request.getParameter("nuevaContrasena");
                    String confirmarContrasena = request.getParameter("confirmarContrasena");
                    if (nuevaContrasena != null && confirmarContrasena != null && nuevaContrasena.equals(confirmarContrasena)) {
                        usuario.setContrasena(nuevaContrasena);
                        actualizado = usuarioNegocio.resetearContrasena(usuario.getDni(), nuevaContrasena);
                    } else {
                        request.setAttribute("mensajeError", "Las contraseñas no coinciden o son inválidas.");
                    }
                    break;

                default:
                    request.setAttribute("mensajeError", "Campo no reconocido: " + field);
                    break;
            }

            if (actualizado) {
                request.setAttribute("mensajeExito", "Perfil actualizado correctamente.");
            } else if (request.getAttribute("mensajeError") == null) {
                request.setAttribute("mensajeError", "No se pudo actualizar el perfil.");
            }

            usuario = usuarioNegocio.buscarUsuarioPorDniExacto(dni);
            request.setAttribute("usuario", usuario);

            request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Perfil/PerfilUsuario.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al modificar el usuario", e);
        }
    }
}

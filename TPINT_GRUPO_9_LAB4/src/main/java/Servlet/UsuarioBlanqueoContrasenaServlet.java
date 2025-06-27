package Servlet;

import Modelo.Usuario;
import Negocio.UsuarioNegocio;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/usuario/blanqueo")
public class UsuarioBlanqueoContrasenaServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        if (dni != null && !dni.trim().isEmpty()) {
            try {
                Usuario usuario = usuarioNegocio.buscarUsuarioPorDniExacto(dni);
                request.setAttribute("usuario", usuario);
            } catch (Exception e) {
                request.setAttribute("mensajeError", "Error al buscar usuario: " + e.getMessage());
            }
        }
        request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/BlanqueoContrasenaUsuario.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String dni = request.getParameter("dni");
        String contrasena = request.getParameter("contrasena");

        if ("blanquear".equals(accion) && dni != null && !dni.trim().isEmpty()) {
            try {
                Usuario usuario = usuarioNegocio.buscarUsuarioPorDniExacto(dni);
                if (usuario != null) {
                    boolean actualizado = usuarioNegocio.resetearContrasena(dni, contrasena);
                    if (actualizado) {
                        request.setAttribute("mensajeExito", "Contraseña blanqueada con éxito para el usuario DNI: " + dni);
                    } else {
                        request.setAttribute("mensajeError", "No se pudo actualizar la contraseña.");
                    }
                    //usuario.setContrasena(contrasena); 
                    request.setAttribute("usuario", usuario);
                } else {
                    request.setAttribute("mensajeError", "No se encontró el usuario con DNI: " + dni);
                }
            } catch (Exception e) {
                request.setAttribute("mensajeError", "Error al blanquear la contraseña: " + e.getMessage());
            }
        } else {
            request.setAttribute("mensajeError", "Parámetros inválidos.");
        }

        request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/BlanqueoContrasenaUsuario.jsp").forward(request, response);
    }
}


package Servlet;

import Modelo.Usuario;
import DAO.UsuarioDAO;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/usuario/blanquear")
public class UsuarioModificarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            usuarioDAO = new UsuarioDAO();  
        } catch (Exception e) {
            throw new ServletException("Error al iniciar UsuarioDAO", e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        if (dni != null && !dni.trim().isEmpty()) {
            try {
                Usuario usuario = usuarioDAO.obtenerUsuarioPorDni(dni);
                request.setAttribute("usuario", usuario);
            } catch (Exception e) {
                request.setAttribute("mensajeError", "Error al buscar usuario: " + e.getMessage());
            }
        }
        request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/ModificacionUsuario.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String dni = request.getParameter("dni");
        
        if ("blanquear".equals(accion) && dni != null && !dni.trim().isEmpty()) {
            try {
                Usuario usuario = usuarioDAO.obtenerUsuarioPorDni(dni);
                if (usuario != null) {
                    String nuevaContrasena = "123456"; 
                    usuario.setContrasena(nuevaContrasena);
                    
                    boolean actualizado = usuarioDAO.resetearContrasena();
                    if (actualizado) {
                        request.setAttribute("mensajeExito", "Contraseña blanqueada con éxito para el usuario DNI: " + dni);
                    } else {
                        request.setAttribute("mensajeError", "No se pudo actualizar la contraseña.");
                    }
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
        
        request.getRequestDispatcher("/Vistas/Usuario/BlanquearContrasena.jsp").forward(request, response);
    }
}


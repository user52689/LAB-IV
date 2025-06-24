package Servlet;


import Modelo.Usuario;
import Negocio.UsuarioNegocio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/usuario/alta")
public class UsuarioAltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtener parámetros del formulario
            String dni = request.getParameter("dni");
            String nombreUsuario = request.getParameter("nombre_usuario");
            String contrasenaPlano = request.getParameter("contrasena");
            String rol = request.getParameter("rol");
            String correo = request.getParameter("correo_electronico");

            // Crear usuario base
            Usuario nuevo = new Usuario();
            nuevo.setDni(dni);
            nuevo.setNombreUsuario(nombreUsuario);
            nuevo.setContrasena(contrasenaPlano); 
            nuevo.setRol(rol);
            nuevo.setCorreoElectronico(correo);
            nuevo.setActivo(true);

            // Usamos capa de negocio
            UsuarioNegocio negocio = new UsuarioNegocio();
            boolean exito = negocio.registrarUsuario(nuevo);

            if (exito) {
            	request.setAttribute("msg", "ok");
            	request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/AltaUsuario.jsp").forward(request, response);

            } else {
                request.setAttribute("error", "No se pudo registrar el usuario.");
                request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/AltaUsuario.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/AltaUsuario.jsp").forward(request, response);
        }
    }
}

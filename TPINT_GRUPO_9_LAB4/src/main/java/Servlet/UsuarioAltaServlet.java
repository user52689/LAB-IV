package Servlet;

import Negocio.GeneroNegocio;
import Negocio.NacionalidadNegocio;
import Negocio.ProvinciaNegocio;
import Negocio.UsuarioNegocio;
import Negocio.LocalidadNegocio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Modelo.Usuario;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/usuario/alta")
public class UsuarioAltaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            cargarCombos(request);
            
            request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/AltaUsuario.jsp")
                   .forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Error al cargar datos: " + ex.getMessage());
            request.getRequestDispatcher("/Vistas/Util/Error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            cargarCombos(request);

            Usuario nuevo = mapearUsuarioDesdeRequest(request); 

            UsuarioNegocio un = new UsuarioNegocio();
            boolean ok = un.registrarUsuario(nuevo);

            if (ok) {
                request.setAttribute("msg", "ok");
            } else {
                request.setAttribute("error", "No se pudo guardar.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", ex.getMessage());
            try {
                cargarCombos(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Usuarios/AltaUsuario.jsp")
               .forward(request, response);
    }

    private Usuario mapearUsuarioDesdeRequest(HttpServletRequest request) throws Exception {
        Usuario u = new Usuario();

        u.setDni(request.getParameter("dni"));
        u.setNombreUsuario(request.getParameter("nombre_usuario"));
        u.setContrasena(request.getParameter("contrasena"));
        u.setCorreoElectronico(request.getParameter("correo_electronico"));
        u.setRol(request.getParameter("rol"));

        u.setIdGenero(Integer.parseInt(request.getParameter("id_genero")));
        u.setIdPais(Integer.parseInt(request.getParameter("id_pais")));
        u.setIdProvincia(Integer.parseInt(request.getParameter("id_provincia")));
        u.setIdLocalidad(Integer.parseInt(request.getParameter("id_localidad")));

        u.setFechaNacimiento(LocalDate.parse(request.getParameter("fecha_nacimiento")));
        u.setDireccion(request.getParameter("direccion"));
        u.setActivo(true);

        return u;
    }

    
    private void cargarCombos(HttpServletRequest request) throws Exception {
        GeneroNegocio generoNegocio = new GeneroNegocio();
        NacionalidadNegocio paisNegocio = new NacionalidadNegocio();
        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocio();
        LocalidadNegocio localidadNegocio = new LocalidadNegocio();

        request.setAttribute("generos", generoNegocio.listarGeneros());
        request.setAttribute("paises", paisNegocio.listarNacionalidades());
        request.setAttribute("provincias", provinciaNegocio.listarProvincias());
        request.setAttribute("localidades", localidadNegocio.listarLocalidades());
    }
}
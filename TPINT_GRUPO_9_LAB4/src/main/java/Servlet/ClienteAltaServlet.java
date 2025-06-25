package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Cliente;
import Negocio.ClienteNegocio;
import Negocio.GeneroNegocio;
import Negocio.LocalidadNegocio;
import Negocio.NacionalidadNegocio;
import Negocio.ProvinciaNegocio;

@WebServlet("/cliente/alta")
public class ClienteAltaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteNegocio clienteNegocio;

    @Override
    public void init() throws ServletException {
        try {
            clienteNegocio = new ClienteNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar ClienteNegocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            cargarCombos(request);
            RequestDispatcher rd = request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/AltaCliente.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar datos para alta cliente: " + e.getMessage());
            request.getRequestDispatcher("/Vistas/Util/Error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            cargarCombos(req);

            Cliente c = mapearClienteDesdeRequest(req);
            boolean ok = clienteNegocio.agregarCliente(c);

            if (ok) {
                req.setAttribute("mensajeExito", "Usuario guardado correctamente.");
            } else {
                req.setAttribute("mensajeError", "No se pudo guardar el usuario.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Error al agregar cliente: " + e.getMessage());
        }

        RequestDispatcher rd = req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/AltaCliente.jsp");
        rd.forward(req, resp);
    }

    private Cliente mapearClienteDesdeRequest(HttpServletRequest req) {
        Cliente c = new Cliente();
        
        c.setDni(req.getParameter("dni"));
        c.setCuil(req.getParameter("cuil"));
        c.setNombre(req.getParameter("nombre"));
        c.setApellido(req.getParameter("apellido"));
        c.setGenero(Integer.parseInt(req.getParameter("generos")));
        c.setPais(Integer.parseInt(req.getParameter("nacionalidades"))); 
        c.setProvincia(Integer.parseInt(req.getParameter("provincias")));
        c.setLocalidad(Integer.parseInt(req.getParameter("localidades")));
        
        String fechaNacimientoStr = req.getParameter("fechaNacimiento");
        if (fechaNacimientoStr == null || fechaNacimientoStr.isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        
        c.setFechaNacimiento(LocalDate.parse(fechaNacimientoStr));
        c.setDireccion(req.getParameter("direccion"));
        c.setCorreoElectronico(req.getParameter("correoElectronico"));
        c.setTelefono(req.getParameter("telefono"));
        c.setUsuario(req.getParameter("usuario"));
        return c;
    }

    private void cargarCombos(HttpServletRequest request) throws Exception {
        GeneroNegocio generoNegocio = new GeneroNegocio();
        NacionalidadNegocio nacionalidadNegocio = new NacionalidadNegocio();
        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocio();
        LocalidadNegocio localidadNegocio = new LocalidadNegocio();

        request.setAttribute("generos", generoNegocio.listarGeneros());
        request.setAttribute("nacionalidades", nacionalidadNegocio.listarNacionalidades()); 
        request.setAttribute("provincias", provinciaNegocio.listarProvincias());
        request.setAttribute("localidades", localidadNegocio.listarLocalidades());
    }
}

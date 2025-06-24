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
            ProvinciaNegocio provNeg = new ProvinciaNegocio();
            LocalidadNegocio locNeg = new LocalidadNegocio();
            GeneroNegocio genNeg = new GeneroNegocio();
            NacionalidadNegocio nacNeg = new NacionalidadNegocio();

            request.setAttribute("provincias", provNeg.listarProvincias());
            request.setAttribute("localidades", locNeg.listarLocalidades()); 
            request.setAttribute("generos", genNeg.listarGeneros());
            request.setAttribute("nacionalidades", nacNeg.listarNacionalidades());

            RequestDispatcher rd = request.getRequestDispatcher("/Vistas/Cliente/AltaCliente.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al cargar datos para alta cliente", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Cliente c = construirClienteDesdeRequest(req);
            clienteNegocio.agregarCliente(c);
            resp.sendRedirect(req.getContextPath() + "/cliente/listar");
        } catch (Exception e) {
            throw new ServletException("Error al agregar cliente", e);
        }
    }

    private Cliente construirClienteDesdeRequest(HttpServletRequest req) {
        Cliente c = new Cliente();
        c.setDni(req.getParameter("dni"));
        c.setCuil(req.getParameter("cuil"));
        c.setNombre(req.getParameter("nombre"));
        c.setApellido(req.getParameter("apellido"));
        c.setGenero(Integer.parseInt(req.getParameter("generos")));
        c.setPais(Integer.parseInt(req.getParameter("nacionalidades")));
        c.setFechaNacimiento(LocalDate.parse(req.getParameter("fechaNacimiento")));
        c.setDireccion(req.getParameter("direccion"));
        c.setLocalidad(Integer.parseInt(req.getParameter("localidades")));
        c.setProvincia(Integer.parseInt(req.getParameter("provincias")));
        c.setCorreoElectronico(req.getParameter("correoElectronico"));
        c.setTelefono(req.getParameter("telefono"));
        c.setUsuario(req.getParameter("usuario"));
        return c;
    }
}
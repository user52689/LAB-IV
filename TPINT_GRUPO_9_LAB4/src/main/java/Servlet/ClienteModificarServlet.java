package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Cliente;
import Negocio.ClienteNegocio;

@WebServlet("/cliente/modificar")
public class ClienteModificarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteNegocio clienteNegocio;

    @Override
    public void init() throws ServletException {
        try {
            clienteNegocio = new ClienteNegocio();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String tipoModificacion = req.getParameter("tipoModificacion");

            if ("reseteoAdmin".equals(tipoModificacion)) {
                // Modificación solo de la contraseña, la seteás fija "1234"
                int idCliente = Integer.parseInt(req.getParameter("idCliente"));
                clienteNegocio.resetearContrasena(idCliente, "1234");
                resp.sendRedirect(req.getContextPath() + "/cliente/listar");
            
            } else if ("cambioUsuario".equals(tipoModificacion)) {
                // Cambio voluntario por parte del usuario
                int idCliente = Integer.parseInt(req.getParameter("idCliente"));
                String nuevaContrasena = req.getParameter("nuevaContrasena");
                clienteNegocio.resetearContrasena(idCliente, nuevaContrasena);
                resp.sendRedirect(req.getContextPath() + "/cliente/perfil"); // o donde corresponda

            } else {
                // Modificación general del cliente (otros datos)
                Cliente c = construirClienteDesdeRequest(req);
                c.setIdCliente(Integer.parseInt(req.getParameter("idCliente")));
                clienteNegocio.modificarCliente(c);
                resp.sendRedirect(req.getContextPath() + "/cliente/listar");
            }
        } catch (Exception e) {
            throw new ServletException("Error al modificar cliente", e);
        }
    }


    private Cliente construirClienteDesdeRequest(HttpServletRequest req) {
        Cliente c = new Cliente();
        c.setDni(req.getParameter("dni"));
        c.setCuil(req.getParameter("cuil"));
        c.setNombre(req.getParameter("nombre"));
        c.setApellido(req.getParameter("apellido"));
        c.setGenero(Integer.parseInt(req.getParameter("genero")));
        c.setPais(Integer.parseInt(req.getParameter("pais")));
        c.setFechaNacimiento(LocalDate.parse(req.getParameter("fechaNacimiento")));
        c.setDireccion(req.getParameter("direccion"));
        c.setLocalidad(Integer.parseInt(req.getParameter("localidad")));
        c.setProvincia(Integer.parseInt(req.getParameter("provincia")));
        c.setCorreoElectronico(req.getParameter("correoElectronico"));
        c.setTelefono(req.getParameter("telefono"));
        c.setUsuario(req.getParameter("usuario"));
        return c;
    }
}

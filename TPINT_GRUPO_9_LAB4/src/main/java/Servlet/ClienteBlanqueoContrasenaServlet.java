package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Cliente;
import Negocio.ClienteNegocio;

@WebServlet("/cliente/blanqueo")
public class ClienteBlanqueoContrasenaServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dni = req.getParameter("dni");
        if (dni != null && !dni.trim().isEmpty()) {
            try {
                Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni);
                req.setAttribute("cliente", cliente);
            } catch (SQLException e) {
                req.setAttribute("mensajeError", "Error al buscar cliente: " + e.getMessage());
            }
        }
        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/BlanqueoContrasenaCliente.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        String dni = req.getParameter("dni");
        String contrasena = req.getParameter("contrasena");
        
        if ("blanquear".equals(accion) && dni != null && !dni.trim().isEmpty()) {
            try {
                Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni);
                if (cliente != null) {
                    boolean actualizado = clienteNegocio.resetearContrasena(dni, contrasena);
                    if (actualizado) {
                        req.setAttribute("mensajeExito", "Contraseña blanqueada con éxito para el cliente DNI: " + dni);
                    } else {
                        req.setAttribute("mensajeError", "No se pudo actualizar la contraseña.");
                    }
                    req.setAttribute("cliente", cliente);
                } else {
                    req.setAttribute("mensajeError", "No se encontró cliente con DNI: " + dni);
                }
            } catch (Exception e) {
                req.setAttribute("mensajeError", "Error al blanquear la contraseña: " + e.getMessage());
            }
            req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/BlanqueoContrasenaCliente.jsp").forward(req, resp);
        
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida o parámetros incompletos");
        }
    }
}


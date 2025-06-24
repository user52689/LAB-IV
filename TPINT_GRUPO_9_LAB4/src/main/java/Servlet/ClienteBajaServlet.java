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

@WebServlet("/cliente/baja")
public class ClienteBajaServlet extends HttpServlet {
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
            Cliente cliente = null;
			try {
				cliente = clienteNegocio.buscarClientePorDniExacto(dni);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            req.setAttribute("cliente", cliente);
        }
        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/BajaCliente.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dni = req.getParameter("dni");
        boolean exito = false;
		try {
			exito = clienteNegocio.borrarClientePorDni(dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (exito) {
            req.setAttribute("mensajeExito", "Cliente eliminado.");
        } else {
            req.setAttribute("mensajeError", "No se pudo eliminar.");
        }
        doGet(req, resp); 
    }
}


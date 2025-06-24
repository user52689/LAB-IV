package Servlet;

import Modelo.Cliente;

import Negocio.ClienteNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cliente/listar")
public class ClienteListarServlet extends HttpServlet {
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
        List<Cliente> clientes = null;
        if (dni != null && !dni.trim().isEmpty()) {
            Cliente cliente = null;
			try {
				cliente = clienteNegocio.buscarClientePorDniExacto(dni.trim());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            clientes = new ArrayList<>();
            if (cliente != null) clientes.add(cliente);
        } else {
            try {
				clientes = clienteNegocio.listarClientes();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        req.setAttribute("listaClientes", clientes);
        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ListadoCliente.jsp").forward(req, resp);
    }
}

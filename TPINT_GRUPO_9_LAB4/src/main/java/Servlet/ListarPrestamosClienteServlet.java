package Servlet;

import DAO.PrestamoDAO;
import Modelo.Cliente;
import Modelo.Prestamo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ListarPrestamosClienteServlet")
public class ListarPrestamosClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListarPrestamosClienteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");

        if (cliente == null) {
            response.sendRedirect("Vistas/Inicio/Login.jsp");
            return;
        }

        try {
            PrestamoDAO dao = new PrestamoDAO();
            List<Prestamo> prestamos = dao.listarPorCliente(cliente.getIdCliente());

            request.setAttribute("prestamos", prestamos);
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/ListarPrestamos.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al listar préstamos.");
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/MenuPrestamos.jsp")
                   .forward(request, response);
        }
    }
}

package Servlet;

import Modelo.Prestamo;
import Negocio.PrestamoNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DetallePrestamoServlet")
public class DetallePrestamoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("ListarPrestamosClienteServlet");
            return;
        }

        try {
            int idPrestamo = Integer.parseInt(idParam);
            PrestamoNegocio negocio = new PrestamoNegocio();
            Prestamo prestamo = negocio.buscarPorId(idPrestamo);

            if (prestamo != null) {
                request.setAttribute("prestamo", prestamo);
                request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/DetallePrestamo.jsp")
                       .forward(request, response);
            } else {
                response.sendRedirect("ListarPrestamosClienteServlet");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(500, "Error al cargar detalle del préstamo.");
        }
    }
}

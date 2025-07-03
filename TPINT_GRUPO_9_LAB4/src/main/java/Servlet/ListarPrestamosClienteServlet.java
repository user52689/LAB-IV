package Servlet;

import Modelo.Prestamo;
import Negocio.PrestamoNegocio;
import Servlet.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ListarPrestamosClienteServlet")
public class ListarPrestamosClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrestamoNegocio prestamoNegocio;

    @Override
    public void init() throws ServletException {
        try {
            prestamoNegocio = new PrestamoNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error inicializando PrestamoNegocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener id_cliente desde la sesión usando SessionUtil
            int idCliente = SessionUtil.getIdClienteFromSession(request);

            // Obtener préstamos del cliente
            List<Prestamo> prestamos = prestamoNegocio.listarPorCliente(idCliente);
            request.setAttribute("prestamosCliente", prestamos);
            System.out.println("ListarPrestamosClienteServlet: Cargados " + prestamos.size() + " préstamos para idCliente = " + idCliente);
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/ListarPrestamos.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al obtener los préstamos: " + e.getMessage());
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/ListarPrestamos.jsp")
                   .forward(request, response);
        } catch (ServletException e) {
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("Vistas/Inicio/Login.jsp").forward(request, response);
        }
    }
}
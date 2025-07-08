package Servlet;

import Modelo.Cliente;
import Negocio.PrestamoNegocio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Prestamos/listar")
public class ListarPrestamosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrestamoNegocio prestamoNegocio;

    @Override
    public void init() throws ServletException {
        try {
            prestamoNegocio = new PrestamoNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error inicializando negocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("clienteLogueado") == null) {
            request.setAttribute("mensajeError", "No hay sesión activa. Por favor, inicie sesión.");
            request.getRequestDispatcher("/Vistas/Inicio/Login.jsp").forward(request, response);
            return;
        }

        Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
        int idCliente = clienteLogueado.getIdCliente();
        System.out.println("[DEBUG] idCliente: " + idCliente);

        try {
            List<Modelo.Prestamo> prestamos = prestamoNegocio.listarPorClienteConSaldo(idCliente);
            System.out.println("[DEBUG] ListarPrestamosServlet: Cargados " + prestamos.size() + " préstamos para idCliente = " + idCliente);
            if (prestamos.isEmpty()) {
                System.out.println("[DEBUG] La lista prestamosCliente está vacía");
            } else {
                System.out.println("[DEBUG] Préstamos: " + prestamos);
                for (Modelo.Prestamo p : prestamos) {
                    System.out.println("[DEBUG] Préstamo ID: " + p.getIdPrestamo() + ", Importe: " + p.getImporteTotal() + ", Saldo: " + p.getSaldoPendiente() + ", Estado: " + p.getEstadoPrestamo().getDescripcion());
                }
            }
            request.setAttribute("prestamosCliente", prestamos);
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/ListarPrestamos.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[ERROR] SQLException en ListarPrestamosServlet: " + e.getMessage());
            request.setAttribute("mensajeError", "Error al cargar préstamos: " + e.getMessage());
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/ListarPrestamos.jsp")
                   .forward(request, response);
        }
    }
}
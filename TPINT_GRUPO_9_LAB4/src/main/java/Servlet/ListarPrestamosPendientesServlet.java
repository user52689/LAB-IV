package Servlet;

import Negocio.PrestamoNegocio;
import Modelo.Prestamo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Prestamos/pendientes")
public class ListarPrestamosPendientesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrestamoNegocio prestamoNegocio;

    @Override
    public void init() throws ServletException {
        try {
            prestamoNegocio = new PrestamoNegocio();
        } catch (SQLException e) {
            throw new ServletException("No se pudo inicializar PrestamoNegocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Prestamo> lista = prestamoNegocio.listarPendientes();
            req.setAttribute("listaPrestamos", lista);
            req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Prestamos/PrestamosSolicitadosClientes.jsp")
               .forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error al listar préstamos pendientes", e);
        }
    }
}

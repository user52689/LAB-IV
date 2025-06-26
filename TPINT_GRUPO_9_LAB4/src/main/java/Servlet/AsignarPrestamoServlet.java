package Servlet;

import Negocio.PrestamoNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet("/Prestamos/asignar")
public class AsignarPrestamoServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("idPrestamo"));
            boolean ok = prestamoNegocio.aprobarPrestamo(id);
            String mensaje = ok
                ? "Préstamo aprobado correctamente"
                : "No se pudo aprobar el préstamo";
            String url = req.getContextPath() 
                       + "/Prestamos/pendientes?msg=" 
                       + URLEncoder.encode(mensaje, "UTF-8");
            resp.sendRedirect(url);
        } catch (SQLException e) {
            throw new ServletException("Error al aprobar préstamo", e);
        }
    }
}

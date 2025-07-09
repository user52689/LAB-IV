package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.ReporteFinanciero;
import Negocio.ReporteNegocio;

@WebServlet("/ReporteFinancieroServlet")
public class ReporteFinancieroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String desdeStr = request.getParameter("fechaDesde");
            String hastaStr = request.getParameter("fechaHasta");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date desde = sdf.parse(desdeStr);
            Date hasta = sdf.parse(hastaStr);

            ReporteNegocio negocio = new ReporteNegocio();
            ReporteFinanciero reporte = negocio.obtenerReporte(desde, hasta);

            request.setAttribute("reporte", reporte);
            request.setAttribute("fechaDesde", desdeStr);
            request.setAttribute("fechaHasta", hastaStr);

        } catch (Exception e) {
            request.setAttribute("error", "Error al generar el reporte: " + e.getMessage());
        }

        request.getRequestDispatcher("Vistas/Administrador/MenuPrincipal/Reportes/ReporteFinanciero.jsp").forward(request, response);
    }
}

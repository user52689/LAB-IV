package Servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Modelo.Cliente;
import Modelo.ReporteCliente;
import Negocio.ClienteNegocio;
import Negocio.ReporteNegocio;

@WebServlet("/ReporteClienteServlet")
public class ReporteClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GET: solo carga lista de clientes
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ClienteNegocio clienteNeg = new ClienteNegocio();
            List<Cliente> listaClientes = clienteNeg.listarClientes();
            request.setAttribute("listaClientes", listaClientes);
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo cargar la lista de clientes: " + e.getMessage());
        }

        request.getRequestDispatcher("MenuPrincipal/Reportes/ReporteCliente.jsp")
               .forward(request, response);
    }

    // POST: genera el reporte
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            String desdeStr = request.getParameter("fechaDesde");
            String hastaStr = request.getParameter("fechaHasta");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date desde = sdf.parse(desdeStr);
            Date hasta = sdf.parse(hastaStr);

            ReporteNegocio negocio = new ReporteNegocio();
            ReporteCliente reporte = negocio.obtenerReportePorCliente(idCliente, desde, hasta);

            request.setAttribute("reporte", reporte);
            request.setAttribute("idCliente", idCliente);
            request.setAttribute("fechaDesde", desdeStr);
            request.setAttribute("fechaHasta", hastaStr);
        } catch (Exception e) {
            request.setAttribute("error", "Error al generar el reporte: " + e.getMessage());
        }

        // volver a cargar clientes en POST también
        try {
            ClienteNegocio clienteNeg = new ClienteNegocio();
            List<Cliente> listaClientes = clienteNeg.listarClientes();
            request.setAttribute("listaClientes", listaClientes);
        } catch (SQLException e) {
            request.setAttribute("error", "No se pudo cargar la lista de clientes");
        }

        request.getRequestDispatcher("MenuPrincipal/Reportes/ReporteCliente.jsp")
               .forward(request, response);
    }
}

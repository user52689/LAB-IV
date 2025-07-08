package Servlet;

import Modelo.Cliente;
import Modelo.Cuenta;
import Modelo.Cuota;
import Negocio.CuentaNegocio;
import Negocio.CuotaNegocio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Prestamos/pagar")
public class PagarCuotaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuotaNegocio cuotaNegocio;
    private CuentaNegocio cuentaNegocio;

    @Override
    public void init() throws ServletException {
        try {
            cuotaNegocio = new CuotaNegocio();
            cuentaNegocio = new CuentaNegocio();
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
        String idPrestamoStr = request.getParameter("idPrestamo");

        // Validar idPrestamo
        if (idPrestamoStr == null || idPrestamoStr.trim().isEmpty()) {
            request.setAttribute("mensajeError", "ID de préstamo no proporcionado.");
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/ListarPrestamos.jsp")
                   .forward(request, response);
            return;
        }

        try {
            int idPrestamo = Integer.parseInt(idPrestamoStr);
            Cuota cuota = cuotaNegocio.obtenerProximaCuotaNoPagada(idPrestamo);
            List<Cuenta> cuentas = cuentaNegocio.listarCuentasPorCliente(idCliente);

            if (cuota == null) {
                request.setAttribute("mensajeError", "No hay cuotas pendientes para este préstamo.");
                request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/ListarPrestamos.jsp")
                       .forward(request, response);
                return;
            }

            request.setAttribute("cuota", cuota);
            request.setAttribute("cuentas", cuentas);
            request.setAttribute("totalCuotas", cuotaNegocio.obtenerTotalCuotas(idPrestamo));
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/PagarPrestamoCliente.jsp")
                   .forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("mensajeError", "ID de préstamo inválido.");
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/ListarPrestamos.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cargar datos: " + e.getMessage());
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/PagarPrestamoCliente.jsp")
                   .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("clienteLogueado") == null) {
            request.setAttribute("mensajeError", "No hay sesión activa. Por favor, inicie sesión.");
            request.getRequestDispatcher("/Vistas/Inicio/Login.jsp").forward(request, response);
            return;
        }

        try {
            int idCuota = Integer.parseInt(request.getParameter("idCuota"));
            int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
            int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));

            boolean exito = cuotaNegocio.pagarCuota(idCuota, idCuenta, idPrestamo);

            if (exito) {
                request.setAttribute("mensajeExito", "Cuota pagada exitosamente.");
            } else {
                request.setAttribute("mensajeError", "Error al procesar el pago de la cuota.");
            }
            request.getRequestDispatcher("/Prestamos/listar").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("mensajeError", "Datos inválidos en el formulario.");
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/PagarPrestamoCliente.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al procesar el pago: " + e.getMessage());
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Prestamos/PagarPrestamoCliente.jsp")
                   .forward(request, response);
        }
    }
}
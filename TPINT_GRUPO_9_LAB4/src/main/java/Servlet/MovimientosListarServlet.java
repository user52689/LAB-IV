package Servlet;

import Modelo.Cliente;
import Modelo.Cuenta;
import Modelo.Movimiento;
import Negocio.CuentaNegocio;
import Negocio.MovimientoNegocio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Movimientos/listar")
public class MovimientosListarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio;
    private MovimientoNegocio movimientoNegocio;

    @Override
    public void init() throws ServletException {
        try {
            cuentaNegocio = new CuentaNegocio();
            movimientoNegocio = new MovimientoNegocio();
            System.out.println("MovimientosListarServlet: Inicializado CuentaNegocio y MovimientoNegocio");
        } catch (SQLException e) {
            throw new ServletException("Error inicializando negocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("MovimientosListarServlet: Procesando GET");
        try {
            // Obtener cliente desde la sesión
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("clienteLogueado") == null) {
                System.out.println("MovimientosListarServlet: Sesión nula o clienteLogueado no encontrado");
                request.setAttribute("mensajeError", "No hay sesión activa. Por favor, inicie sesión.");
                request.getRequestDispatcher("/Vistas/Inicio/Login.jsp").forward(request, response);
                return;
            }

            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            int idCliente = clienteLogueado.getIdCliente();
            System.out.println("MovimientosListarServlet: idCliente = " + idCliente);

            // Obtener cuentas del cliente
            List<Cuenta> cuentas = cuentaNegocio.listarCuentasPorCliente(idCliente);
            if (cuentas.isEmpty()) {
                System.out.println("MovimientosListarServlet: No se encontraron cuentas para idCliente = " + idCliente);
                request.setAttribute("mensajeError", "No tenés cuentas registradas.");
                request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Cuentas/MovimientosCuentas.jsp")
                       .forward(request, response);
                return;
            }

            // Obtener movimientos por cuenta
            Map<Cuenta, List<Movimiento>> cuentasConMovimientos = new HashMap<>();
            for (Cuenta cuenta : cuentas) {
                List<Movimiento> movimientos = movimientoNegocio.listarMovimientosPorCuenta(cuenta.getIdCuenta());
                cuentasConMovimientos.put(cuenta, movimientos);
            }

            if (cuentasConMovimientos.values().stream().allMatch(List::isEmpty)) {
                System.out.println("MovimientosListarServlet: No se encontraron movimientos para idCliente = " + idCliente);
                request.setAttribute("mensajeError", "No hay movimientos para mostrar.");
            } else {
                System.out.println("MovimientosListarServlet: Cargadas cuentas con movimientos para idCliente = " + idCliente);
            }

            request.setAttribute("cuentasConMovimientos", cuentasConMovimientos);
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Cuentas/MovimientosCuentas.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("MovimientosListarServlet: Error SQL - " + e.getMessage());
            request.setAttribute("mensajeError", "Error al cargar los movimientos: " + e.getMessage());
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Cuentas/MovimientosCuentas.jsp")
                   .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
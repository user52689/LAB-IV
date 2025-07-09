package Servlet;

import Modelo.Cuenta;
import Negocio.CuentaNegocio;
import Servlet.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/IrASolicitarPrestamoServlet")
public class IrASolicitarPrestamoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio;

    @Override
    public void init() throws ServletException {
        try {
            cuentaNegocio = new CuentaNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error inicializando CuentaNegocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener id_cliente desde la sesión usando SessionUtil
            int idCliente = SessionUtil.getIdClienteFromSession(request);

            // Obtener cuentas del cliente
            List<Cuenta> cuentas = cuentaNegocio.buscarCuentasPorId(String.valueOf(idCliente));
            request.setAttribute("cuentasCliente", cuentas);
            System.out.println("IrASolicitarPrestamoServlet: Cargadas " + cuentas.size() + " cuentas para idCliente = " + idCliente);
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/SolicitarPrestamo.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cargar las cuentas del cliente: " + e.getMessage());
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/SolicitarPrestamo.jsp")
                   .forward(request, response);
        } catch (ServletException e) {
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("Vistas/Inicio/Login.jsp").forward(request, response);
        }
    }
}
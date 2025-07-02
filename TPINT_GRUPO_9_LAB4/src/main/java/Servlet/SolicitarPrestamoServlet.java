package Servlet;

import DAO.PrestamoDAO;
import Modelo.Cliente;
import Modelo.Cuenta;
import Modelo.EstadoPrestamo;
import Modelo.Prestamo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet("/SolicitarPrestamoServlet")
public class SolicitarPrestamoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SolicitarPrestamoServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. Obtener datos del formulario
        try {
            int idCuentaDeposito = Integer.parseInt(request.getParameter("idCuentaDeposito"));
            double importeSolicitado = Double.parseDouble(request.getParameter("importeSolicitado"));
            int plazoMeses = Integer.parseInt(request.getParameter("plazoMeses"));
            String observaciones = request.getParameter("observaciones");

            // 2. Obtener cliente desde sesión
            HttpSession session = request.getSession(false);
            Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");

            if (cliente == null) {
                response.sendRedirect("Vistas/Inicio/Login.jsp"); // redirige si no hay sesión
                return;
            }

            // 3. Calcular valores derivados
            double interesMensual = 0.05; // 5% mensual (ajustalo si tenés otro)
            double importeTotal = importeSolicitado * Math.pow(1 + interesMensual, plazoMeses);
            double montoCuota = importeTotal / plazoMeses;

            // 4. Crear objeto préstamo
            Prestamo p = new Prestamo();
            p.setCliente(cliente);

            Cuenta cuenta = new Cuenta();
            cuenta.setIdCuenta(idCuentaDeposito);
            p.setCuentaDeposito(cuenta);

            EstadoPrestamo estado = new EstadoPrestamo();
            estado.setIdEstadoPrestamo(1); // 1 = Pendiente
            p.setEstadoPrestamo(estado);

            p.setImporteSolicitado(importeSolicitado);
            p.setImporteTotal(importeTotal);
            p.setPlazoMeses(plazoMeses);
            p.setMontoCuota(montoCuota);
            p.setFechaSolicitud(LocalDateTime.now());
            p.setObservaciones(observaciones);
            p.setActivo(true);

            // 5. Guardar en la base de datos
            PrestamoDAO dao = new PrestamoDAO();
            int idGenerado = dao.agregar(p);

            if (idGenerado > 0) {
                p.setIdPrestamo(idGenerado);
                request.setAttribute("mensajeExito", "Préstamo solicitado exitosamente.");
                response.sendRedirect("Vistas/Clientes/MenuPrincipal/Prestamos/MenuPrestamos.jsp");
            } else {
                request.setAttribute("mensajeError", "Error al solicitar el préstamo.");
                request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/SolicitarPrestamo.jsp")
                        .forward(request, response);
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Datos inválidos o error interno.");
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/SolicitarPrestamo.jsp")
                    .forward(request, response);
        }
    }
}

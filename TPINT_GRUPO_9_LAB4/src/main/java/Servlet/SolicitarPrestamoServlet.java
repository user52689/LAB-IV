package Servlet;

import DAO.PrestamoDAO;
import Modelo.Cliente;
import Modelo.Cuenta;
import Modelo.EstadoPrestamo;
import Modelo.Prestamo;
import Modelo.Usuario;

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

        try {
            // 1. Obtener datos del formulario
            int idCuentaDeposito = Integer.parseInt(request.getParameter("idCuentaDeposito"));
            double importeSolicitado = Double.parseDouble(request.getParameter("importeSolicitado"));
            int plazoMeses = Integer.parseInt(request.getParameter("plazoMeses"));
            String observaciones = request.getParameter("observaciones");

            // 2. Obtener usuario logueado desde la sesión
            HttpSession session = request.getSession(false);
            Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;

            if (usuario == null) {
                request.setAttribute("mensajeError", "Debe iniciar sesión para solicitar un préstamo.");
                request.getRequestDispatcher("Vistas/Inicio/Login.jsp").forward(request, response);
                return;
            }

            System.out.println("Usuario logueado: ID = " + usuario.getIdUsuario() + ", Nombre = " + usuario.getNombreUsuario());

            // 3. Crear objeto Cliente
            Cliente cliente = new Cliente();
            cliente.setIdCliente(usuario.getIdUsuario()); // ID de Usuario coincide con ID de Cliente

            // 4. Cálculos del préstamo
            double tasaInteresFija = 0.10;
            double importeTotal = importeSolicitado * (1 + tasaInteresFija);
            double montoCuota = importeTotal / plazoMeses;

            // 5. Crear préstamo
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

            // 6. Guardar préstamo
            PrestamoDAO dao = new PrestamoDAO();
            int idGenerado = dao.agregar(p);

            if (idGenerado > 0) {
                p.setIdPrestamo(idGenerado);
                System.out.println("Préstamo solicitado con éxito. ID generado: " + idGenerado);
                response.sendRedirect("Vistas/Clientes/MenuPrincipal/Prestamos/MenuPrestamos.jsp");
            } else {
                System.out.println("Error: El préstamo no se pudo guardar.");
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

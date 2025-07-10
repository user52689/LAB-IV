package Servlet;
import DAO.PrestamoDAO;
import Modelo.Cliente;
import Modelo.Cuenta;
import Modelo.EstadoPrestamo;
import Modelo.Prestamo;
import Modelo.Usuario;
import Servlet.SessionUtil;
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
            
            // 2. Obtener id_cliente y cliente desde la sesión usando SessionUtil
            int idCliente = SessionUtil.getIdClienteFromSession(request);
            HttpSession session = request.getSession(false); // Ya validado por SessionUtil
            Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
            
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
                
                // CAMBIO AQUÍ: En lugar de redirect, usar forward con mensaje de éxito
                request.setAttribute("mensajeExito", "Préstamo solicitado exitosamente. ID de préstamo: " + idGenerado);
                request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/SolicitarPrestamo.jsp")
                       .forward(request, response);
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
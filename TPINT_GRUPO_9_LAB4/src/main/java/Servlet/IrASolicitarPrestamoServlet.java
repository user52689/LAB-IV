package Servlet;

import Modelo.Cuenta;
import Modelo.Usuario;
import Negocio.CuentaNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/IrASolicitarPrestamoServlet")
public class IrASolicitarPrestamoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;

        if (usuario == null) {
            response.sendRedirect("Vistas/Inicio/Login.jsp");
            return;
        }

        try {
            int idCliente = usuario.getIdUsuario(); // Se asume que coincide con id_cliente
            CuentaNegocio cuentaNegocio = new CuentaNegocio();
            List<Cuenta> cuentas = cuentaNegocio.buscarCuentasPorId(String.valueOf(idCliente));

            request.setAttribute("cuentasCliente", cuentas);
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Prestamos/SolicitarPrestamo.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Error al cargar las cuentas del cliente.");
        }
    }
}

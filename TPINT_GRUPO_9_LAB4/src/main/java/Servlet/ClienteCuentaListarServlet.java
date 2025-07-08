package Servlet;

import Modelo.Cliente;
import Modelo.Cuenta;
import Negocio.CuentaNegocio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ClienteCuentas/listar")
public class ClienteCuentaListarServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio;

    @Override
    public void init() throws ServletException {
        try {
            cuentaNegocio = new CuentaNegocio();
            System.out.println("ClienteCuentaListarServlet: Inicializado CuentaNegocio");
        } catch (SQLException e) {
            throw new ServletException("Error inicializando CuentaNegocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ClienteCuentaListarServlet: Procesando GET");
        try {
            // Obtener cliente desde la sesión
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("clienteLogueado") == null) {
                System.out.println("ClienteCuentaListarServlet: Sesión nula o clienteLogueado no encontrado");
                request.setAttribute("mensajeError", "No hay sesión activa. Por favor, inicie sesión.");
                request.getRequestDispatcher("/Vistas/Inicio/Login.jsp").forward(request, response);
                return;
            }

            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            int idCliente = clienteLogueado.getIdCliente();
            System.out.println("ClienteCuentaListarServlet: idCliente = " + idCliente);

            // Obtener cuentas del cliente
            List<Cuenta> cuentas = cuentaNegocio.listarCuentasPorCliente(idCliente);
            if (cuentas == null || cuentas.isEmpty()) {
                System.out.println("ClienteCuentaListarServlet: No se encontraron cuentas para idCliente = " + idCliente);
                request.setAttribute("mensajeError", "No tenés cuentas registradas.");
            } else {
                System.out.println("ClienteCuentaListarServlet: Cargadas " + cuentas.size() + " cuentas para idCliente = " + idCliente);
            }

            request.setAttribute("cuentas", cuentas);
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Cuentas/ListarCuentas.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ClienteCuentaListarServlet: Error SQL - " + e.getMessage());
            request.setAttribute("mensajeError", "Error al cargar las cuentas: " + e.getMessage());
            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Cuentas/ListarCuentas.jsp")
                   .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
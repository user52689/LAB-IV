package Servlet;

import Modelo.Cliente;
import Modelo.Cuenta;
import Negocio.ClienteNegocio;
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

@WebServlet("/TransferenciaServlet")
public class TransferenciaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteNegocio clienteNegocio;
    private CuentaNegocio cuentaNegocio;

    @Override
    public void init() throws ServletException {
        try {
            clienteNegocio = new ClienteNegocio();
            cuentaNegocio = new CuentaNegocio();
            System.out.println("TransferenciaServlet: Inicializados ClienteNegocio, CuentaNegocio");
        } catch (SQLException e) {
            throw new ServletException("Error inicializando negocios", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("TransferenciaServlet: Procesando GET");
        try {
            // Obtener id_cliente desde la sesión
            HttpSession session = request.getSession(false);
            if (session == null) {
                throw new ServletException("No hay sesión activa.");
            }
            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            if (clienteLogueado == null) {
                System.out.println("TransferenciaServlet: clienteLogueado es null");
                throw new ServletException("No se encontró información de cliente asociada.");
            }
            int idCliente = clienteLogueado.getIdCliente();
            System.out.println("TransferenciaServlet: Sesión obtenida, idCliente = " + idCliente);

            // Obtener cuentas propias del cliente
            List<Cuenta> cuentasPropias = cuentaNegocio.listarCuentasPorCliente(idCliente);
            if (cuentasPropias == null || cuentasPropias.isEmpty()) {
                System.out.println("TransferenciaServlet: No se encontraron cuentas para idCliente = " + idCliente);
                throw new ServletException("No se encontraron cuentas asociadas al cliente.");
            }

            request.setAttribute("cuentasPropias", cuentasPropias);
            System.out.println("TransferenciaServlet: Cargadas " + cuentasPropias.size() + " cuentas propias para idCliente = " + idCliente);
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Cuentas/TransferenciasDinero.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("TransferenciaServlet: Error SQL - " + e.getMessage());
            request.setAttribute("mensajeError", "Error al cargar las cuentas: " + e.getMessage());
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Cuentas/TransferenciasDinero.jsp")
                   .forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            System.out.println("TransferenciaServlet: Error Servlet - " + e.getMessage());
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("Vistas/Inicio/Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("TransferenciaServlet: Procesando POST");
        try {
            // Obtener parámetros
            String idCuentaOrigenStr = request.getParameter("idCuentaOrigen");
            String tipoDestino = request.getParameter("tipoDestino");
            String idCuentaDestinoPropiaStr = request.getParameter("idCuentaDestinoPropia");
            String cbuDestinoExterna = request.getParameter("cbuDestinoExterna");
            String montoStr = request.getParameter("monto");

            // Validar parámetros
            if (idCuentaOrigenStr == null || tipoDestino == null || montoStr == null) {
                throw new IllegalArgumentException("Faltan parámetros requeridos.");
            }

            int idCuentaOrigen;
            double monto;
            try {
                idCuentaOrigen = Integer.parseInt(idCuentaOrigenStr);
                monto = Double.parseDouble(montoStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Formato inválido para ID de cuenta o monto.");
            }

            // Obtener id_cliente y dni desde la sesión
            HttpSession session = request.getSession(false);
            if (session == null) {
                throw new IllegalArgumentException("No hay sesión activa.");
            }
            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            if (clienteLogueado == null) {
                System.out.println("TransferenciaServlet: clienteLogueado es null");
                throw new IllegalArgumentException("No se encontró información de cliente asociada.");
            }
            int idCliente = clienteLogueado.getIdCliente();
            String dni = clienteLogueado.getDni();
            System.out.println("TransferenciaServlet: Sesión obtenida, idCliente = " + idCliente + ", dni = " + dni);

            String cbuDestino = null;
            String detalle = "Transferencia " + ("propia".equals(tipoDestino) ? "entre cuentas propias" : "a cuenta externa");

            // Procesar destino
            if ("propia".equals(tipoDestino)) {
                if (idCuentaDestinoPropiaStr == null || idCuentaDestinoPropiaStr.isEmpty()) {
                    throw new IllegalArgumentException("Debe seleccionar una cuenta destino propia.");
                }
                int idCuentaDestinoPropia;
                try {
                    idCuentaDestinoPropia = Integer.parseInt(idCuentaDestinoPropiaStr);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Formato inválido para ID de cuenta destino.");
                }
                Cuenta cuentaDestino = cuentaNegocio.buscarCuentaPorId(idCuentaDestinoPropia);
                if (cuentaDestino == null || !cuentaDestino.isActivo()) {
                    throw new IllegalArgumentException("La cuenta destino propia no es válida o no está activa.");
                }
                cbuDestino = cuentaDestino.getCbu();
            } else if ("externa".equals(tipoDestino)) {
                if (cbuDestinoExterna == null || !cbuDestinoExterna.matches("\\d{22}")) {
                    throw new IllegalArgumentException("El CBU debe ser un número de 22 dígitos.");
                }
                cbuDestino = cbuDestinoExterna;
            } else {
                throw new IllegalArgumentException("Tipo de destino no válido.");
            }

            // Realizar la transferencia
            boolean exito = cuentaNegocio.transferirDinero(idCuentaOrigen, idCliente, cbuDestino, monto, detalle);

            if (exito) {
                // Actualizar cliente en la sesión
                Cliente clienteActualizado = clienteNegocio.buscarClientePorDniExacto(dni);
                session.setAttribute("clienteLogueado", clienteActualizado);
                request.setAttribute("mensajeExito", "Transferencia realizada exitosamente.");
                System.out.println("TransferenciaServlet: Transferencia exitosa - idCuentaOrigen = " + idCuentaOrigen + 
                                  ", cbuDestino = " + cbuDestino + ", monto = " + monto);
            } else {
                System.out.println("TransferenciaServlet: Falló la transferencia - idCuentaOrigen = " + idCuentaOrigen);
                throw new IllegalArgumentException("No se pudo realizar la transferencia.");
            }

            // Recargar cuentas propias
            List<Cuenta> cuentasPropias = cuentaNegocio.listarCuentasPorCliente(idCliente);
            request.setAttribute("cuentasPropias", cuentasPropias);
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Cuentas/TransferenciasDinero.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("TransferenciaServlet: Error SQL - " + e.getMessage());
            request.setAttribute("mensajeError", "Error al procesar la transferencia: " + e.getMessage());
            try {
                int idCliente = ((Cliente) request.getSession(false).getAttribute("clienteLogueado")).getIdCliente();
                List<Cuenta> cuentasPropias = cuentaNegocio.listarCuentasPorCliente(idCliente);
                request.setAttribute("cuentasPropias", cuentasPropias);
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("TransferenciaServlet: Error al recargar cuentas - " + ex.getMessage());
            }
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Cuentas/TransferenciasDinero.jsp")
                   .forward(request, response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("TransferenciaServlet: Error - " + e.getMessage());
            request.setAttribute("mensajeError", e.getMessage());
            try {
                int idCliente = ((Cliente) request.getSession(false).getAttribute("clienteLogueado")).getIdCliente();
                List<Cuenta> cuentasPropias = cuentaNegocio.listarCuentasPorCliente(idCliente);
                request.setAttribute("cuentasPropias", cuentasPropias);
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("TransferenciaServlet: Error al recargar cuentas - " + ex.getMessage());
            }
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Cuentas/TransferenciasDinero.jsp")
                   .forward(request, response);
        }
    }
}
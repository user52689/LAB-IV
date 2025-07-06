package Servlet;

import Modelo.Cliente;
import Negocio.ClienteNegocio;
import Servlet.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ActualizarPerfilServlet")
public class ActualizarPerfilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClienteNegocio clienteNegocio;

    @Override
    public void init() throws ServletException {
        try {
            clienteNegocio = new ClienteNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error inicializando ClienteNegocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener id_cliente y dni desde la sesion usando SessionUtil
            int idCliente = SessionUtil.getIdClienteFromSession(request);
            HttpSession session = request.getSession(false);
            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            if (clienteLogueado == null) {
                throw new ServletException("No se encontro informacion de cliente asociada.");
            }
            String dni = clienteLogueado.getDni();

            // Obtener datos del cliente
            Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni);
            if (cliente == null) {
                throw new ServletException("No se encontró el cliente asociado.");
            }
            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Perfil/PerfilCliente.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al cargar los datos del cliente: " + e.getMessage());
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Perfil/PerfilCliente.jsp")
                   .forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("Vistas/Inicio/Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener parametros
            String action = request.getParameter("action");
            String field = request.getParameter("field");
            if (action == null || field == null) {
                throw new ServletException("Parámetros action o field no proporcionados.");
            }

            // Obtener id_cliente y dni desde la sesion usando SessionUtil
            int idCliente = SessionUtil.getIdClienteFromSession(request);
            HttpSession session = request.getSession(false);
            if (session == null) {
                throw new ServletException("Sesión no encontrada.");
            }
            Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
            if (clienteLogueado == null) {
                throw new ServletException("No se encontró información de cliente asociada.");
            }
            String dni = clienteLogueado.getDni();

            // Obtener cliente actual
            Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni);
            if (cliente == null) {
                throw new ServletException("No se encontró el cliente asociado.");
            }

            // Procesar solo la accion save
            if (!"save".equals(action)) {
                throw new ServletException("Acción no válida: " + action);
            }

            // Obtener el valor del campo especificado
            String valor = request.getParameter(field);

            boolean actualizar = false;
            String mensajeExito = "";
            String mensajeError = "";

            // Validar y actualizar solo el campo especificado
            if ("correoElectronico".equals(field) && valor != null && !valor.trim().isEmpty()) {
                if (!valor.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    mensajeError = "El Email es inválido.";
                } else if (!valor.equals(cliente.getCorreoElectronico())) {
                    cliente.setCorreoElectronico(valor);
                    actualizar = true;
                    mensajeExito = "Email actualizado exitosamente.";
                }
            } else if ("telefono".equals(field) && valor != null && !valor.trim().isEmpty()) {
                if (!valor.matches("\\d{10}")) {
                    mensajeError = "El teléfono debe ser un número de 10 dígitos.";
                } else if (!valor.equals(cliente.getTelefono())) {
                    cliente.setTelefono(valor);
                    actualizar = true;
                    mensajeExito = "Teléfono actualizado exitosamente.";
                }
            } else if ("direccion".equals(field) && valor != null && !valor.trim().isEmpty()) {
                if (!valor.equals(cliente.getDireccion())) {
                    cliente.setDireccion(valor);
                    actualizar = true;
                    mensajeExito = "Dirección actualizada exitosamente.";
                }
            } else {
                mensajeError = "El campo " + field + " no es válido o está vacío.";
            }

            // Si hay errores de validacion, lanzar excepcion
            if (!mensajeError.isEmpty()) {
                throw new ServletException(mensajeError);
            }

            // Guardar cambios si se actualizo el campo
            if (actualizar) {
                boolean actualizado = clienteNegocio.modificarCliente(cliente);
                if (actualizado) {
                    // Actualizar cliente en la sesión
                    session.setAttribute("clienteLogueado", cliente);
                    request.setAttribute("mensajeExito", mensajeExito);
                } else {
                    throw new ServletException("No se pudo actualizar el perfil.");
                }
            } else {
                request.setAttribute("mensajeError", "No se realizaron cambios en el perfil.");
            }

            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Perfil/PerfilCliente.jsp")
                   .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Error al actualizar el perfil: " + e.getMessage());
            try {
                // Intentar recargar el cliente para el formulario
                int idCliente = SessionUtil.getIdClienteFromSession(request);
                HttpSession session = request.getSession(false);
                Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");
                if (clienteLogueado != null) {
                    Cliente cliente = clienteNegocio.buscarClientePorDniExacto(clienteLogueado.getDni());
                    request.setAttribute("cliente", cliente);
                }
            } catch (SQLException | ServletException ex) {
                ex.printStackTrace();
            }
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Perfil/PerfilCliente.jsp")
                   .forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("Vistas/Clientes/MenuPrincipal/Perfil/PerfilCliente.jsp")
                   .forward(request, response);
        }
    }
}
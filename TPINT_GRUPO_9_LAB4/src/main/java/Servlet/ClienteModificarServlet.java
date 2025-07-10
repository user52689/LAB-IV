package Servlet;

import Modelo.Cliente;
import Negocio.ClienteNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cliente/modificar")
public class ClienteModificarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteNegocio clienteNegocio;

    @Override
    public void init() throws ServletException {
        try {
            clienteNegocio = new ClienteNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar ClienteNegocio", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        try {
            if (dni != null && !dni.isEmpty()) {
                Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni);
                if (cliente == null) {
                    request.setAttribute("mensajeError", "Cliente no encontrado.");
                } else {
                    request.setAttribute("cliente", cliente);
                }
            } 
        } catch (SQLException e) {
            throw new ServletException("Error al buscar cliente", e);
        }
        request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ModificacionCliente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correoElectronico = request.getParameter("correoElectronico");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        if (dni == null || dni.isEmpty() ||
            nombre == null || nombre.isEmpty() ||
            apellido == null || apellido.isEmpty() ||
            correoElectronico == null || correoElectronico.isEmpty()) {
            request.setAttribute("mensajeError", "Datos incompletos o inválidos.");
            doGet(request, response);
            return;
        }

        try {
            Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni);
            if (cliente == null) {
                request.setAttribute("mensajeError", "Cliente no encontrado.");
                request.getRequestDispatcher("/Vistas/Error.jsp").forward(request, response);
                return;
            }

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setCorreoElectronico(correoElectronico);
            cliente.setTelefono(telefono);
            cliente.setDireccion(direccion);

            boolean actualizado = clienteNegocio.modificarCliente(cliente);

            if (actualizado) {
                request.setAttribute("mensajeExito", "Perfil actualizado correctamente.");
            } else {
                request.setAttribute("mensajeError", "No se pudo actualizar el perfil.");
            }

            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ModificacionCliente.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al modificar cliente", e);
        }
    }
}










//package Servlet;
//
//import Modelo.Cliente;
//import Negocio.ClienteNegocio;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/cliente/modificar")
//public class ClienteModificarServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private ClienteNegocio clienteNegocio;
//
//    @Override
//    public void init() throws ServletException {
//        try {
//            clienteNegocio = new ClienteNegocio();
//        } catch (SQLException e) {
//            throw new ServletException("Error al inicializar ClienteNegocio", e);
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String dni = request.getParameter("dni");
//        try {
//            if (dni != null && !dni.isEmpty()) {
//                Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni);
//                if (cliente == null) {
//                    request.setAttribute("mensajeError", "Cliente no encontrado.");
//                } else {
//                    request.setAttribute("cliente", cliente);
//                }
//            }
//        } catch (SQLException e) {
//            throw new ServletException("Error al buscar cliente", e);
//        }
//        request.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Clientes/ModificacionCliente.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        String dni = request.getParameter("dni");
//        String campo = request.getParameter("field");
//
//        if (dni == null || dni.isEmpty()) {
//            request.setAttribute("mensajeError", "DNI es requerido.");
//            doGet(request, response);
//            return;
//        }
//
//        try {
//            Cliente cliente = clienteNegocio.buscarClientePorDniExacto(dni);
//            if (cliente == null) {
//                request.setAttribute("mensajeError", "Cliente no encontrado.");
//                request.getRequestDispatcher("/Vistas/Error.jsp").forward(request, response);
//                return;
//            }
//
//            if ("updatePassword".equals(action) && "contrasena".equals(campo)) {
//                String nuevaContrasena = request.getParameter("nuevaContrasena");
//                String confirmarContrasena = request.getParameter("confirmarContrasena");
//                
//                if (nuevaContrasena == null || confirmarContrasena == null ||
//                    nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty()) {
//                    request.setAttribute("mensajeError", "Debe ingresar y confirmar la nueva contraseña.");
//                } else if (!nuevaContrasena.equals(confirmarContrasena)) {
//                    request.setAttribute("mensajeError", "Las contraseñas no coinciden.");
//                } else if (nuevaContrasena.length() < 6) {
//                    request.setAttribute("mensajeError", "La contraseña debe tener al menos 6 caracteres.");
//                } else {
//                    boolean actualizado = clienteNegocio.resetearContrasena(cliente.getDni(), nuevaContrasena);
//                    if (actualizado) {
//                        request.setAttribute("mensajeExito", "Contraseña actualizada correctamente.");
//                    } else {
//                        request.setAttribute("mensajeError", "No se pudo actualizar la contraseña.");
//                    }
//                }
//            } else {
//                // Actualización de otros campos individuales
//                boolean actualizado = false;
//
//                switch (campo) {
//                    case "correoElectronico":
//                        String correo = request.getParameter("correoElectronico");
//                        if (correo == null || correo.isEmpty()) {
//                            request.setAttribute("mensajeError", "Email es obligatorio.");
//                            break;
//                        }
//                        cliente.setCorreoElectronico(correo);
//                        actualizado = clienteNegocio.modificarCliente(cliente);
//                        if (actualizado)
//                            request.setAttribute("mensajeExito", "Email actualizado correctamente.");
//                        else
//                            request.setAttribute("mensajeError", "No se pudo actualizar el email.");
//                        break;
//
//                    case "telefono":
//                        String telefono = request.getParameter("telefono");
//                        cliente.setTelefono(telefono);
//                        actualizado = clienteNegocio.modificarCliente(cliente);
//                        if (actualizado)
//                            request.setAttribute("mensajeExito", "Teléfono actualizado correctamente.");
//                        else
//                            request.setAttribute("mensajeError", "No se pudo actualizar el teléfono.");
//                        break;
//
//                    case "direccion":
//                        String direccion = request.getParameter("direccion");
//                        cliente.setDireccion(direccion);
//                        actualizado = clienteNegocio.modificarCliente(cliente);
//                        if (actualizado)
//                            request.setAttribute("mensajeExito", "Dirección actualizada correctamente.");
//                        else
//                            request.setAttribute("mensajeError", "No se pudo actualizar la dirección.");
//                        break;
//
//                    default:
//                        request.setAttribute("mensajeError", "Campo no reconocido para actualización.");
//                        break;
//                }
//            }
//
//            request.setAttribute("cliente", cliente);
//            request.getRequestDispatcher("/Vistas/Clientes/MenuPrincipal/Perfil/PerfilCliente.jsp").forward(request, response);
//
//        } catch (SQLException e) {
//            throw new ServletException("Error al modificar cliente", e);
//        }
//    }
//}

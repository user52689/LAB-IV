package Servlet;

import Modelo.Cliente;
import Modelo.Usuario;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {

    /**
     * Obtiene el id_cliente del cliente logueado desde la sesión.
     * para que la implementen
     * 
     * devolver solo id int idCliente = SessionUtil.getIdClienteFromSession(request);
     * 
     * necesitan el cliente entero = Cliente cliente = (Cliente) request.getSession(false).getAttribute("clienteLogueado");

     */
    public static int getIdClienteFromSession(HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            throw new ServletException("Debe iniciar sesión para realizar esta acción.");
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if (!"cliente".equalsIgnoreCase(usuario.getRol())) {
            throw new ServletException("Solo los clientes pueden realizar esta acción.");
        }

        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
        if (cliente == null) {
            throw new ServletException("No se encontró información de cliente asociada.");
        }

        System.out.println("SessionUtils: Obtenido idCliente = " + cliente.getIdCliente() + " para usuario " + usuario.getNombreUsuario());
        return cliente.getIdCliente();
    }
}
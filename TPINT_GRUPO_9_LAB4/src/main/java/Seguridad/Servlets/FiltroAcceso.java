package Seguridad.Servlets;

import Modelo.Usuario;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FiltroAcceso implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws javax.servlet.ServletException {
        System.out.println("FiltroAcceso: Inicializado");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, javax.servlet.ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        System.out.println("FiltroAcceso: requestURI = " + requestURI + ", contextPath = " + contextPath);

        // Permitir acceso a recursos publicos
        if (requestURI.equals(contextPath + "/") ||
            requestURI.endsWith("/Vistas/Inicio/Inicio.jsp") ||
            requestURI.endsWith("/Vistas/Inicio/Login.jsp") || 
            requestURI.endsWith("/Vistas/Inicio/AccesoDenegado.jsp") ||
            requestURI.endsWith("/LoginServlet") || 
            requestURI.endsWith("/LogoutServlet") ||
            requestURI.endsWith(".css") || 
            requestURI.endsWith(".js")) {
            System.out.println("FiltroAcceso: Acceso permitido a recurso público: " + requestURI);
            chain.doFilter(request, response);
            return;
        }

        // Verificar usuario logueado
        Usuario usuarioLogueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;
        if (usuarioLogueado == null) {
            System.out.println("FiltroAcceso: No hay usuario logueado, redirigiendo a Login.jsp");
            httpResponse.sendRedirect(contextPath + "/Vistas/Inicio/Login.jsp");
            return;
        }

        // Verificar acceso segun rol
        String rol = usuarioLogueado.getRol() != null ? usuarioLogueado.getRol().toUpperCase() : "NULL";
        System.out.println("FiltroAcceso: Usuario: " + usuarioLogueado.getNombreUsuario() + ", Rol: " + rol);

        // Verificar acceso usando metodo auxiliar
        if (!tieneAccesoARuta(requestURI, contextPath, rol)) {
            System.out.println("FiltroAcceso: Acceso denegado a " + requestURI + " para rol " + rol);
            httpResponse.sendRedirect(contextPath + "/Vistas/Inicio/AccesoDenegado.jsp");
            return;
        }

        // Establecer nombre de usuario para las vistas
        request.setAttribute("nombreUsuario", usuarioLogueado.getNombreUsuario());
        System.out.println("FiltroAcceso: Acceso permitido para " + usuarioLogueado.getNombreUsuario() + " a " + requestURI);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("FiltroAcceso: Destruido");
    }
    
    private boolean tieneAccesoARuta(String requestURI, String contextPath, String rol) {
        switch (rol) {
            case "ADMIN":
                return requestURI.startsWith(contextPath + "/Vistas/Administrador/MenuPrincipal/") ||
                       requestURI.contains("/usuario/") ||
                       requestURI.endsWith("/cliente/alta") || 
                       requestURI.endsWith("/cliente/baja") ||
                       requestURI.endsWith("/cliente/modificar") ||
                       requestURI.endsWith("/cliente/blanquear") ||
                       requestURI.endsWith("/cliente/listar") ||
                       requestURI.endsWith("/Cuentas/alta") ||
                       requestURI.endsWith("/Cuentas/listar") ||
                       requestURI.endsWith("/Cuentas/baja") ||
                       requestURI.endsWith("/Cuentas/modificar") ||
                       requestURI.endsWith("/Vistas/Administrador/Cuentas/ListarCuentas.jsp") ||
                       requestURI.endsWith("/Vistas/Administrador/Prestamos/PrestamosSolicitadosClientes.jsp") ||
                       requestURI.endsWith("/Vistas/Administrador/MenuPrincipal/reportesMenu.jsp");
                
            case "CLIENTE":
                // Excluir explícitamente las rutas de administración que contienen "cliente"
                if (requestURI.endsWith("/cliente/baja") ||
                    requestURI.endsWith("/cliente/alta") ||
                    requestURI.endsWith("/cliente/modificar") ||
                    requestURI.endsWith("/cliente/blanquear") ||
                    requestURI.endsWith("/cliente/listar")) {
                    return false;  // Estas rutas NO son para clientes
                }
                return requestURI.startsWith(contextPath + "/Vistas/Clientes/MenuPrincipal/") ||
                       requestURI.endsWith("/TransferirServlet") ||
                       requestURI.endsWith("/Vistas/Clientes/Cuentas/ListarCuentas.jsp") ||
                       requestURI.endsWith("/Vistas/Clientes/Perfil/PerfilCliente.jsp") ||
                       requestURI.endsWith("/Vistas/Clientes/Cuentas/TransferenciasDinero.jsp") ||
                       requestURI.endsWith("/Vistas/Clientes/Prestamos/MenuPrestamos.jsp");
                       
            default:
                return false;
        }
    }
}    
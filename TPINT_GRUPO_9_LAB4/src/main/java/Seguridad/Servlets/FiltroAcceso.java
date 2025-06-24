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
    public void init(FilterConfig filterConfig) throws javax.servlet.ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, javax.servlet.ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // Depuración: Imprimir la URI solicitada
        System.out.println("FiltroAcceso: requestURI = " + requestURI);

        // Permitir acceso a recursos públicos
        if (requestURI.equals(contextPath + "/") ||
            requestURI.endsWith("/Vistas/Inicio/login.jsp") || 
            requestURI.endsWith("/Vistas/Inicio/AccesoDenegado.jsp") ||
            requestURI.endsWith("/LoginServlet") || 
            requestURI.endsWith("/LogoutServlet") ||
            requestURI.endsWith(".css") || 
            requestURI.endsWith(".js") ||
            requestURI.endsWith("/Vistas/Inicio/Inicio.jsp")) {
            System.out.println("FiltroAcceso: Acceso permitido a recurso público: " + requestURI);
            chain.doFilter(request, response);
            return;
        }

        // Verificar usuario logueado
        Usuario usuarioLogueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;
        if (usuarioLogueado == null) {
            System.out.println("FiltroAcceso: No hay usuario logueado, redirigiendo a login.jsp");
            httpResponse.sendRedirect(contextPath + "/Vistas/Inicio/login.jsp");
            return;
        }

        // Verificar acceso según rol
        if (requestURI.startsWith(contextPath + "/Vistas/Administrador/") && !usuarioLogueado.esAdministrador()) {
            System.out.println("FiltroAcceso: Acceso denegado a /Vistas/Administrador/ para rol " + usuarioLogueado.getRol());
            httpResponse.sendRedirect(contextPath + "/Vistas/Inicio/AccesoDenegado.jsp");
            return;
        }
        if (requestURI.startsWith(contextPath + "/Vistas/Clientes/") && !usuarioLogueado.esCliente()) {
            System.out.println("FiltroAcceso: Acceso denegado a /Vistas/Clientes/ para rol " + usuarioLogueado.getRol());
            httpResponse.sendRedirect(contextPath + "/Vistas/Inicio/AccesoDenegado.jsp");
            return;
        }

        // Establecer nombre de usuario para las vistas
        request.setAttribute("nombreUsuario", usuarioLogueado.getNombreUsuario());
        System.out.println("FiltroAcceso: Acceso permitido para " + usuarioLogueado.getNombreUsuario() + " a " + requestURI);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
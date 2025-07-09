package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.CuentaNegocio;
import Negocio.TipoCuentaNegocio;
import Modelo.Cuenta;
import Modelo.TipoCuenta;

@WebServlet("/Cuentas/listar")
public class CuentaListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio;
    private TipoCuentaNegocio tipoCuentaNegocio;
    
    @Override
    public void init() throws ServletException {
        try {
        	cuentaNegocio = new CuentaNegocio();
        	tipoCuentaNegocio = new TipoCuentaNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar Negocio",e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nroCuenta = req.getParameter("numeroCuenta");
        String nombreCliente = req.getParameter("nombreCliente");
        String dni = req.getParameter("dni");
        String orden = req.getParameter("orden");
        String tipoCuenta = req.getParameter("tipoCuenta");
        if (nroCuenta == null) nroCuenta = "";
        if (nombreCliente == null) nombreCliente = "";
        if (dni == null) dni = "";
        if (orden == null) orden = "";
        if (tipoCuenta == null) tipoCuenta = "";

        int pagina = 1;
        final int registrosPorPagina = 10;

        String paginaParam = req.getParameter("pagina");
        if (paginaParam != null) {
            try {
                pagina = Integer.parseInt(paginaParam);
            } catch (NumberFormatException e) {
                pagina = 1;
            }
        }

        try {
            List<Cuenta> cuentas = cuentaNegocio.listarRegistros(nroCuenta, nombreCliente, dni, tipoCuenta, orden, pagina, registrosPorPagina);
            List<TipoCuenta> tiposCuentas = tipoCuentaNegocio.listarTiposCuenta();
            int totalRegistros = cuentaNegocio.contarRegistrosActivos(nroCuenta, nombreCliente, dni, tipoCuenta);
            int totalPaginas = (int) Math.ceil((double) totalRegistros / registrosPorPagina);

            req.setAttribute("listaCuentas", cuentas);
            req.setAttribute("listaTiposCuentas", tiposCuentas);
            req.setAttribute("dni", dni);
            req.setAttribute("nombreCliente", nombreCliente);
            req.setAttribute("orden", orden);
            req.setAttribute("tipoCuenta", tipoCuenta);
            req.setAttribute("paginaActual", pagina);
            req.setAttribute("totalPaginas", totalPaginas);
            req.setAttribute("numeroCuenta", nroCuenta);
            
            StringBuilder queryParams = new StringBuilder();

            if (!nroCuenta.isEmpty()) {
                queryParams.append("&numeroCuenta=").append(nroCuenta);
            }
            if (!nombreCliente.isEmpty()) {
                queryParams.append("&nombreCliente=").append(nombreCliente);
            }
            if (!dni.isEmpty()) {
                queryParams.append("&dni=").append(dni);
            }
            if (!tipoCuenta.isEmpty()) {
                queryParams.append("&tipoCuenta=").append(tipoCuenta);
            }
            if (!orden.isEmpty()) {
                queryParams.append("&orden=").append(orden);
            }

            req.setAttribute("queryParams", queryParams.toString());

            req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/ListadoCuenta.jsp").forward(req, res);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "No se pudo obtener la lista de cuentas.");
            req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/ListadoCuenta.jsp").forward(req, res);
        }
    }

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accion = req.getParameter("accion");
	    String nroCuenta = req.getParameter("numeroCuenta");
	    String dni = req.getParameter("dni");
	    String nombreCliente = req.getParameter("nombreCliente");
	    String orden = req.getParameter("orden");
        String tipoCuenta = req.getParameter("tipoCuenta");
	    
	    StringBuilder redirectStr = new StringBuilder("/Cuentas/listar?pagina=1");

	    if ("buscar".equals(accion)) {
	    	if (!nroCuenta.isEmpty()) redirectStr.append("&nroCuenta=").append(nroCuenta);
	    	if (!nombreCliente.isEmpty()) redirectStr.append("&nombreCliente=").append(nombreCliente);
	    	if (!dni.isEmpty()) redirectStr.append("&dni=").append(dni);
	        if (!orden.isEmpty()) redirectStr.append("&orden=").append(orden);
	        if (!tipoCuenta.isEmpty()) redirectStr.append("&tipoCuenta=").append(tipoCuenta);
	    } else if ("todos".equals(accion)) {
	    	nroCuenta = "";
	    }

	    res.sendRedirect(req.getContextPath() + redirectStr);
	    return;
    }

}

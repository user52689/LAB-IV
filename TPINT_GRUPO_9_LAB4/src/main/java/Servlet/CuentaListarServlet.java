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
import Modelo.Cuenta;

@WebServlet("/Cuentas/listar")
public class CuentaListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio;
    
    @Override
    public void init() throws ServletException {
        try {
        	cuentaNegocio = new CuentaNegocio();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String nroCuenta = req.getParameter("numeroCuenta");
	    if (nroCuenta == null) nroCuenta = "";

	    int pagina = 0;
	    int totalPaginas = 0;
	    final int registrosPorPagina = 0;

	    String paginaParam = req.getParameter("pagina");
	    if (paginaParam != null) {
	        try {
	            pagina = Integer.parseInt(paginaParam);
	        } catch (NumberFormatException e) {
	            pagina = 1;
	        }
	    }

	    try {
	        List<Cuenta> cuentas = cuentaNegocio.listarRegistros(nroCuenta, pagina, registrosPorPagina);
	        //int totalCuentas = cuentaNegocio.contarRegistrosActivos(nroCuenta);
	        //int totalPaginas = (int) Math.ceil((double) totalCuentas / registrosPorPagina);

	        req.setAttribute("listaCuentas", cuentas);
	        req.setAttribute("paginaActual", pagina);
	        req.setAttribute("totalPaginas", totalPaginas);
	        req.setAttribute("numeroCuenta", nroCuenta);

	        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/ListadoCuenta.jsp").forward(req, res);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        req.setAttribute("error", "No se pudo obtener la lista de cuentas.");
	        req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/ListadoCuenta.jsp").forward(req, res);
	    }
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accion = req.getParameter("accion");
	    String nroCuenta = "";
	    
	    StringBuilder redirectStr = new StringBuilder("/Cuentas/listar");
	    
	    //StringBuilder redirectStr = new StringBuilder("/Cuentas/listar?pagina=1");

	    if ("buscar".equals(accion)) {
	    	nroCuenta = req.getParameter("numeroCuenta");
	        if (nroCuenta == null) nroCuenta = "";
	    } else if ("todos".equals(accion)) {
	    	nroCuenta = "";
	    }
	    
	    if (nroCuenta != null && !nroCuenta.trim().isEmpty()) {
	    	redirectStr.append("?numeroCuenta="+nroCuenta);
	    }

	    res.sendRedirect(req.getContextPath() + redirectStr);
	    return;
    }

}

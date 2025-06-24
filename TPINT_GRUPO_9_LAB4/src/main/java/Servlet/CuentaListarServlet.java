package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Negocio.CuentaNegocio;
import Modelo.Cuenta;

/**
 * Servlet implementation class CuentaListarServlet
 */
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
		
		if (req.getParameter("mostrarTodos") != null) {
    	    res.sendRedirect(req.getContextPath() + "/Cuentas/listar");
    	    return;
    	}
		
    	String dni = req.getParameter("dni");
        if(dni == null) {
        	dni = "";
        }
		
		List<Cuenta> cuentas = new ArrayList<>();
		int pagina = 1;
	    int tamañoPagina = 10;
	    int totalPaginas = 1;

	    String paginaParam = req.getParameter("pagina");
	    if (paginaParam != null) {
	    	pagina = Integer.parseInt(paginaParam);
	    }

	    try {
			cuentas = cuentaNegocio.listarRegistros(dni, pagina, tamañoPagina);
			int totalCuentas = cuentaNegocio.contarRegistrosActivos(dni);
	        totalPaginas = (int) Math.ceil((double) totalCuentas / tamañoPagina);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    req.setAttribute("listaCuentas", cuentas);
	    req.setAttribute("paginaActual", pagina);
	    req.setAttribute("totalPaginas", totalPaginas);
    	req.setAttribute("dni", dni);
	    req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/ListadoCuenta.jsp").forward(req, res);
		
	}

}

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
@WebServlet("/CuentaListarServlet")
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
		List<Cuenta> cuentas = new ArrayList<>();
		int pagina = 1;
	    int tamañoPagina = 10;
	    int totalPaginas = 1;

	    String paginaParam = req.getParameter("pagina");
	    if (paginaParam != null) {
	        try {
	            pagina = Integer.parseInt(paginaParam);
	        } catch (NumberFormatException ignored) {}
	    }

	    try {
			cuentas = cuentaNegocio.listarCuentasPaginadas(pagina, tamañoPagina);
			int totalCuentas = cuentaNegocio.obtenerCantidadTotalCuentas();
	        totalPaginas = (int) Math.ceil((double) totalCuentas / tamañoPagina);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    req.setAttribute("listaCuentas", cuentas);
	    req.setAttribute("paginaActual", pagina);
	    req.setAttribute("totalPaginas", totalPaginas);
	    req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/ListadoCuenta.jsp").forward(req, res);
		
	}

}

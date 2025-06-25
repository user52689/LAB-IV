package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Cliente;
import Modelo.Cuenta;
import Modelo.TipoCuenta;
import Negocio.ClienteNegocio;
import Negocio.CuentaNegocio;
import Negocio.TipoCuentaNegocio;

/**
 * Servlet implementation class CuentaAltaServlet
 */
@WebServlet("/Cuentas/alta")
public class CuentaAltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio cuentaNegocio;
       
	@Override
    public void init() throws ServletException {
        try {
            cuentaNegocio = new CuentaNegocio();
        } catch (SQLException e) {
            throw new ServletException("Error al inicializar ClienteNegocio", e);
        }
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			TipoCuentaNegocio tcNeg = new TipoCuentaNegocio();
			
			
			req.setAttribute("tiposCuenta", tcNeg.listarTiposCuenta());

            RequestDispatcher rd = req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/AltaCuenta.jsp");
            rd.forward(req, res);
		} catch (SQLException e) {
            throw new ServletException("Error al cargar datos para alta cuenta", e);
        }
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
	        String tipoCuentaStr = req.getParameter("tipoCuenta");

	        int idTipoCuenta = Integer.parseInt(tipoCuentaStr);

	        ClienteNegocio cNeg = new ClienteNegocio();
	        Cliente c = cNeg.buscarClientePorDniExacto(req.getParameter("dniCliente"));

	        TipoCuentaNegocio tcNeg = new TipoCuentaNegocio();
	        TipoCuenta tc = tcNeg.buscarTipoCuentaPorIdExacto(idTipoCuenta);

	        if (c != null) {
	            Cuenta cu = construirCuentaDesdeRequest(req, c, tc);
	            cuentaNegocio.agregarCuenta(cu);
	            res.sendRedirect(req.getContextPath() + "/Cuentas/listar");
	        } else {
	            req.setAttribute("errorDni", "El DNI ingresado no corresponde a ningún cliente activo.");
	            req.setAttribute("tiposCuenta", tcNeg.listarTiposCuenta());

	            RequestDispatcher rd = req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/AltaCuenta.jsp");
	            rd.forward(req, res);
	        }

	    } catch (SQLException e) {

	        if ("45000".equals(e.getSQLState())) {
	        	
	        	TipoCuentaNegocio tcNeg;
				try {
					tcNeg = new TipoCuentaNegocio();
					req.setAttribute("tiposCuenta", tcNeg.listarTiposCuenta());
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
	        	
	            req.setAttribute("errorCuenta", e.getMessage());
	            RequestDispatcher rd = req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/AltaCuenta.jsp");
	            rd.forward(req, res);
	        } else {
	            throw new ServletException("Error inesperado al agregar cuenta", e);
	        }
	    } catch (Exception e) {
	        throw new ServletException("Error al agregar cuenta", e);
	    }
	}
	
	private Cuenta construirCuentaDesdeRequest(HttpServletRequest req, Cliente cliente, TipoCuenta tipoCuenta) {
        Cuenta cu = new Cuenta(); 
        cu.setCliente(cliente);
        cu.setTipoCuenta(tipoCuenta);   
        cu.setSaldo(Double.parseDouble(req.getParameter("saldoInicial")));
        cu.setFechaCreacion(LocalDateTime.now());
        return cu;
    }

}

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
 * Servlet implementation class CuentaModificarServlet
 */
@WebServlet("/Cuentas/modificar")
public class CuentaModificarServlet extends HttpServlet {
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
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nroCuenta = req.getParameter("numeroCuenta");
        if (nroCuenta != null && !nroCuenta.trim().isEmpty()) {
        	try {
                Cuenta cuenta = cuentaNegocio.obtenerCuentaPorNroCuenta(nroCuenta);
                req.setAttribute("cuenta", cuenta);
                req.setAttribute("saldo", cuenta.getSaldo());
                req.setAttribute("numeroCuenta", nroCuenta);

                TipoCuentaNegocio tcNeg = new TipoCuentaNegocio();
                req.setAttribute("tiposCuenta", tcNeg.listarTiposCuenta());

                RequestDispatcher rd = req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/ModificacionCuenta.jsp");
                rd.forward(req, res);
            } catch (SQLException e) {
                throw new ServletException("Error al cargar datos para modificación", e);
            }
        }

        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	try {
    		
            String numeroCuenta = req.getParameter("numeroCuenta");
            int idTipoCuenta = Integer.parseInt(req.getParameter("tipoCuenta"));

            TipoCuentaNegocio tcNeg = new TipoCuentaNegocio();
            TipoCuenta tipoCuenta = tcNeg.buscarTipoCuentaPorIdExacto(idTipoCuenta);

            Cuenta cuenta = new Cuenta();
            cuenta.setNumeroCuenta(numeroCuenta);
            cuenta.setTipoCuenta(tipoCuenta);

            cuentaNegocio.modificarCuenta(cuenta);

            res.sendRedirect(req.getContextPath() + "/Cuentas/listar?pagina=1&numeroCuenta=" + numeroCuenta);
        } catch (Exception e) {
            throw new ServletException("Error al modificar cuenta", e);
        }

    }

}

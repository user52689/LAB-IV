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

import Modelo.Cuenta;
import Negocio.CuentaNegocio;
import Modelo.Cliente;
import Negocio.ClienteNegocio;

@WebServlet("/Cuentas/baja")
public class CuentaBajaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocio cuentaNegocio;
	private ClienteNegocio clienteNegocio;

    @Override
    public void init() throws ServletException {
        try {
        	clienteNegocio = new ClienteNegocio();
        	cuentaNegocio = new CuentaNegocio();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(req.getParameter("btnBuscar") != null) {
    		String dni = req.getParameter("dni");
    		if (dni != null && !dni.trim().isEmpty()) {
    			Cliente cliente = null;
    			List<Cuenta> lista = new ArrayList<>();
    			try {
    				cliente = clienteNegocio.buscarClientePorDniExacto(dni);
    				lista = cuentaNegocio.buscarCuentasPorId(String.valueOf(cliente.getIdCliente()));
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			req.setAttribute("cliente", cliente);
    			req.setAttribute("cuentas", lista);
    		}
    	}
    	req.getRequestDispatcher("/Vistas/Administrador/MenuPrincipal/Cuentas/BajaCuenta.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if(req.getParameter("btnBaja") != null) {
	        String nroCuenta = req.getParameter("numeroCuenta");
	        boolean exito = false;
			try {
				exito = cuentaNegocio.borrarCuentaPorNroCuenta(nroCuenta);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        if (exito) {
	            req.setAttribute("msjExito", "Cuenta eliminada.");
	        } else {
	            req.setAttribute("msjError", "No se pudo eliminar.");
	        }
    	}
	        doGet(req, resp); 
    }
}


package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeguroDao;
import dao.TiposSeguroDao;
import dominio.Seguro;
import dominio.TiposSeguro;

@WebServlet("/ServletSeguro")
public class ServletSeguro extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletSeguro() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsp = "/AgregarSeguro.jsp";
        String debugMessage = "OK";

        // Cargar tipos de seguro
        TiposSeguroDao tiposSeguroDao = new TiposSeguroDao();
        ArrayList<TiposSeguro> listaTiposSeguro = tiposSeguroDao.obtenerTodos();
        if (listaTiposSeguro == null) {
            debugMessage = "Error: No se pudieron cargar los tipos de seguro. Verifique la conexión a la base de datos.";
        }
        request.setAttribute("listaTiposSeguro", listaTiposSeguro);
        request.setAttribute("tiposSeguroCount", listaTiposSeguro != null ? listaTiposSeguro.size() : 0);
        request.setAttribute("debugMessage", debugMessage);

        // Obtener próximo ID de seguro
        SeguroDao seguroDao = new SeguroDao();
        int proximoId = seguroDao.obtenerProximoId();
        request.setAttribute("idSeguro", proximoId > 0 ? proximoId : "-");

        if (request.getParameter("btnAceptar") != null) {
            int filas = 0;
            Seguro seguro = new Seguro();
            try {
                seguro.setDescripcion(request.getParameter("descripcion"));
                seguro.setIdTipo(Integer.parseInt(request.getParameter("tipoSeguro")));
                seguro.setCostoContratacion(Float.parseFloat(request.getParameter("costoContratacion")));
                seguro.setCostoAsegurado(Float.parseFloat(request.getParameter("costoMaximoAsegurado")));
                
                filas = seguroDao.agregarSeguro(seguro);
            } catch (NumberFormatException e) {
                debugMessage = "Error: Formato inválido en los datos ingresados.";
                e.printStackTrace();
            }
            request.setAttribute("cantFilas", filas);
            request.setAttribute("debugMessage", debugMessage);

            
            proximoId = seguroDao.obtenerProximoId();
            request.setAttribute("idSeguro", proximoId > 0 ? proximoId : "-");
        } else if (request.getParameter("Param") != null || request.getParameter("tipoSeleccionado") != null) {
            jsp = "/ListarSeguro.jsp";
            ArrayList<Seguro> listaSeguros;
            
            if (request.getParameter("tipoSeleccionado") != null && !request.getParameter("tipoSeleccionado").isEmpty()) {
                try {
                    int idTipo = Integer.parseInt(request.getParameter("tipoSeleccionado"));
                    listaSeguros = seguroDao.obtenerSegurosPorTipo(idTipo);
                    request.setAttribute("tipoSeleccionado", idTipo);
                } catch (NumberFormatException e) {
                    listaSeguros = seguroDao.obtenerSeguros();
                }
            } else {
                listaSeguros = seguroDao.obtenerSeguros();
            }
            
            request.setAttribute("listaSeguros", listaSeguros);
        }

        RequestDispatcher rd = request.getRequestDispatcher(jsp);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
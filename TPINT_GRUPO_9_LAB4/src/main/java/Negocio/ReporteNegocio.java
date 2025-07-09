package Negocio;

import java.util.Date;

import DAO.ReporteDAO;
import Modelo.ReporteCliente;
import Modelo.ReporteFinanciero;

public class ReporteNegocio {
    private ReporteDAO dao = new ReporteDAO();

    public ReporteFinanciero obtenerReporte(Date desde, Date hasta) {
        return dao.getTotalesPorRangoFechas(desde, hasta);
    }
    public ReporteCliente obtenerReportePorCliente(int idCliente, Date desde, Date hasta) {
        return dao.getTotalesPorClienteYFechas(idCliente, desde, hasta);
    }
}

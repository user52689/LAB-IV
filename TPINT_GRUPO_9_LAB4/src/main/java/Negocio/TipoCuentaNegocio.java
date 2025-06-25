package Negocio;

import java.sql.SQLException;
import java.util.List;

import DAO.TipoCuentaDAO;
import Modelo.TipoCuenta;

public class TipoCuentaNegocio {
	private TipoCuentaDAO dao;

    public TipoCuentaNegocio() throws SQLException{
        this.dao = new TipoCuentaDAO();
    }
    
    public TipoCuenta buscarTipoCuentaPorIdExacto(int id) throws SQLException {
        return dao.obtenerTipoDeCuentaPorId(id);
    }

    public List<TipoCuenta> listarTiposCuenta() throws SQLException {
        return dao.listarTiposCuenta();
    }
}

package Negocio;

import java.sql.SQLException;
import java.util.List;

import DAO.ProvinciaDAO;
import Modelo.Provincia;

public class ProvinciaNegocio {
    public List<Provincia> listarProvincias() throws SQLException {
        ProvinciaDAO dao = new ProvinciaDAO();
        return dao.listarProvincias();
    }
}

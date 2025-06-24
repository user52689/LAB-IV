package Negocio;

import java.sql.SQLException;
import java.util.List;
import DAO.LocalidadDAO;
import Modelo.Localidad;

public class LocalidadNegocio {


    public List<Localidad> listarLocalidades() throws SQLException {
        try {
            LocalidadDAO dao = new LocalidadDAO();  
            return dao.listarLocalidades();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
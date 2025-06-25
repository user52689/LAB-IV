package Negocio;

import java.sql.SQLException;
import java.util.List;

import DAO.NacionalidadDAO;
import Modelo.Pais;
public class NacionalidadNegocio {

	    public List<Pais> listarNacionalidades() throws SQLException {
	        NacionalidadDAO dao = new NacionalidadDAO();
	        return dao.listarNacionalidades();
	    }
	}

package Negocio;

import Modelo.Genero;
import DAO.GeneroDAO;
import java.sql.SQLException;
import java.util.List;

public class GeneroNegocio {
    private GeneroDAO dao;

    public GeneroNegocio() throws SQLException{
        this.dao = new GeneroDAO();
    }

    public List<Genero> listarGeneros() throws SQLException {
        return dao.listarGeneros();
    }
}

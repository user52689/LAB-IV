package DAO;

import Modelo.Genero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {
    private Connection conexion;

    public GeneroDAO() throws SQLException {
        this.conexion = Conexion.obtenerConexion();
    }


    public List<Genero> listarGeneros() throws SQLException {
        List<Genero> generos = new ArrayList<>();
        String sql = "SELECT id_genero, descripcion, activo FROM generos WHERE activo = true";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Genero g = new Genero();
                g.setIdGenero(rs.getInt("id_genero"));    // ojo acá, igual el atributo es idGenero
                g.setDescripcion(rs.getString("descripcion"));
                g.setActivo(rs.getBoolean("activo"));
                generos.add(g);
            }
        }
        return generos;
    }

}


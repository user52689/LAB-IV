package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Pais;
public class NacionalidadDAO {

    public List<Pais> listarNacionalidades() throws SQLException {
        List<Pais> paises = new ArrayList<>();
        String sql = "SELECT idPais, nombre, activo FROM paises WHERE activo = 1";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pais p = new Pais();
                p.setIdPais(rs.getInt("idPais"));
                p.setNombre(rs.getString("nombre"));
                p.setActivo(rs.getBoolean("activo"));
                paises.add(p);
            }
        }

        return paises;
    }
}

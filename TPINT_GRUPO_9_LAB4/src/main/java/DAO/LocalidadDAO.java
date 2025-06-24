package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Localidad;

public class LocalidadDAO {

    public List<Localidad> listarLocalidades() throws SQLException {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT id_localidad, nombre_localidad FROM localidades WHERE activo = 1";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Localidad loc = new Localidad();
                loc.setIdLocalidad(rs.getInt("id_localidad"));
                loc.setNombreLocalidad(rs.getString("nombre_localidad"));
                localidades.add(loc);
            }
        }
        return localidades;
    }
}

package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Modelo.Provincia;

public class ProvinciaDAO {

    public List<Provincia> listarProvinciasActivas() throws SQLException {
        List<Provincia> provincias = new ArrayList<>();
        String query = "SELECT idProvincia, nombreProvincia, activo FROM provincias WHERE activo = 1";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Provincia p = new Provincia();
                p.setIdProvincia(rs.getInt("idProvincia"));
                p.setNombreProvincia(rs.getString("nombreProvincia"));
                p.setActivo(rs.getBoolean("activo"));
                provincias.add(p);
            }
        }

        return provincias;
    }
}

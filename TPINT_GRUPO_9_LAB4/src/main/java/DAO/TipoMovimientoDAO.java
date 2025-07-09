package DAO;

import Modelo.TipoMovimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoMovimientoDAO {
    private Connection conexion;

    public TipoMovimientoDAO() throws SQLException {
        this.conexion = Conexion.obtenerConexion();
    }

    public TipoMovimiento buscarTipoMovimiento(int idTipoMovimiento) throws SQLException {
        String sql = "SELECT * FROM tipos_movimiento WHERE id_tipo_movimiento = ? AND activo = TRUE";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idTipoMovimiento);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TipoMovimiento tm = new TipoMovimiento();
                    tm.setIdTipoMovimiento(rs.getInt("id_tipo_movimiento"));
                    tm.setDescripcion(rs.getString("descripcion"));
                    tm.setActivo(rs.getBoolean("activo"));
                    return tm;
                }
            }
        }
        return null;
    }
}
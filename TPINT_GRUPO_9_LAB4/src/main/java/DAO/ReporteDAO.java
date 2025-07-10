package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Modelo.ReporteCliente;
import Modelo.ReporteFinanciero;

public class ReporteDAO {

    public ReporteFinanciero getTotalesPorRangoFechas(Date desde, Date hasta) {
        ReporteFinanciero reporte = new ReporteFinanciero();

        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = """
                SELECT 
                    SUM(CASE WHEN m.importe > 0 THEN m.importe ELSE 0 END) AS total_ingresos,
                    SUM(CASE WHEN m.importe < 0 THEN -m.importe ELSE 0 END) AS total_egresos
                FROM movimientos m
                WHERE m.fecha_movimiento BETWEEN ? AND ? AND m.activo = TRUE
            """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(desde.getTime()));
            stmt.setDate(2, new java.sql.Date(hasta.getTime()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reporte.setTotalIngresos(rs.getDouble("total_ingresos"));
                reporte.setTotalEgresos(rs.getDouble("total_egresos"));
                reporte.setSaldo(rs.getDouble("total_ingresos") - rs.getDouble("total_egresos"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reporte;
    }

    public ReporteCliente getTotalesPorClienteYFechas(int idCliente, Date desde, Date hasta) {
        ReporteCliente reporte = new ReporteCliente();

        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = """
                SELECT 
                    CONCAT(c.nombre, ' ', c.apellido) AS nombreCliente,
                    SUM(CASE WHEN m.importe > 0 THEN m.importe ELSE 0 END) AS ingresos,
                    SUM(CASE WHEN m.importe < 0 THEN -m.importe ELSE 0 END) AS egresos
                FROM movimientos m
                INNER JOIN cuentas cu ON m.id_cuenta = cu.id_cuenta
                INNER JOIN clientes c ON cu.id_cliente = c.id_cliente
                WHERE c.id_cliente = ? AND m.fecha_movimiento BETWEEN ? AND ? AND m.activo = TRUE
            """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setDate(2, new java.sql.Date(desde.getTime()));
            stmt.setDate(3, new java.sql.Date(hasta.getTime()));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reporte.setNombreCliente(rs.getString("nombreCliente"));
                reporte.setIngresos(rs.getDouble("ingresos"));
                reporte.setEgresos(rs.getDouble("egresos"));
                reporte.setSaldo(rs.getDouble("ingresos") - rs.getDouble("egresos"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reporte;
    }
}

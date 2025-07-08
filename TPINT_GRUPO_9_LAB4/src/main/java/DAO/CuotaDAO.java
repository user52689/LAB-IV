package DAO;

import Modelo.Cuota;
import Modelo.Prestamo;

import java.sql.*;
import java.time.LocalDateTime;

public class CuotaDAO {
    private Connection conexion;
    private PrestamoDAO prestamoDAO;

    public CuotaDAO() throws SQLException {
        this.conexion = Conexion.obtenerConexion();
        this.prestamoDAO = new PrestamoDAO();
    }

    public Cuota obtenerProximaCuotaNoPagada(int idPrestamo) throws SQLException {
        String sql = """
            SELECT c.id_cuota, c.id_prestamo, c.numero_cuota, c.monto_cuota, c.fecha_vencimiento, c.fecha_pago, c.id_cuenta_pago, c.activo
            FROM cuotas c
            WHERE c.id_prestamo = ? AND c.fecha_pago IS NULL AND c.activo = TRUE
            ORDER BY c.numero_cuota ASC
            LIMIT 1
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cuota cuota = new Cuota();
                    cuota.setIdCuota(rs.getInt("id_cuota"));
                    cuota.setNumeroCuota(rs.getInt("numero_cuota"));
                    cuota.setMontoCuota(rs.getDouble("monto_cuota"));
                    cuota.setFechaVencimiento(rs.getDate("fecha_vencimiento").toLocalDate());
                    Timestamp fechaPago = rs.getTimestamp("fecha_pago");
                    if (fechaPago != null) {
                        cuota.setFechaPago(fechaPago.toLocalDateTime());
                    }
                    // Manejar id_cuenta_pago que puede ser NULL
                    int idCuentaPago = rs.getInt("id_cuenta_pago");
                    if (!rs.wasNull()) {
                        cuota.setIdCuentaPago(idCuentaPago);
                    } else {
                        cuota.setIdCuentaPago(null);
                    }
                    cuota.setActivo(rs.getBoolean("activo"));

                    Prestamo prestamo = prestamoDAO.buscarPorId(idPrestamo);
                    cuota.setPrestamo(prestamo);
                    return cuota;
                }
            }
        }
        return null;
    }

    public int obtenerTotalCuotas(int idPrestamo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cuotas WHERE id_prestamo = ? AND activo = TRUE";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public boolean registrarPagoCuota(int idCuota, int idCuentaPago, LocalDateTime fechaPago) throws SQLException {
        String sql = """
            UPDATE cuotas
            SET fecha_pago = ?, id_cuenta_pago = ?
            WHERE id_cuota = ? AND fecha_pago IS NULL AND activo = TRUE
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(fechaPago));
            ps.setInt(2, idCuentaPago);
            ps.setInt(3, idCuota);
            return ps.executeUpdate() > 0;
        }
    }
}
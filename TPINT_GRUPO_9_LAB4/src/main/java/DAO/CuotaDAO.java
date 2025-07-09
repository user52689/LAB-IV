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

                    int idCuentaPago = rs.getInt("id_cuenta_pago");
                    if (!rs.wasNull()) {
                        cuota.setIdCuentaPago(idCuentaPago);
                    } else {
                        cuota.setIdCuentaPago(null);
                    }

                    cuota.setActivo(rs.getBoolean("activo"));

                    Prestamo prestamo = prestamoDAO.buscarPorId(idPrestamo);
                    if (prestamo != null) {
                        double saldoPendiente = calcularSaldoPendiente(idPrestamo);
                        prestamo.setSaldoPendiente(saldoPendiente);
                        cuota.setPrestamo(prestamo);
                        System.out.println("[DEBUG] CuotaDAO: idPrestamo = " + idPrestamo + ", saldoPendiente = " + saldoPendiente);
                    } else {
                        System.out.println("[ERROR] CuotaDAO: Prestamo no encontrado para idPrestamo = " + idPrestamo);
                    }

                    return cuota;
                }
            }
        }
        return null;
    }

    private double calcularSaldoPendiente(int idPrestamo) throws SQLException {
        String sql = """
            SELECT SUM(monto_cuota) as saldo_pendiente
            FROM cuotas
            WHERE id_prestamo = ? AND fecha_pago IS NULL AND activo = TRUE
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double saldoPendiente = rs.getDouble("saldo_pendiente");
                    return rs.wasNull() ? 0.0 : saldoPendiente;
                }
            }
        }
        return 0.0;
    }

    public double obtenerSaldoPendiente(int idPrestamo) throws SQLException {
        return calcularSaldoPendiente(idPrestamo);
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
            int filasActualizadas = ps.executeUpdate();

            // Si se actualizó la cuota, verificar si el préstamo está pagado
            if (filasActualizadas > 0) {
                // Obtener id_prestamo desde la cuota
                String sqlCuota = "SELECT id_prestamo FROM cuotas WHERE id_cuota = ?";
                int idPrestamo;
                try (PreparedStatement psCuota = conexion.prepareStatement(sqlCuota)) {
                    psCuota.setInt(1, idCuota);
                    try (ResultSet rs = psCuota.executeQuery()) {
                        if (rs.next()) {
                            idPrestamo = rs.getInt("id_prestamo");
                        } else {
                            return false;
                        }
                    }
                }

                // Verificar si quedan cuotas pendientes
                double saldoPendiente = calcularSaldoPendiente(idPrestamo);
                if (saldoPendiente == 0) {
                    // Actualizar el estado del préstamo a "Pagado" (id_estado_prestamo = 4)
                    String sqlUpdatePrestamo = "UPDATE prestamos SET id_estado_prestamo = 4 WHERE id_prestamo = ? AND activo = TRUE";
                    try (PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdatePrestamo)) {
                        psUpdate.setInt(1, idPrestamo);
                        psUpdate.executeUpdate();
                        System.out.println("[DEBUG] CuotaDAO: Préstamo id = " + idPrestamo + " actualizado a estado Pagado");
                    }
                }
                return true;
            }
            return false;
        }
    }
}

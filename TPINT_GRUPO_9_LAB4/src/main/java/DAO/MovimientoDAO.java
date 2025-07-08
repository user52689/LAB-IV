package DAO;

import Modelo.Cuenta;
import Modelo.Movimiento;
import Modelo.TipoMovimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO {
    private Connection connection;

    public MovimientoDAO() throws SQLException {
        this.connection = Conexion.obtenerConexion();
    }

    public boolean registrarMovimiento(int idCuenta, int idTipoMovimiento, double importe, String detalle,
                                      double saldoAnterior, double saldoPosterior, Integer idCuentaDestino,
                                      String numeroReferencia) throws SQLException {
        String sql = "INSERT INTO movimientos (id_cuenta, id_tipo_movimiento, importe, detalle, " +
                     "fecha_movimiento, saldo_anterior, saldo_posterior, id_cuenta_destino, numero_referencia, activo) " +
                     "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?, ?, true)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCuenta);
            stmt.setInt(2, idTipoMovimiento);
            stmt.setDouble(3, importe);
            stmt.setString(4, detalle);
            stmt.setDouble(5, saldoAnterior);
            stmt.setDouble(6, saldoPosterior);
            if (idCuentaDestino != null) {
                stmt.setInt(7, idCuentaDestino);
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            stmt.setString(8, numeroReferencia);
            return stmt.executeUpdate() > 0;
        }
    }
    
    private Movimiento mapearMovimiento(ResultSet rs) throws SQLException {
        CuentaDAO cuentaDAO = new CuentaDAO();
        TipoMovimientoDAO tmDAO = new TipoMovimientoDAO();
        Movimiento mov = new Movimiento();
        mov.setIdMovimiento(rs.getInt("id_movimiento"));
        mov.setCuentaOrigen(cuentaDAO.buscarCuentaPorId(rs.getInt("id_cuenta")));
        mov.setTipoMovimiento(tmDAO.buscarTipoMovimiento(rs.getInt("id_tipo_movimiento")));
        mov.setImporte(rs.getDouble("importe"));
        mov.setDetalle(rs.getString("detalle"));
        mov.setFechaMovimiento(rs.getTimestamp("fecha_movimiento").toLocalDateTime());
        mov.setSaldoAnterior(rs.getDouble("saldo_anterior"));
        mov.setSaldoPosterior(rs.getDouble("saldo_posterior"));
        int idCuentaDestino = rs.getInt("id_cuenta_destino");
        if (!rs.wasNull()) {
            mov.setCuentaDestino(cuentaDAO.buscarCuentaPorId(idCuentaDestino));
        }
        mov.setNumeroReferencia(rs.getString("numero_referencia"));
        mov.setActivo(rs.getBoolean("activo"));
        return mov;
    }

    public List<Movimiento> listarMovimientosPorCuenta(int idCuenta) throws SQLException {
        List<Movimiento> movimientos = new ArrayList<>();
        String sql = "SELECT m.*, tm.* " +
                    "FROM movimientos m " +
                    "JOIN tipos_movimiento tm ON m.id_tipo_movimiento = tm.id_tipo_movimiento " +
                    "WHERE m.id_cuenta = ? AND m.activo = TRUE " +
                    "ORDER BY m.fecha_movimiento DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    movimientos.add(mapearMovimiento(rs));
                }
            }
        }
        return movimientos;
    }
}
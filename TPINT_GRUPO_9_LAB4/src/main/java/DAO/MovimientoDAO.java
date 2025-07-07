package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
}
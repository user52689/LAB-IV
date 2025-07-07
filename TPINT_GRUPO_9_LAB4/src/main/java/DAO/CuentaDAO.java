package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import Modelo.Cuenta;
import Modelo.TipoCuenta;

public class CuentaDAO {
    private Connection conexion;

    public CuentaDAO() throws SQLException {
        this.conexion = Conexion.obtenerConexion();
    }

    private Cuenta mapearCuenta(ResultSet rs) throws SQLException {
        ClienteDAO cDAO = new ClienteDAO();
        TipoCuentaDAO tcDAO = new TipoCuentaDAO();
        Cuenta cu = new Cuenta();
        Cliente c = cDAO.mapearCliente(rs);
        TipoCuenta tc = tcDAO.mapearTipoCuenta(rs);
        cu.setIdCuenta(rs.getInt("id_cuenta"));
        cu.setNumeroCuenta(rs.getString("numero_cuenta"));
        cu.setCliente(c);
        cu.setCbu(rs.getString("cbu"));
        cu.setTipoCuenta(tc);
        cu.setSaldo(rs.getDouble("saldo"));
        cu.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
        cu.setActivo(rs.getBoolean("activo"));
        return cu;
    }

    /**
     * Inserta una nueva cuenta para un cliente.
     */
    public boolean agregarCuenta(int idCliente, int idTipoCuenta, double saldo, Timestamp fechaCreacion) throws SQLException {
        String sql = """
            INSERT INTO cuentas (
              numero_cuenta,
              cbu,
              id_cliente,
              id_tipo_cuenta,
              saldo,
              fecha_creacion,
              activo
            ) VALUES (NULL, NULL, ?, ?, ?, ?, TRUE)
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setInt(2, idTipoCuenta);
            ps.setDouble(3, saldo);
            ps.setTimestamp(4, fechaCreacion);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Devuelve un listado paginado de cuentas según DNI de cliente.
     */
    public List<Cuenta> listarRegistros(String nroCuenta, int offset, int limite) throws SQLException {
        List<Cuenta> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT cu.*, c.*, tc.* " +
            "FROM cuentas cu " +
            "JOIN clientes c ON c.id_cliente = cu.id_cliente " +
            "JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta " +
            "WHERE cu.activo = TRUE "
        );
        if (nroCuenta != null && !nroCuenta.trim().isEmpty()) {
            sql.append(" AND cu.numero_cuenta LIKE ? ");
        }
        if(limite > 0) {
        	sql.append(" LIMIT ? OFFSET ?");
        }
        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
            int idx = 1;
            if (nroCuenta != null && !nroCuenta.isEmpty()) {
                ps.setString(idx++, "%" + nroCuenta + "%");
            }
            if (limite > 0) {
                ps.setInt(idx++, limite);
                ps.setInt(idx, offset);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearCuenta(rs));
                }
            }
        } 
        return lista;
    }
    
    public boolean modificarCuenta(Cuenta cu) throws SQLException {
        String sql = "UPDATE cuentas SET id_tipo_cuenta = ? WHERE numero_cuenta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cu.getTipoCuenta().getIdTipoCuenta());
            ps.setString(2, cu.getNumeroCuenta());
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    public List<Cuenta> buscarCuentasPorId(String id) throws SQLException {
        List<Cuenta> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT cu.*, c.*, tc.* " +
            "FROM cuentas cu " +
            "JOIN clientes c ON c.id_cliente = cu.id_cliente " +
            "JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta " +
            "WHERE cu.activo = TRUE AND cu.id_cliente LIKE ? "
        );
        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
            int idx = 1;
            if (id != null && !id.isEmpty()) {
                ps.setString(idx++,id);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearCuenta(rs));
                }
            }
        }
        return lista;
    }
    
    public Cuenta obtenerCuentaPorNroCuenta(String nroCuenta) throws SQLException {
    	String sql = "SELECT cu.*, c.*, tc.* FROM cuentas cu JOIN clientes c ON c.id_cliente = cu.id_cliente JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta WHERE cu.activo = TRUE AND cu.numero_cuenta = ? ";
    	try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nroCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCuenta(rs);
                }
            }
        }
        return null;
    }
    
    public boolean borrarCuentaPorNroCuenta(String nroCuenta) throws SQLException {
    	String sql = "UPDATE cuentas SET activo = false WHERE numero_cuenta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nroCuenta);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }
    
    /**
     * Cuenta cuántas cuentas activas hay (para paginación).
     */
    public int contarRegistrosActivos(String nroCuenta) throws SQLException {
        StringBuilder sql = new StringBuilder(
            "SELECT COUNT(*) FROM cuentas cu " +
            "JOIN clientes c ON cu.id_cliente = c.id_cliente " +
            "WHERE cu.activo = TRUE "
        );
        if (nroCuenta != null && !nroCuenta.trim().isEmpty()) {
            sql.append(" AND cu.numero_cuenta LIKE ? ");
        }
        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
            if (nroCuenta != null && !nroCuenta.isEmpty()) {
                ps.setString(1, "%" + nroCuenta + "%");
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    /**
     * Devuelve el saldo actual de una cuenta.
     */
    public double obtenerSaldo(int idCuenta) throws SQLException {
        String sql = "SELECT saldo FROM cuentas WHERE id_cuenta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("saldo");
                }
            }
        }
        throw new SQLException("Cuenta no encontrada: " + idCuenta);
    }

    /**
     * Suma (o resta si importe negativo) al saldo de la cuenta.
     */

    public boolean actualizarSaldo(int idCuenta, double importe) throws SQLException {
        String sql = "UPDATE cuentas SET saldo = saldo + ? WHERE id_cuenta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDouble(1, importe);
            ps.setInt(2, idCuenta);
            return ps.executeUpdate() > 0;
        }
    }
    
    /*/Metodo alejo 
    public Cuenta obtenerCuentaPorCbu(String cbu) throws SQLException {
    	String sql = "select * from cuentas where cbu = ?";
    	try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cbu);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                	return mapearCuenta(rs);
                }
            }
        }
        throw new SQLException("Cuenta no encontrada: " + cbu);
    }
    */
    
    public Cuenta obtenerCuentaPorCbu(String cbu) throws SQLException {
        String sql = "SELECT cu.*, c.*, tc.* FROM cuentas cu " +
                    "JOIN clientes c ON c.id_cliente = cu.id_cliente " +
                    "JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta " +
                    "WHERE cu.activo = TRUE AND cu.cbu = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cbu);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCuenta(rs);
                }
            }
        }
        throw new SQLException("Cuenta no encontrada: " + cbu);
    }
    
    public List<Cuenta> listarCuentasPorCliente(int idCliente) throws SQLException {
        List<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT cu.*, c.*, tc.* " +
                    "FROM cuentas cu " +
                    "JOIN clientes c ON c.id_cliente = cu.id_cliente " +
                    "JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta " +
                    "WHERE cu.activo = TRUE AND cu.id_cliente = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearCuenta(rs));
                }
            }
        }
        return lista;
    }

    public Cuenta buscarCuentaPorId(int idCuenta) throws SQLException {
        String sql = "SELECT cu.*, c.*, tc.* FROM cuentas cu " +
                    "JOIN clientes c ON c.id_cliente = cu.id_cliente " +
                    "JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta " +
                    "WHERE cu.activo = TRUE AND cu.id_cuenta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCuenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCuenta(rs);
                }
            }
        }
        return null;
    }
}

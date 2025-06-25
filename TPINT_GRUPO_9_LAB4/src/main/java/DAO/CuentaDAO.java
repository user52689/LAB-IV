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
    
    public boolean agregarCuenta(int idCliente, int idTipoCuenta, double saldo, Timestamp fechaCreacion) throws SQLException {
    	String sql = "INSERT INTO cuentas (numero_cuenta, cbu, id_cliente, id_tipo_cuenta, saldo, fecha_creacion, activo) "
    			   + "VALUES (NULL, NULL, ?, ?, ?, ?, TRUE)";
    	
    	try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            ps.setInt(2, idTipoCuenta);
            ps.setDouble(3, saldo);
            ps.setTimestamp(4, fechaCreacion);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    }
    
    public List<Cuenta> listarRegistros(String dni, int offset, int limite) throws SQLException {
    	List<Cuenta> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT c.*, cu.*, tc.* "
        		+ "FROM cuentas cu "
        		+ "JOIN clientes c ON c.id_cliente = cu.id_cliente "
        		+ "JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta "
        		+ "WHERE cu.activo = true ");
        
        if(dni != null && !dni.isEmpty()) {
        	sql.append(" AND c.dni LIKE ? ");
        }
        
        sql.append("LIMIT ? OFFSET ?");
        
        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
        	int index = 1;
        	
        	if(dni != null && !dni.isEmpty()) {
        		ps.setString(index, "%" + dni + "%");
        		index++;
        	}
        	
            ps.setInt(index, limite);
            index++;
            ps.setInt(index, offset);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	Cuenta cuenta = this.mapearCuenta(rs);
                    lista.add(cuenta);
                }
            }
        }
        return lista;
    }
    
    public int contarRegistrosActivos(String dni) throws SQLException {
    	StringBuilder sql = new StringBuilder("SELECT COUNT(*) "
    			+ "FROM cuentas cu "
    			+ "JOIN clientes c ON cu.id_cliente = c.id_cliente "
    			+ "WHERE cu.activo = true ");

        if (dni != null && !dni.isEmpty()) {
            sql.append("AND c.dni LIKE ?");
        }

        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
            int index = 1;

            if (dni != null && !dni.isEmpty()) {
                ps.setString(index, "%" + dni + "%");
            }

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
	
}

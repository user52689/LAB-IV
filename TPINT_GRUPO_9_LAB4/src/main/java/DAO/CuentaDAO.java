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
    
    public List<Cuenta> listarCuentasPaginado(int offset, int limite) throws SQLException {
    	List<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT c.*, cu.*, tc.* "
        		+ "FROM cuentas cu "
        		+ "JOIN clientes c ON c.id_cliente = cu.id_cliente "
        		+ "JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta "
        		+ "WHERE cu.activo = true "
        		+ "LIMIT ? OFFSET ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, limite);
            ps.setInt(2, offset);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cuenta cuenta = mapearCuenta(rs);
                    lista.add(cuenta);
                }
            }
        }
        return lista;
        
    }
    
	public List<Cuenta> listarCuentas() throws SQLException {
        List<Cuenta> lista = new ArrayList<>();
        String sql = "SELECT c.*, cu.*, tc.* "
        		+ "FROM cuentas cu "
        		+ "JOIN clientes c ON c.id_cliente = cu.id_cliente "
        		+ "JOIN tipos_cuenta tc ON cu.id_tipo_cuenta = tc.id_tipo_cuenta "
        		+ "WHERE cu.activo = true";
        try (Statement st = conexion.createStatement();
        	ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
            	Cuenta c = mapearCuenta(rs);
                lista.add(c);
            }
        }
        return lista;
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
	
    public int contarCuentasActivas() throws SQLException {
        String sql = "SELECT COUNT(*) FROM cuentas WHERE activo = true";
        try (Statement st = conexion.createStatement();
        	ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
	
}

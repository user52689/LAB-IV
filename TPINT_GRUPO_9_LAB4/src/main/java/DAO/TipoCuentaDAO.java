package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.TipoCuenta;

public class TipoCuentaDAO {
	private Connection conexion;
	
	public TipoCuentaDAO() throws SQLException {
        this.conexion = Conexion.obtenerConexion();
    }
	
	public List<TipoCuenta> listarTiposCuenta() throws SQLException {
        List<TipoCuenta> generos = new ArrayList<>();
        String sql = "SELECT id_tipo_cuenta, descripcion, activo FROM tipos_cuenta WHERE activo = true";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	TipoCuenta g = new TipoCuenta();
                g.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));    // ojo acá, igual el atributo es idGenero
                g.setDescripcion(rs.getString("descripcion"));
                g.setActivo(rs.getBoolean("activo"));
                generos.add(g);
            }
        }
        return generos;
    }
	
	public TipoCuenta obtenerTipoDeCuentaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tipos_cuenta WHERE id_tipo_cuenta = ? AND activo = TRUE";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearTipoCuenta(rs);
                }
            }
        }
        return null;
    }
	
	public TipoCuenta mapearTipoCuenta(ResultSet rs) throws SQLException {
    	TipoCuenta t = new TipoCuenta();
        t.setIdTipoCuenta(rs.getInt("id_tipo_cuenta"));
        t.setDescripcion(rs.getString("descripcion"));
        t.setActivo(rs.getBoolean("activo"));
        return t;
    }

}

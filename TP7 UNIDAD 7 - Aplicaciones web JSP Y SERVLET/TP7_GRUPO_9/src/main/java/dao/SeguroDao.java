package dao;

import java.sql.*;
import java.util.ArrayList;

import dominio.Seguro;

public class SeguroDao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/segurosgroup?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public int agregarSeguro(Seguro seguro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int filas = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoMaximoAsegurado) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, seguro.getDescripcion());
            stmt.setInt(2, seguro.getIdTipo());
            stmt.setFloat(3, seguro.getCostoContratacion());
            stmt.setFloat(4, seguro.getCostoAsegurado());
            filas = stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return filas;
    }

    public ArrayList<Seguro> obtenerSeguros() {
        ArrayList<Seguro> lista = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT idSeguro, descripcion, idTipo, costoContratacion, costoMaximoAsegurado FROM seguros");

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setId(rs.getInt("idSeguro"));
                seguro.setDescripcion(rs.getString("descripcion"));
                seguro.setIdTipo(rs.getInt("idTipo"));
                seguro.setCostoContratacion(rs.getFloat("costoContratacion"));
                seguro.setCostoAsegurado(rs.getFloat("costoMaximoAsegurado"));
                lista.add(seguro);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return lista;
    }

    public ArrayList<Seguro> obtenerSegurosPorTipo(int idTipo) {
        ArrayList<Seguro> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "SELECT idSeguro, descripcion, idTipo, costoContratacion, costoMaximoAsegurado FROM seguros WHERE idTipo = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idTipo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setId(rs.getInt("idSeguro"));
                seguro.setDescripcion(rs.getString("descripcion"));
                seguro.setIdTipo(rs.getInt("idTipo"));
                seguro.setCostoContratacion(rs.getFloat("costoContratacion"));
                seguro.setCostoAsegurado(rs.getFloat("costoMaximoAsegurado"));
                lista.add(seguro);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return lista;
    }

    public int obtenerProximoId() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int proximoId = -1;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'segurosgroup' AND TABLE_NAME = 'seguros'");
            if (rs.next()) {
                proximoId = rs.getInt("AUTO_INCREMENT");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return proximoId;
    }
}
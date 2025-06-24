package dao;

import java.sql.*;
import java.util.ArrayList;

import dominio.TiposSeguro;

public class TiposSeguroDao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/segurosgroup?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public ArrayList<TiposSeguro> obtenerTodos() {
        ArrayList<TiposSeguro> lista = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Indicar error en la carga del driver
        }

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT idTipo, descripcion FROM tiposeguros");

            while (rs.next()) {
                TiposSeguro ts = new TiposSeguro();
                ts.setId(rs.getInt("idTipo"));
                ts.setDescripcion(rs.getString("descripcion"));
                lista.add(ts);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Indicar error en la consulta
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return lista.isEmpty() ? null : lista; // Retornar null si la lista está vacía
    }
}
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String host = "jdbc:mysql://localhost:3306/";
    private static final String dbName = "banco";
    private static final String user = "root";
    private static final String pass = "root";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver MySQL cargado correctamente");
            return DriverManager.getConnection(host + dbName, user, pass);
        } catch (ClassNotFoundException e) {
        	System.out.println("No se encontró el driver");
            throw new SQLException("No se encontró el driver de MySQL", e);
        }
    }
}

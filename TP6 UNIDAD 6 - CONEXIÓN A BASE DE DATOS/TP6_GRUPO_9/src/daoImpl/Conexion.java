package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	public static Conexion conexion;
	private Connection connection;
	
	private Conexion() {
		try{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdPersonas","root","root");
			this.connection.setAutoCommit(false);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Conexion getConexion() {
		if(conexion == null) {
			conexion = new Conexion();
		}
		return conexion;
	}
	public Connection getSqlConexion() {
		return this.connection;
	}
	public void CerrarConexion() {
		try {
			this.connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		conexion = null;
	}
}

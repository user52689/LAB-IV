package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import dominio.Contratacion;


public class ContratacionDao {

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "segurosgroup";
	
public ArrayList<Contratacion> listarContratacion(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		ArrayList<Contratacion> lista = new ArrayList<Contratacion>();
		Connection conn = null;
	
		
		try {
			conn = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM segurosgroup.contratacion;");
			
		
		  while (rs.next()) {
			  Contratacion contratacion = new Contratacion();		  
			  contratacion.setNombreUsuario(rs.getString("nombreUsuario")); 
			  contratacion.setIdSeguro(rs.getInt("idSeguro"));
			  contratacion.setCostoContratacion(rs.getFloat("costoContratacion"));
              lista.add(contratacion);
          }

      } catch (Exception e) {
          e.printStackTrace();
      }finally {
    	  
      }

      return lista;
		
	}
    
public void agregarContratacion(Contratacion contratacion) {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(host + dbName, user, pass);
        
        String sql = "INSERT INTO contratacion (nombreUsuario, idSeguro, costoContratacion) VALUES (?, ?, ?)";
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
        ps.setString(1, contratacion.getNombreUsuario());
        ps.setInt(2, contratacion.getIdSeguro());
        ps.setFloat(3, contratacion.getCostoContratacion());
        
        ps.executeUpdate();
        
        ps.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
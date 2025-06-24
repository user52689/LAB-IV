package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PersonaDao;
import entidad.Persona;

public class PersonaDaoImpl implements PersonaDao {
	private static final String insertQuery = "INSERT INTO personas(Dni, Nombre, Apellido) VALUES(?, ?, ?)";
	private static final String deleteQuery= "DELETE FROM personas WHERE Dni = ?";
	private static final String updateQuery = "UPDATE personas SET Nombre = ?, Apellido = ? WHERE Dni = ?";
	private static final String readallQuery = "SELECT * FROM personas";
	
	
	@Override
	public boolean insert(Persona persona) {
		PreparedStatement statement;
		Connection cn = Conexion.getConexion().getSqlConexion();
		boolean insert = false;
		
		try {
			statement = cn.prepareStatement(insertQuery);
			statement.setString(1,persona.getDni());
			statement.setString(2,persona.getNombre());
			statement.setString(3,persona.getApellido());
			if(statement.executeUpdate()>0) {
				cn.commit();
				insert = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return insert;
}

	@Override
	public boolean delete(Persona persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSqlConexion();
		boolean delete = false;
		try 
		{
			statement = conexion.prepareStatement(deleteQuery);
			statement.setString(1, persona.getDni());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				delete = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return delete;
	}

	@Override
	public List<Persona> getAll() {
	    PreparedStatement statement;
	    ResultSet resultSet;
	    Connection conexion = Conexion.getConexion().getSqlConexion();
	    List<Persona> personas = new ArrayList<>();
	    
	    try {
	        statement = conexion.prepareStatement(readallQuery);
	        resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	            String dni = resultSet.getString("Dni");
	            String nombre = resultSet.getString("Nombre");
	            String apellido = resultSet.getString("Apellido");
	            
	            Persona p = new Persona(dni, nombre, apellido);
	            personas.add(p);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return personas;
	}
	@Override
	public boolean update(Persona persona) {
	    PreparedStatement statement;
	    Connection conexion = Conexion.getConexion().getSqlConexion();
	    boolean actualizado = false;

	    try {
	        statement = conexion.prepareStatement(updateQuery);
	        statement.setString(1, persona.getNombre());
	        statement.setString(2, persona.getApellido());
	        statement.setString(3, persona.getDni());

	        if (statement.executeUpdate() > 0) {
	            conexion.commit();
	            actualizado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return actualizado;
	}
	@Override
	public boolean existeDni(String dni) {
	    boolean existe = false;
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conexion = Conexion.getConexion().getSqlConexion();
	        statement = conexion.prepareStatement("SELECT COUNT(*) FROM personas WHERE dni = ?");
	        statement.setString(1, dni);
	        resultSet = statement.executeQuery();

	        if (resultSet.next() && resultSet.getInt(1) > 0) {
	            existe = true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return existe;
	}



}

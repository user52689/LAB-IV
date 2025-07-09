package DAO;

import Modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conexion;

    public ClienteDAO() throws SQLException {
        this.conexion = Conexion.obtenerConexion();
    }

    public boolean agregarCliente(Cliente c) throws SQLException {
        String sql = "INSERT INTO clientes (dni, cuil, nombre, apellido, id_genero, id_pais, fecha_nacimiento, direccion, id_localidad, id_provincia, correo_electronico, telefono, usuario, contrasena, fecha_alta, activo)"
        		   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getDni());
            ps.setString(2, c.getCuil());
            ps.setString(3, c.getNombre());
            ps.setString(4, c.getApellido());
            ps.setInt(5, c.getGenero());
            ps.setInt(6, c.getPais());
            ps.setDate(7, Date.valueOf(c.getFechaNacimiento()));
            ps.setString(8, c.getDireccion());
            ps.setInt(9, c.getLocalidad());
            ps.setInt(10, c.getProvincia());
            ps.setString(11, c.getCorreoElectronico());
            ps.setString(12, c.getTelefono());
            ps.setString(13, c.getUsuario());
            ps.setString(14, c.getContrasena());
            ps.setTimestamp(15, Timestamp.valueOf(c.getFechaAlta()));
            ps.setBoolean(16, c.isActivo());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        c.setIdCliente(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE activo = true";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = mapearCliente(rs);
                lista.add(c);
            }
        }
        return lista;
    }
    
    public Cliente obtenerClientePorDni(String dni) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE dni = ? AND activo = true";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        }
        return null;
    }
    
    public boolean modificarCliente(Cliente c) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, correo_electronico = ?, telefono = ?, direccion = ? WHERE dni = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getCorreoElectronico());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getDni());
            
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

       

    public boolean borrarClientePorDni(String dni) throws SQLException {
        String sql = "UPDATE clientes SET activo = false WHERE dni = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    Cliente mapearCliente(ResultSet rs) throws SQLException {
    	Cliente c = new Cliente();
    	c.setIdCliente(rs.getInt("id_cliente"));   
    	c.setDni(rs.getString("dni"));
    	c.setCuil(rs.getString("cuil"));
    	c.setNombre(rs.getString("nombre"));
    	c.setApellido(rs.getString("apellido"));
    	c.setGenero(rs.getInt("id_genero"));
    	c.setPais(rs.getInt("id_pais"));
    	c.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
    	c.setDireccion(rs.getString("direccion"));
    	c.setLocalidad(rs.getInt("id_localidad"));
    	c.setProvincia(rs.getInt("id_provincia"));
    	c.setCorreoElectronico(rs.getString("correo_electronico"));
    	c.setTelefono(rs.getString("telefono"));
    	c.setUsuario(rs.getString("usuario"));
    	c.setContrasena(rs.getString("contrasena"));
    	c.setFechaAlta(rs.getTimestamp("fecha_alta").toLocalDateTime());
    	c.setActivo(rs.getBoolean("activo"));
    	return c;
    }
    
    // Paginacion
    
    public List<Cliente> listarRegistros(String nombreApellido, String dni, String provincia, String ordenApellido, int offset, int limite) throws SQLException {
    	List<Cliente> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM clientes WHERE activo = true ");

        if(nombreApellido != null && !nombreApellido.isEmpty()) {
        	sql.append(" AND (nombre LIKE ? OR apellido LIKE ?) ");
        }
        
        if(dni != null && !dni.isEmpty()) {
        	sql.append(" AND dni LIKE ? ");
        }
        
        if(provincia != null && !provincia.isEmpty()) {
        	sql.append(" AND id_provincia = ? ");
        }
        
        if(ordenApellido.equalsIgnoreCase("asc") || ordenApellido.equalsIgnoreCase("desc")) {
        	sql.append(" ORDER BY apellido ").append(ordenApellido);
        }
        
        sql.append(" LIMIT ? OFFSET ?");
        
        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
        	int index = 1;
        	
        	if(nombreApellido != null && !nombreApellido.isEmpty()) {
        		ps.setString(index++, "%" + nombreApellido + "%");
        		ps.setString(index++, "%" + nombreApellido + "%");
        	}
        	
        	if(dni != null && !dni.isEmpty()) {
        		ps.setString(index++, "%" + dni + "%");
        	}
        	
        	if(provincia != null && !provincia.isEmpty()) {
        		ps.setString(index++, provincia);
        	}
        	
            ps.setInt(index++, limite);
            ps.setInt(index, offset);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                	Cliente cliente = this.mapearCliente(rs);
                    lista.add(cliente);
                }
            }
        }
        return lista;
    }
    
    public int contarRegistrosActivos(String nombreApellido, String dni, String provincia) throws SQLException {
    	StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM clientes WHERE activo = true");

    	if(nombreApellido != null && !nombreApellido.isEmpty()) {
        	sql.append(" AND (nombre LIKE ? OR apellido LIKE ?) ");
        }
    	
        if (dni != null && !dni.isEmpty()) {
            sql.append(" AND dni LIKE ?");
        }
        
        if (provincia != null && !provincia.isEmpty()) {
        	sql.append(" AND id_provincia = ? ");
        }

        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
            int index = 1;

            if(nombreApellido != null && !nombreApellido.isEmpty()) {
        		ps.setString(index++, "%" + nombreApellido + "%");
        		ps.setString(index++, "%" + nombreApellido + "%");
        	}
            
            if (dni != null && !dni.isEmpty()) {
                ps.setString(index++, "%" + dni + "%");
            }
            
            if(provincia != null && !provincia.isEmpty()) {
        		ps.setString(index, provincia);
        	}

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }


    public boolean resetearContrasena(String dni, String contrasenaHash) throws SQLException {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE dni = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, contrasenaHash);
            ps.setString(2, dni);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

}

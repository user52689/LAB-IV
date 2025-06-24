package DAO;

import Modelo.Usuario;
import Seguridad.Seguridad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO() throws SQLException {
        this.conexion = Conexion.obtenerConexion();
    }

    public boolean agregarUsuario(Usuario u) throws SQLException {
        String sql = "INSERT INTO usuarios (" +
                     "dni, nombre_usuario, contrasena, rol, correo_electronico, " +
                     "id_genero, id_pais, id_provincia, id_localidad, " +
                     "fecha_nacimiento, direccion, activo, fecha_creacion, ultimo_acceso) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombreUsuario());
            ps.setString(3, u.getContrasena());
            ps.setString(4, u.getRol());
            ps.setString(5, u.getCorreoElectronico());

            ps.setInt(6, u.getIdGenero());
            ps.setInt(7, u.getIdPais());
            ps.setInt(8, u.getIdProvincia());
            ps.setInt(9, u.getIdLocalidad());

            ps.setDate(10, java.sql.Date.valueOf(u.getFechaNacimiento()));
            ps.setString(11, u.getDireccion());

            ps.setBoolean(12, u.isActivo());
            ps.setTimestamp(13, Timestamp.valueOf(u.getFechaCreacion()));
            
            if (u.getUltimoAcceso() != null) {
                ps.setTimestamp(14, Timestamp.valueOf(u.getUltimoAcceso()));
            } else {
                ps.setTimestamp(14, null);
            }

            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE activo = TRUE";
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = mapearUsuario(rs);
                lista.add(u);
            }
        }
        return lista;
    }

    public Usuario obtenerUsuarioPorDni(String dni) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE dni = ? AND activo = TRUE";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }

    public boolean modificarUsuario(Usuario u) throws SQLException {
        String sql = "UPDATE usuarios SET " +
                     "dni = ?, nombre_usuario = ?, contrasena = ?, rol = ?, correo_electronico = ?, " +
                     "id_genero = ?, id_pais = ?, id_provincia = ?, id_localidad = ?, " +
                     "fecha_nacimiento = ?, direccion = ?, activo = ?, fecha_creacion = ?, ultimo_acceso = ? " +
                     "WHERE id_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombreUsuario());
            ps.setString(3, u.getContrasena());
            ps.setString(4, u.getRol());
            ps.setString(5, u.getCorreoElectronico());

            ps.setInt(6, u.getIdGenero());
            ps.setInt(7, u.getIdPais());
            ps.setInt(8, u.getIdProvincia());
            ps.setInt(9, u.getIdLocalidad());

            ps.setDate(10, java.sql.Date.valueOf(u.getFechaNacimiento()));
            ps.setString(11, u.getDireccion());

            ps.setBoolean(12, u.isActivo());
            ps.setTimestamp(13, Timestamp.valueOf(u.getFechaCreacion()));
            if (u.getUltimoAcceso() != null) {
                ps.setTimestamp(14, Timestamp.valueOf(u.getUltimoAcceso()));
            } else {
                ps.setTimestamp(14, null);
            }
            ps.setInt(15, u.getIdUsuario());

            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    public boolean borrarUsuarioPorDni(String dni) throws SQLException {
        String sql = "UPDATE usuarios SET activo = false WHERE dni = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, dni);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    public boolean resetearContrasena(int idUsuario, String contrasenaHash) throws SQLException {
        String sql = "UPDATE usuarios SET contrasena = ? WHERE id_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, contrasenaHash);
            ps.setInt(2, idUsuario);
            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setDni(rs.getString("dni"));
        u.setNombreUsuario(rs.getString("nombre_usuario"));
        u.setContrasena(rs.getString("contrasena"));
        u.setRol(rs.getString("rol"));
        u.setCorreoElectronico(rs.getString("correo_electronico"));
        u.setIdGenero(rs.getInt("id_genero"));
        u.setIdPais(rs.getInt("id_pais"));
        u.setIdProvincia(rs.getInt("id_provincia"));
        u.setIdLocalidad(rs.getInt("id_localidad"));
        u.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        u.setDireccion(rs.getString("direccion"));
        u.setActivo(rs.getBoolean("activo"));
        u.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
        
        Timestamp ultimoAccesoTs = rs.getTimestamp("ultimo_acceso");
        if (ultimoAccesoTs != null) {
            u.setUltimoAcceso(ultimoAccesoTs.toLocalDateTime());
        }
        return u;
    }
    
    // Metodo para RBAC (login)
    public Usuario obtenerUsuarioPorCredenciales(String nombreUsuario, String contrasena) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND activo = TRUE";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String contrasenaHash = rs.getString("contrasena");
                    if (Seguridad.verifyPassword(contrasena, contrasenaHash)) {
                        return mapearUsuario(rs);
                    }
                }
            }
        }
        return null;
    }
    
    public boolean existeNombreUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT 1 FROM usuarios WHERE nombre_usuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); 
        }
    }

}

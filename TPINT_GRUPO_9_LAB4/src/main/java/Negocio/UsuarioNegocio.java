package Negocio;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioNegocio {

    private UsuarioDAO usuarioDAO;

    public UsuarioNegocio() throws SQLException {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean registrarUsuario(Usuario nuevo) throws Exception {
        if (nuevo.getDni() == null || nuevo.getDni().isEmpty()) {
            throw new Exception("El DNI no puede estar vacío.");
        }

        if (nuevo.getNombreUsuario() == null || nuevo.getNombreUsuario().isEmpty()) {
            throw new Exception("El nombre de usuario no puede estar vacío.");
        }

        if (usuarioDAO.existeNombreUsuario(nuevo.getNombreUsuario())) {
            throw new Exception("Ya existe un usuario con ese nombre.");
        }

        Usuario existente = usuarioDAO.obtenerUsuarioPorDni(nuevo.getDni());
        if (existente != null) {
            throw new Exception("Ya existe un usuario registrado con ese DNI.");
        }

        String hash = BCrypt.hashpw(nuevo.getContrasena(), BCrypt.gensalt());
        nuevo.setContrasena(hash);
        nuevo.setActivo(true);
        nuevo.setFechaCreacion(LocalDateTime.now());

        return usuarioDAO.agregarUsuario(nuevo);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.listarUsuarios();
    }

    public boolean modificarUsuario(Usuario u) throws SQLException {
        return usuarioDAO.modificarUsuario(u);
    }

    public Usuario buscarUsuarioPorDniExacto(String dni) throws SQLException {
        return usuarioDAO.obtenerUsuarioPorDni(dni);
    }

    public boolean borrarUsuarioPorDni(String dni) throws SQLException {
        return usuarioDAO.borrarUsuarioPorDni(dni);
    }

    public void resetearContrasena(int idUsuario, String nuevaContrasenaPlano) throws SQLException {
        String hash = BCrypt.hashpw(nuevaContrasenaPlano, BCrypt.gensalt());
        usuarioDAO.resetearContrasena(idUsuario, hash);
    }
}

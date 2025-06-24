package Negocio;

import DAO.UsuarioDAO;
import Modelo.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioNegocio {

    private UsuarioDAO usuarioDAO;

    public UsuarioNegocio() throws SQLException {
        this.usuarioDAO = new UsuarioDAO();
    }

//    public boolean agregarUsuario(Usuario u) throws SQLException {
//        if (u.getDni() == null || u.getDni().isEmpty()) {
//            throw new IllegalArgumentException("DNI no puede estar vacío");
//        }
//        u.setActivo(true);
//        u.setFecha_creacion(java.time.LocalDateTime.now());
//
//        return usuarioDAO.agregarUsuario(u);
//    }

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

    public void resetearContrasena(int idUsuario, String nuevaContrasenaHash) throws SQLException {
        usuarioDAO.resetearContrasena(idUsuario, nuevaContrasenaHash);
    }
}

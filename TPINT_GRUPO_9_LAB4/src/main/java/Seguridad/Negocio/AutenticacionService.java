package Seguridad.Negocio;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import java.sql.SQLException;

public class AutenticacionService {
    private final UsuarioDAO usuarioDAO;

    public AutenticacionService() throws SQLException {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario login(String nombreUsuario, String contrasena) throws AutenticacionFallidaException, SQLException {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCredenciales(nombreUsuario, contrasena);
        if (usuario == null) {
            throw new AutenticacionFallidaException("Usuario o contraseña incorrectos.");
        }
        return usuario;
    }
}
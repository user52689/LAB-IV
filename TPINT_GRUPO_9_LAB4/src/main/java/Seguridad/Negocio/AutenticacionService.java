package Seguridad.Negocio;

import DAO.ClienteDAO;
import DAO.UsuarioDAO;
import Modelo.Cliente;
import Modelo.Usuario;
import java.sql.SQLException;

public class AutenticacionService {
    private final UsuarioDAO usuarioDAO;
    private final ClienteDAO clienteDao;

    public AutenticacionService() throws SQLException {
        this.usuarioDAO = new UsuarioDAO();
        this.clienteDao = new ClienteDAO();
    }

    public AuthResponse login(String nombreUsuario, String contrasena) throws AutenticacionFallidaException, SQLException {
        // Autenticar usuario
        Usuario usuario = usuarioDAO.obtenerUsuarioPorCredenciales(nombreUsuario, contrasena);
        if (usuario == null) {
            throw new AutenticacionFallidaException("Usuario o contraseña incorrectos.");
        }

        // Buscar cliente si el rol es 'cliente'
        Cliente cliente = null;
        if ("cliente".equalsIgnoreCase(usuario.getRol())) {
            cliente = clienteDao.obtenerClientePorDni(usuario.getDni());
            if (cliente == null) {
                System.out.println("AutenticacionService: No se encontró cliente para dni " + usuario.getDni());
            }
        }

        return new AuthResponse(usuario, cliente);
    }
}
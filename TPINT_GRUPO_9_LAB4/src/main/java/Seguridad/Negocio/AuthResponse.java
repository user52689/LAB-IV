package Seguridad.Negocio;

import Modelo.Usuario;
import Modelo.Cliente;

public class AuthResponse {
    private final Usuario usuario;
    private final Cliente cliente;

    public AuthResponse(Usuario usuario, Cliente cliente) {
        this.usuario = usuario;
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
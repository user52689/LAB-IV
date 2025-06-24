package Ejercicio1;

import java.io.IOException;

public class DniInvalido extends IOException {
	
    public DniInvalido() {
        super("El DNI ingresado es inválido, solo debe contener números.");
    }
    
    public DniInvalido(String mensaje) {
        super(mensaje);
    }
}
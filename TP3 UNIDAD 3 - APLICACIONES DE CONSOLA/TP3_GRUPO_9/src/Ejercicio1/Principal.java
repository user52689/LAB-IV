package Ejercicio1;

import java.util.TreeSet;

public class Principal {

	public static void main(String[] args) {
		Archivo archivo = new Archivo();
		archivo.setRuta("Personas.txt");
		TreeSet<Persona> personas = archivo.leer();
		archivo.escribirResultado(personas);
	}

}

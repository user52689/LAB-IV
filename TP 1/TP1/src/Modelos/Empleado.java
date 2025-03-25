package Modelos;

public class Empleado {

	private static int contadorID = 1000;
	private final int id;
	private String nombre;
	private int edad;	
}

    // Constructor
    public Empleado(int id, String nombre, int edad) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
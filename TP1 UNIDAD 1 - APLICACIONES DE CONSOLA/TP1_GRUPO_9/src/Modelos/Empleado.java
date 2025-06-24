package Modelos;

public class Empleado {

	//Atributos
	private static int contadorID = 1000;
	private final int id;
	private String nombre;
	private int edad;	
	
	//Metodos
	public static int devolverProxID() {
		return contadorID;
	}	

    // Constructor
	public Empleado() {
    this.id = contadorID++;
    this.nombre = "sin nombre";
    this.edad = 99;
}
    public Empleado(String nombre, int edad) {
        this.id = contadorID++;
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
   // public void setId(int id) {
   //     this.id = id;
    //}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    // Metodo toString
	@Override
	public String toString() {
		return "ID=" + id + ", Nombre=" + nombre + ", Edad=" + edad;
	}
}
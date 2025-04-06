package ejercicio1;

public class Empleado {

	private static int contadorID = 1000;
	private final int id;
	private String nombre;
	private int edad;	
	
	

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

    
    public static int devolverProxID() {
    	return contadorID;
    }	

    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
	@Override
	public String toString() {
		return "ID=" + id + ", Nombre=" + nombre + ", Edad=" + edad;
	}
}

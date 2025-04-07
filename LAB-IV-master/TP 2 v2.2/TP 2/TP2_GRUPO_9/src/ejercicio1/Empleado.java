package ejercicio1;

import java.util.Objects;

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
	@Override
	public int hashCode() {
		return Objects.hash(edad, nombre);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return edad == other.edad && Objects.equals(nombre, other.nombre);
	}
	
	
}

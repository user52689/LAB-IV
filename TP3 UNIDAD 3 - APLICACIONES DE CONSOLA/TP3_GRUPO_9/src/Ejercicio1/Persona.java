package Ejercicio1;

import java.util.Objects;

public class Persona implements Comparable<Persona>{
    private String nombre;
    private String apellido;
    private String dni;
    
    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

	public static boolean VerificarDniInvalido(String dni) throws DniInvalido {
	    boolean dniValido = true;
	
	
	    if (dni == null || dni.trim().isEmpty()) {
	        throw new DniInvalido();
	    }
	
	    for (int i = 0; i < dni.length(); i++) {
	        if (!Character.isDigit(dni.charAt(i))) {
	            dniValido = false;
	        }
	    }
	
	    if (dniValido == false) {
	        throw new DniInvalido();
	    }
	
	    return dniValido;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(dni, other.dni)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public int compareTo(Persona o) {
		return this.apellido.compareToIgnoreCase(((Persona) o).getApellido());
	}

	@Override
	public String toString() {
		return nombre + "-" + apellido + "-" + dni;
	}
}

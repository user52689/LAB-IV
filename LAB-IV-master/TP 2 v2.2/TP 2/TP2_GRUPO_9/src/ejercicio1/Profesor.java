package ejercicio1;

import java.util.Objects;

public class Profesor extends Empleado implements Comparable<Profesor>{

	private String cargo;
	private int antiguedadDocente;

// Constructores

	public Profesor() {
		super();
		this.cargo = "Sin cargo";
		this.antiguedadDocente = 0;
	}
	
	public Profesor(String nombre, int edad, String cargo,int antiguedadDocente) {
    	super(nombre,edad);
    	this.cargo = cargo;
    	this.antiguedadDocente = antiguedadDocente;
    }
	
// Setters y Getters
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	public void setAntiguedad(int antiguedad) {
		this.antiguedadDocente = antiguedad;
	}
	
	public String getCargo() {
		return this.cargo;
	}
	public int getAntiguedad() {
		return this.antiguedadDocente;
	}
	
	@Override
	public String toString() {
		return "Profesor [cargo=" + cargo + ", antiguedadDocente=" + antiguedadDocente + "]";
	}

	@Override
	public int compareTo(Profesor o) {
		return Integer.compare(this.getEdad(), o.getEdad());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(antiguedadDocente, cargo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return antiguedadDocente == other.antiguedadDocente && Objects.equals(cargo, other.cargo);
	}

		
	
}

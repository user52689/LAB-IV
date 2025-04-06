package ejercicio1;

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
		return "Profesor "
				+ "[id=" + getId() + 
				", nombre=" + getNombre() + 
				", edad=" + getEdad() + 
				", cargo=" + cargo + 
				", antiguedadDocente=" + antiguedadDocente + 
				"]";
	}

	@Override
	public int compareTo(Profesor o) {
		return Integer.compare(this.getEdad(), o.getEdad());
	}
}

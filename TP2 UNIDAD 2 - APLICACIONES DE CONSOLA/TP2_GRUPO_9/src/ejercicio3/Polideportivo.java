package ejercicio3;

public class Polideportivo implements IEdificio, IInstalacionDeportiva {
	private double superficie;
	private String nombre;
	private int tipoInstalacion;
	
	public Polideportivo() {
		this.superficie = 0.0;
		this.nombre = "SIN DATO";
		this.tipoInstalacion = 0;
	}
	
	public Polideportivo(double superficie, String nombre, int tipoInstalacion) {
		this.superficie = superficie;
		this.nombre = nombre;
		this.tipoInstalacion = tipoInstalacion;
	}
	
	public String getNombre() {
        return nombre;
    }
	
	@Override
	public int getTipoDeInstalacion() {
		return this.tipoInstalacion;
	}
	@Override
	public double getSuperficieEdificio() {
		return this.superficie;
	}
	
	@Override
    public String toString() {
        return "Polideportivo "
        	   + "[nombre=" + nombre 
               + ", superficie=" + superficie 
               + ", tipoInstalacion=" + tipoInstalacion + "]";
    }
}

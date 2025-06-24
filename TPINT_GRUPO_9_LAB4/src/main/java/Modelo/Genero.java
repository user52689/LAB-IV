package Modelo;

public class Genero {
    private int idGenero;
    private String descripcion;
    private boolean activo;
    
    public Genero() {}
    
	public Genero(int idGenero, String descripcion, boolean activo) {
		super();
		this.idGenero = idGenero;
		this.descripcion = descripcion;
		this.activo = activo;
	}
	
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
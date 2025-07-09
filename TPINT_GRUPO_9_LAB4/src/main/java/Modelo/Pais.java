package Modelo;

public class Pais {
    private int idPais;
    private String nombre;
    private boolean activo;
    
    public Pais() {}
    
	public Pais(int idPais, String nombre, boolean activo) {
		super();
		this.idPais = idPais;
		this.nombre = nombre;
		this.activo = activo;
	}
	
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

    
}


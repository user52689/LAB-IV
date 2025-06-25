package Modelo;

public class Provincia {
    private int idProvincia;
    private String nombreProvincia;
    private boolean activo;
    
    public Provincia() {}
    
	public Provincia(int idProvincia, String nombreProvincia, boolean activo) {
		super();
		this.idProvincia = idProvincia;
		this.nombreProvincia = nombreProvincia;
		this.activo = activo;
	}
	
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}    
}


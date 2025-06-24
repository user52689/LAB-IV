package Modelo;

public class Localidad {
    private int idLocalidad;
    private String nombreLocalidad;
    private Provincia provincia;
    private boolean activo;
    
    public Localidad() {}
    
	public Localidad(int idLocalidad, String nombreLocalidad, Provincia provincia, boolean activo) {
		super();
		this.idLocalidad = idLocalidad;
		this.nombreLocalidad = nombreLocalidad;
		this.provincia = provincia;
		this.activo = activo;
	}
	
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}
	public void setNombreLocalidad(String nombreLocalidad) {
		this.nombreLocalidad = nombreLocalidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}


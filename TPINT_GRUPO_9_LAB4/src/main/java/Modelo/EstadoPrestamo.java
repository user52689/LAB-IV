package Modelo;

public class EstadoPrestamo {
    private int idEstadoPrestamo;
    private String descripcion;
    private boolean activo;
    
    public EstadoPrestamo() {}
    
	public EstadoPrestamo(int idEstadoPrestamo, String descripcion, boolean activo) {
		super();
		this.idEstadoPrestamo = idEstadoPrestamo;
		this.descripcion = descripcion;
		this.activo = activo;
	}
	
	public int getIdEstadoPrestamo() {
		return idEstadoPrestamo;
	}
	public void setIdEstadoPrestamo(int idEstadoPrestamo) {
		this.idEstadoPrestamo = idEstadoPrestamo;
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

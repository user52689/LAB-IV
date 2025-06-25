package Modelo;

public class TipoMovimiento {
    private int idTipoMovimiento;
    private String descripcion;
    private boolean activo;
    
    public TipoMovimiento() {}
    
	public TipoMovimiento(int idTipoMovimiento, String descripcion, boolean activo) {
		super();
		this.idTipoMovimiento = idTipoMovimiento;
		this.descripcion = descripcion;
		this.activo = activo;
	}
	
	public int getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(int idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
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


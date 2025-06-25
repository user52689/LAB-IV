package Modelo;

public class TipoCuenta {
    private int idTipoCuenta;
    private String descripcion;
    private boolean activo;
    
    public TipoCuenta() {}
    
	public TipoCuenta(int idTipoCuenta, String descripcion, boolean activo) {
		super();
		this.idTipoCuenta = idTipoCuenta;
		this.descripcion = descripcion;
		this.activo = activo;
	}
	
	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}
	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
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


package Modelo;

import java.time.LocalDateTime;

public class Movimiento {
    private int idMovimiento;
    private Cuenta cuentaOrigen;
    private TipoMovimiento tipoMovimiento;
    private double importe;
    private String detalle;
    private LocalDateTime fechaMovimiento;
    private double saldoAnterior;
    private double saldoPosterior;
    private Cuenta cuentaDestino;
    private String numeroReferencia;
    private boolean activo;
    
    public Movimiento() {}
    
	public Movimiento(int idMovimiento, Cuenta cuentaOrigen, TipoMovimiento tipoMovimiento, double importe,
			String detalle, LocalDateTime fechaMovimiento, double saldoAnterior, double saldoPosterior,
			Cuenta cuentaDestino, String numeroReferencia, boolean activo) {
		super();
		this.idMovimiento = idMovimiento;
		this.cuentaOrigen = cuentaOrigen;
		this.tipoMovimiento = tipoMovimiento;
		this.importe = importe;
		this.detalle = detalle;
		this.fechaMovimiento = fechaMovimiento;
		this.saldoAnterior = saldoAnterior;
		this.saldoPosterior = saldoPosterior;
		this.cuentaDestino = cuentaDestino;
		this.numeroReferencia = numeroReferencia;
		this.activo = activo;
	}
	
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public LocalDateTime getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public double getSaldoAnterior() {
		return saldoAnterior;
	}
	public void setSaldoAnterior(double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	public double getSaldoPosterior() {
		return saldoPosterior;
	}
	public void setSaldoPosterior(double saldoPosterior) {
		this.saldoPosterior = saldoPosterior;
	}
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

    
}


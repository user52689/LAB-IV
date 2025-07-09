package Modelo;

import java.time.LocalDateTime;

public class Cuenta {
    private int idCuenta;
    private String numeroCuenta;
    private String cbu;
    private Cliente cliente;
    private TipoCuenta tipoCuenta;
    private double saldo;
    private LocalDateTime fechaCreacion;
    private boolean activo;
    
    

	public Cuenta() {}

	public Cuenta(int idCuenta, String numeroCuenta, String cbu, Cliente cliente, TipoCuenta tipoCuenta, double saldo,
			LocalDateTime fechaCreacion, boolean activo) {
		super();
		this.idCuenta = idCuenta;
		this.numeroCuenta = numeroCuenta;
		this.cbu = cbu;
		this.cliente = cliente;
		this.tipoCuenta = tipoCuenta;
		this.saldo = saldo;
		this.fechaCreacion = fechaCreacion;
		this.activo = activo;
	}
	
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

    
}


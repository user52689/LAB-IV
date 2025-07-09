package Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cuota {
    private int idCuota;
    private Prestamo prestamo;
    private int numeroCuota;
    private double montoCuota;
    private LocalDate fechaVencimiento;
    private LocalDateTime fechaPago;
    private Cuenta cuentaPago;
    private Integer idCuentaPago;
    private boolean activo;
    
    public Cuota() {}
    
	public Cuota(int idCuota, Prestamo prestamo, int numeroCuota, double montoCuota, LocalDate fechaVencimiento,
			LocalDateTime fechaPago, Cuenta cuentaPago, boolean activo) {
		super();
		this.idCuota = idCuota;
		this.prestamo = prestamo;
		this.numeroCuota = numeroCuota;
		this.montoCuota = montoCuota;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaPago = fechaPago;
		this.cuentaPago = cuentaPago;
		this.activo = activo;
	}
	
	public int getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	public int getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public LocalDateTime getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(LocalDateTime fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Cuenta getCuentaPago() {
		return cuentaPago;
	}
	public void setCuentaPago(Cuenta cuentaPago) {
		this.cuentaPago = cuentaPago;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	 public Integer getIdCuentaPago() {
	        return idCuentaPago;
	}

	 public void setIdCuentaPago(Integer idCuentaPago) {
	        this.idCuentaPago = idCuentaPago;
	 }

    
}


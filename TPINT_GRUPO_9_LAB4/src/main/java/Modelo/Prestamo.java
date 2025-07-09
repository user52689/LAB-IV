package Modelo;

import java.time.LocalDateTime;

public class Prestamo {
    private int idPrestamo;
    private Cliente cliente;
    private Cuenta cuentaDeposito;
    private double importeSolicitado;
    private double importeTotal;
    private int plazoMeses;
    private double montoCuota;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaResolucion;
    private EstadoPrestamo estadoPrestamo;
    private String observaciones;
    private boolean activo;
    private double saldoPendiente;
    
    public Prestamo() {}
    
    public Prestamo(int idPrestamo, Cliente cliente, Cuenta cuentaDeposito, double importeSolicitado,
            double importeTotal, int plazoMeses, double montoCuota, LocalDateTime fechaSolicitud,
            LocalDateTime fechaResolucion, EstadoPrestamo estadoPrestamo, String observaciones,
            boolean activo, double saldoPendiente) {
super();
this.idPrestamo = idPrestamo;
this.cliente = cliente;
this.cuentaDeposito = cuentaDeposito;
this.importeSolicitado = importeSolicitado;
this.importeTotal = importeTotal;
this.plazoMeses = plazoMeses;
this.montoCuota = montoCuota;
this.fechaSolicitud = fechaSolicitud;
this.fechaResolucion = fechaResolucion;
this.estadoPrestamo = estadoPrestamo;
this.observaciones = observaciones;
this.activo = activo;
this.saldoPendiente = saldoPendiente;
}
	
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Cuenta getCuentaDeposito() {
		return cuentaDeposito;
	}
	public void setCuentaDeposito(Cuenta cuentaDeposito) {
		this.cuentaDeposito = cuentaDeposito;
	}
	public double getImporteSolicitado() {
		return importeSolicitado;
	}
	public void setImporteSolicitado(double importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}
	public double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}
	public int getPlazoMeses() {
		return plazoMeses;
	}
	public void setPlazoMeses(int plazoMeses) {
		this.plazoMeses = plazoMeses;
	}
	public double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public LocalDateTime getFechaResolucion() {
		return fechaResolucion;
	}
	public void setFechaResolucion(LocalDateTime fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}
	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}
	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public double getSaldoPendiente() {
	    return saldoPendiente;
	}

	public void setSaldoPendiente(double saldoPendiente) {
	    this.saldoPendiente = saldoPendiente;
	}

    
}


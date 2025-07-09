package Modelo;

public class ReporteCliente {
    private String nombreCliente;
    private double ingresos;
    private double egresos;
    private double saldo;

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public double getIngresos() { return ingresos; }
    public void setIngresos(double ingresos) { this.ingresos = ingresos; }

    public double getEgresos() { return egresos; }
    public void setEgresos(double egresos) { this.egresos = egresos; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}

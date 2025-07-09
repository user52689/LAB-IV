package Modelo;

public class ReporteFinanciero {
    private double totalIngresos;
    private double totalEgresos;
    private double saldo;

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public double getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(double totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

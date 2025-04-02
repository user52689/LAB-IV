package Main;
import Modelos.Empleado;

public class main {
    public static void main(String[] args) {

        Empleado[] empleados = new Empleado[7];

        // Inicializar empleados
        empleados[0] = new Empleado();
        empleados[1] = new Empleado("OSCAR", 25);
        empleados[2] = new Empleado("ISAIAS", 19);
        empleados[3] = new Empleado();
        empleados[4] = new Empleado("LUCIANO", 34);
        empleados[5] = new Empleado("ALEJO", 20);
        empleados[6] = new Empleado("ABRAHAM", 38);

        for (Empleado emp : empleados) {
            System.out.println(emp);
        }

        // Proximo ID disponible
        System.out.println(" El proximo ID sera el " + Empleado.devolverProxID());
    }
}
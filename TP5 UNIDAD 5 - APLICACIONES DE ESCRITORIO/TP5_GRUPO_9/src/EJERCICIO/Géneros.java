package EJERCICIO;

import java.util.Arrays;
import java.util.List;

public class Géneros {
    private String nombre;
    
    public Géneros(String nombre) {
        this.nombre = nombre; }
   
    public String getNombre() {
        return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;}

    public static List<String> getListaGeneros() {
        return Arrays.asList(
            "Seleccione un género",
            "Terror",
            "Acción",
            "Suspenso",
            "Romántica"
        );
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
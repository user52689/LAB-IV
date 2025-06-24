package EJERCICIO;

public class Peliculas {
    private static int contador = 0;
    private int ID;
    private String nombre;
    private Géneros genero;
    
    public Peliculas() {
        contador++;
        this.ID = contador;
        this.nombre = "NULL";
        this.genero = new Géneros("NULL");
    }
    
    public Peliculas(String nombre, Géneros genero) {
        contador++;
        this.ID = contador;
        this.nombre = nombre;
        this.genero = genero;
    }

    public static int getContador() {
        return contador;
    }
    
    public int getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Géneros getGenero() {
        return genero;
    }

    public void setGenero(Géneros genero) {
        this.genero = genero;
    }

	@Override
	public String toString() {
		return nombre  + ", " + genero + ", " + ID;
	}    
}

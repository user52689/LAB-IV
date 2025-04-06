package ejercicio1;

import java.util.Iterator;
import java.util.TreeSet;

public class mainEjercicio1_b {

	public static void main(String[] args) {
		TreeSet<Profesor> profesores = new TreeSet<>();
		
		profesores.add(new Profesor("Oscar", 30, "Titular", 1));
        profesores.add(new Profesor("Isaias", 19, "Jefe de Catedra", 20));
        profesores.add(new Profesor("Alejo", 35, "Suplente", 10));
        profesores.add(new Profesor("Luciano", 32, "Consultor", 7));
        profesores.add(new Profesor("Abraham", 45, "Ayudante", 18));
        
        
        Iterator<Profesor> it = profesores.iterator();
        while (it.hasNext()) {
        	Profesor profesor = it.next();
            System.out.println(profesor.toString());
        }

	}

}

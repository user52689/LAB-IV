package ejercicio1;

import java.util.ArrayList;
import java.util.Iterator;

public class mainEjercicio1_a {

	public static void main(String[] args) {
		ArrayList<Profesor> profesores = new ArrayList<>();
		
		profesores.add(new Profesor("Oscar", 30, "Titular", 15));
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


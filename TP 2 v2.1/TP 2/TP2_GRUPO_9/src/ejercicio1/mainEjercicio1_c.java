package ejercicio1;

public class mainEjercicio1_c {

	public static void main(String[] args) {
		Profesor profesor_a = new Profesor("Carlos",28,"Titular",3); 
		Profesor profesor_b = new Profesor("Carlos",28,"Titular",3); 
		
		if (profesor_a.equals(profesor_b)) {
			System.out.println("Es el mismo profesor");
		} else {
			System.out.println("No es el mismo profesor");
		}
	}

}

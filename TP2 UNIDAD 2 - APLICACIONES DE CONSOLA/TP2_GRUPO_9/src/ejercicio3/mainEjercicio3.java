package ejercicio3;
import java.util.ArrayList;
import java.util.Iterator;

public class mainEjercicio3 {

	public static void main(String[] args) {
		ArrayList<IEdificio> listaEdificios = new ArrayList<>();

		listaEdificios.add(new Polideportivo(850, "Polideportivo 1", 2));
		listaEdificios.add(new Polideportivo(1200.50, "Polideportivo 2", 3));
		listaEdificios.add(new EdificioOficinas(1000,17));
		listaEdificios.add(new EdificioOficinas(700,10));
		listaEdificios.add(new Polideportivo(900.80, "Polideportivo 3", 1));
		
		 Iterator<IEdificio> iterator = listaEdificios.iterator();
	        while (iterator.hasNext()) {
	            IEdificio instalacion = iterator.next();
	            System.out.println(instalacion.toString());
	        }
}
}
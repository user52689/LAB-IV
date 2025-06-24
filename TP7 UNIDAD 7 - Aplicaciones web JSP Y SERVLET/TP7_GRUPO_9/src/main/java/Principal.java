import java.util.List;

import dao.TiposSeguroDao;
import dominio.TiposSeguro;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TiposSeguroDao tipo = new TiposSeguroDao();
		List<TiposSeguro> lista = tipo.obtenerTodos();
		if(lista.size() > 0) {
			System.out.println("La lista no esta vacia");
		}
		else {
			System.out.println("La lista esta vacia");
		}
		for(TiposSeguro tTipo : lista) {
			System.out.println(tTipo.toString());
		}
	}

}

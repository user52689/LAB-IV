package ejercicio2;

public class Producto {
	
	private String FechaCaducidad;
	private String NumeroLote;		

	//CONSTRUCTORES
	
	public Producto() {
		this.FechaCaducidad = "SIN DATOS";
		this.NumeroLote = "SIN DATOS";
	}
	
	public Producto(String FechaCaducidad, String NumeroLote) {
		this.FechaCaducidad =FechaCaducidad;
		this.NumeroLote = NumeroLote;
	}
	
	//GETTERS
	
	public String getFechaCaducidad() { return FechaCaducidad; }
	
	public String getNumeroLote() { return NumeroLote; }
	
	//SETERS
	public void setFechaCaducidad(String fechaCaducidad) {
		FechaCaducidad = fechaCaducidad;
	}

	public void setNumeroLote(String numeroLote) {
		NumeroLote = numeroLote;
	}

	@Override
	public String toString() {
		return "Producto [FechaCaducidad=" + FechaCaducidad + ", NumeroLote=" + NumeroLote + "]";
	}
	
	//MOSTRAR FALTA
	

}
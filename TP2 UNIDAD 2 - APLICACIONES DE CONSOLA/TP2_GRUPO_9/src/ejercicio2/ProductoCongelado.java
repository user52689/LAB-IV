package ejercicio2;

public class ProductoCongelado extends Producto {
	private String temperaturaRecom;

	public ProductoCongelado() {
		super();
		this.temperaturaRecom = "SIN DATOS";
	}

	public ProductoCongelado(String fechaCaducidad, String numeroLote, String temperatura) {
		super(fechaCaducidad, numeroLote);
		this.temperaturaRecom = temperatura;
	}
	
	public void setTemperatura(String temperatura) {
		this.temperaturaRecom = temperatura;
	}
	
	public String getTemperatura() {
		return this.temperaturaRecom;
	}

	@Override
	public String toString() {
		String numeroLote = super.getNumeroLote();
		String fechaCaducidad = super.getFechaCaducidad();
		return "ProductoCongelado "
				+ "[numeroLote=" + numeroLote 
				+ ", fechaCaducidad=" + fechaCaducidad 
				+ ", temperaturaRecom=" + temperaturaRecom + "]";
	}
	
	
	
}
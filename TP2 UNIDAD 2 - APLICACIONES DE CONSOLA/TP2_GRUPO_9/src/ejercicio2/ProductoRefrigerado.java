package ejercicio2;

public class ProductoRefrigerado extends Producto {
	private String codigoOrganismo;
	
	public ProductoRefrigerado() {
		super();
		this.codigoOrganismo = "SIN DATOS";
	}
	
	public ProductoRefrigerado(String fechaCaducidad, String numeroLote, String codigoOrganismo) {
		super(fechaCaducidad,numeroLote);
		this.codigoOrganismo = codigoOrganismo;
	}

	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}

	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}

	@Override
	public String toString() {
		String numeroLote = super.getNumeroLote();
		String fechaCaducidad = super.getFechaCaducidad();
		return "ProductoCongelado "
				+ ", [numeroLote=" + numeroLote
				+ ", fechaCaducidad=" + fechaCaducidad 
				+ ", codigoOrganismo=" + codigoOrganismo + "]";
	}
	
	
}
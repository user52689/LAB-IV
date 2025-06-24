package ejercicio2;


public class ProductoFresco extends Producto{
	private String fechaEnvasado;
	private String paisOrigen;


	public ProductoFresco() {
		super();
		this.fechaEnvasado = "SIN DATOS";
		this.paisOrigen = "SIN DATOS";
	}
	
	public ProductoFresco(String fechaCaducidad, String numeroLote,
			String fechaEnvasado, String paisOrigen) {
		super(fechaCaducidad,numeroLote);
		this.fechaEnvasado = fechaEnvasado;
		this.paisOrigen = paisOrigen;
	}

	public String getfechaEnvasado() {
		return fechaEnvasado;
	}

	public void setfechaEnvasado(String fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}

	public String getpaisOrigen() {
		return paisOrigen;
	}

	public void setpaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	@Override
	public String toString() {
		String numeroLote = super.getNumeroLote();
		String fechaCaducidad = super.getFechaCaducidad();
		return "ProductoFresco "
				+ "[numeroLote=" + numeroLote 
				+ ", fechaCaducidad=" + fechaCaducidad 
				+ ", fechaEnvasado=" + fechaEnvasado 
				+ ", paisOrigen=" + paisOrigen + "]";
	}
	
	
}
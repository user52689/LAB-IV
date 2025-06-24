package ejercicio3;

public class EdificioOficinas implements IEdificio{

	private double superficie;
	private int numeroOficinas;
	
	//Constructores
	
	public EdificioOficinas() {
			this.superficie = 0;
			this.numeroOficinas= 0;
		}

	public EdificioOficinas(double superficie, int numeroOficinas) {
			this.superficie = superficie;
			this.numeroOficinas = numeroOficinas;
		}

	@Override
	public double getSuperficieEdificio() {
		return superficie;
	}
	
	public int getNumeroOficinas() {
		return numeroOficinas;
	}

	@Override
	public String toString() {
		return "EdificioOficinas " 
				+ "[superficie=" + superficie 
				+ ", numeroOficinas=" + numeroOficinas + "]";
	}
	
	
	
	
}


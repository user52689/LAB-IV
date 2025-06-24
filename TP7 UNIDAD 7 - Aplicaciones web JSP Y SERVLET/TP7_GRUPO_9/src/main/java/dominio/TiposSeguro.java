package dominio;

public class TiposSeguro {
    private int id;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


	@Override
	public String toString() {
		return "TiposSeguro [id=" + id + ", descripcion=" + descripcion + "]";
	}
    
}

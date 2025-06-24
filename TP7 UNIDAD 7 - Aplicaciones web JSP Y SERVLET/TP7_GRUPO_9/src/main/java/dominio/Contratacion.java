package dominio;

public class Contratacion {
    private int id;
    private String nombreUsuario;
    private int idSeguro;
    private float costoContratacion;

    public Contratacion() {
    }

    public Contratacion(int id, String nombreUsuario, int idSeguro, float costoContratacion) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.idSeguro = idSeguro;
        this.costoContratacion = costoContratacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public float getCostoContratacion() {
        return costoContratacion;
    }

    public void setCostoContratacion(float costoContratacion) {
        this.costoContratacion = costoContratacion;
    }
}


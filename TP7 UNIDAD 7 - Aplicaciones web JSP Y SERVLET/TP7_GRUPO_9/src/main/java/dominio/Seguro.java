package dominio;

public class Seguro {
    private int id;
    private String descripcion;
    private int idTipo;
    private String descripcionTipo;
    private float costoContratacion;
    private float costoAsegurado;

    public Seguro() {
    }

    public Seguro(int id, String descripcion, int idTipo, String descripcionTipo , float costoContratacion, float costoAsegurado) {
        this.id = id;
        this.descripcion = descripcion;
        this.idTipo = idTipo;
        this.descripcionTipo = descripcionTipo;
        this.costoContratacion = costoContratacion;
        this.costoAsegurado = costoAsegurado;
    }

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

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    public String getDescripcionTipo() {
        return this.descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public float getCostoContratacion() {
        return costoContratacion;
    }

    public void setCostoContratacion(float costoContratacion) {
        this.costoContratacion = costoContratacion;
    }

    public float getCostoAsegurado() {
        return costoAsegurado;
    }

    public void setCostoAsegurado(float costoAsegurado) {
        this.costoAsegurado = costoAsegurado;
    }
}

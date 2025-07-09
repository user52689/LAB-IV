package Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {
    private int idUsuario;
    private String dni;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String rol;
    private String correoElectronico;
    private String telefono;
    private int idGenero;
    private int idPais;
    private LocalDate fechaNacimiento;
    private String direccion;
    private int idLocalidad;
    private int idProvincia;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimoAcceso;
    private boolean activo;

    public Usuario() {}

    public Usuario(int idUsuario, String dni, String nombreUsuario, String nombre, String apellido, String contrasena, String rol,
                   String correoElectronico, String telefono, int idGenero, int idPais, LocalDate fechaNacimiento,
                   String direccion, int idLocalidad, int idProvincia,
                   LocalDateTime fechaCreacion, LocalDateTime ultimoAcceso, boolean activo) {
        this.idUsuario = idUsuario;
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.rol = rol;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.idGenero = idGenero;
        this.idPais = idPais;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.idLocalidad = idLocalidad;
        this.idProvincia = idProvincia;
        this.fechaCreacion = fechaCreacion;
        this.ultimoAcceso = ultimoAcceso;
        this.activo = activo;
    }

    // Getters y setters

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdGenero() {
        return idGenero;
    }
    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdPais() {
        return idPais;
    }
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }
    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public int getIdProvincia() {
        return idProvincia;
    }
    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }
    public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Métodos para RBAC (Roles)

    public boolean esAdministrador() {
        return "admin".equalsIgnoreCase(rol);
    }

    public boolean esCliente() {
        return "cliente".equalsIgnoreCase(rol);
    }
}

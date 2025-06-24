package negocio;

import java.util.List;

import entidad.Persona;

public interface PersonaNegocio {
	public boolean AgregarPersona(Persona persona);
	public boolean EliminarPersona(Persona seleccionada);
	public boolean ModificarPersona(Persona persona);
	public List<Persona> getAll();
	public String getMensaje();
}

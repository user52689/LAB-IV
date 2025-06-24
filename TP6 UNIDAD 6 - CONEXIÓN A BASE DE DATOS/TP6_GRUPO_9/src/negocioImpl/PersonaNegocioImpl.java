package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio{
	Persona persona;
	PersonaDao personaDao = new PersonaDaoImpl();
	
	private String mensaje = "";

	@Override
	public boolean AgregarPersona(Persona persona) {
	    boolean personaAgregada = false;
	    mensaje = "";

	    if (!persona.getDni().trim().isEmpty() && 
	        !persona.getNombre().trim().isEmpty() && 
	        !persona.getApellido().trim().isEmpty()) {

	        if (!personaDao.existeDni(persona.getDni())) {
	            personaAgregada = personaDao.insert(persona);
	            if (personaAgregada)
	                mensaje = "Persona agregada con exito.";
	            else
	                mensaje = "Error al insertar persona.";
	        } else {
	            mensaje = "El DNI ya existe.";
	        }
	    } else {
	        mensaje = "Todos los campos son obligatorios.";
	    }

	    return personaAgregada;
	}
	public String getMensaje() {
	    return mensaje;
	}
	@Override
	public boolean EliminarPersona(Persona personaSeleccionada) {
		boolean estado=false;
		if(personaSeleccionada.getDni()!="" )
		{
			estado = personaDao.delete(personaSeleccionada);
		}
		return estado;
	}
	@Override
	public List<Persona> getAll() {
	    List<Persona> listaPersonas = personaDao.getAll();
	    if (listaPersonas == null) {
	        return new ArrayList<>();  
	    }
	    return listaPersonas;
	}
	
	@Override
	public boolean ModificarPersona(Persona persona) {
	    return personaDao.update(persona);
	}	
}

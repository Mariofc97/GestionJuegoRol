package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.EquipamientoDao;
import dao.PersonajeDao;
import dao.impl.EquipamientoDaoImpl;
import dao.impl.PersonajeDaoImpl;
import dto.EquipamientoDto;
import entities.Personaje;
import entities.equipo.Equipamiento;
import exceptions.ReglaJuegoException;
import service.EquipamientoService;


public class EquipamientoServiceImpl implements EquipamientoService {

	private final EquipamientoDao equipamientoDao = new EquipamientoDaoImpl();
	private final PersonajeDao personajeDao = new PersonajeDaoImpl();
	
	private static final int MAX_OBJETOS = 20;
	private static final int MAX_PESO_TOTAL = 50;
		
	private EquipamientoDto mapToDto(Equipamiento e) {
	    if (e == null) return null;

	    String tipo = e.getClass().getSimpleName().toUpperCase();

	    Long personajeId = null;
	    if (e.getPersonaje() != null) {
	        personajeId = e.getPersonaje().getId();
	    }

	    return new EquipamientoDto(
	        e.getId(),
	        tipo,
	        e.getNombre(),
	        e.getPeso(),
	        e.getDurabilidad(),
	        e.getNivelRequerido(),
	        personajeId
	    );
	}

	@Override
    public List<EquipamientoDto> listarPorPersonaje(Long personajeId) {
        if (personajeId == null) throw new RuntimeException("personajeId obligatorio");

        Personaje personaje = personajeDao.findById(personajeId);
        if (personaje == null) throw new RuntimeException("No existe Personaje con id=" + personajeId);

        if (personaje.getEquipo() == null) return List.of();

        List<EquipamientoDto> res = new ArrayList<>();
        for (Equipamiento e : personaje.getEquipo()) {
            res.add(mapToDto(e));
        }
        return res;
    }

	@Override
	public void eliminar(Long equipamientoId) {
		// TODO Auto-generated method stub
        if (equipamientoId == null) throw new RuntimeException("equipamientoId obligatorio");

        Equipamiento e = equipamientoDao.findById(equipamientoId);
        if (e == null) throw new RuntimeException("No existe Equipamiento con id=" + equipamientoId);

        equipamientoDao.delete(e);
		
	}

	@Override
	public EquipamientoDto añadirAlInventario(Long personajeId, Equipamiento nuevo) throws ReglaJuegoException {
		// TODO Auto-generated method stub
		Personaje p = personajeDao.findById(personajeId);
		if(p == null) throw new ReglaJuegoException("El personaje no existe.");
		
		if(p.getEquipo().size() >= 20) {
			throw new ReglaJuegoException("No puedes llevar más de 20 objetos.");
		}
		
		int pesoActual = 0;
		
		for (Equipamiento e : p.getEquipo()) {
			pesoActual += e.getPeso();
		}
		
		int pesoMax = 35;
		
		if(pesoActual + nuevo.getPeso() > pesoMax) {
			throw new ReglaJuegoException("Te pasas de peso. El máximo permitido es: " + pesoMax + " kg.");
		}
		
		//Enlazar FK
		nuevo.setPersonaje(p);
		p.getEquipo().add(nuevo);
		
		personajeDao.update(p);
		return mapToDto(nuevo);
	}
	
}

package service.impl;

import dao.EquipamientoDao;
import dao.PersonajeDao;
import dao.impl.EquipamientoDaoImpl;
import dao.impl.PersonajeDaoImpl;
import dto.EquipamientoDto;
import entities.equipo.Equipamiento;

public class EquipamientoServiceImpl {

	private final EquipamientoDao equipamientoDao = new EquipamientoDaoImpl();
	private final PersonajeDao personajeDao = new PersonajeDaoImpl();
		
	private EquipamientoDto mapToDto(Equipamiento e) {
		if(e == null) {
			return null;
		}
		
		String tipo = e.getClass().getSimpleName().toUpperCase();
		Long personajeId = null;
		if(e.getPersonaje() == null){
			personajeId = null;
		} else {
			e.getPersonaje().getId();
		}
	}
}

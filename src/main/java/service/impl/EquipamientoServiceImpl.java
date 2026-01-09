package service.impl;

import dao.EquipamientoDao;
import dao.PersonajeDao;
import dao.impl.EquipamientoDaoImpl;
import dao.impl.PersonajeDaoImpl;
import dto.EquipamientoDto;
import entities.equipo.Equipamiento;
import entities.equipo.objetos.Cuerda;
import entities.equipo.objetos.MojonSeco;
import entities.equipo.objetos.Palo;
import entities.equipo.objetos.Piedra;
import entities.equipo.objetos.Pocion;

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
	
	private Equipamiento construirEquipamiento(String tipo) {
		String t = tipo.trim().toUpperCase();
		
		switch (t) {
		case "CUERDA":
			return new Cuerda();
		case "PIERDA":
			return new Piedra();
		case "PALO":
			return new Palo();
		case "MOJON SECO":
			return new MojonSeco();
		case "POCION":
			return new Pocion();
		case "PALO":
			return new Palo();
		case "PALO":
			return new Palo();

		default:
			break;
		}
	}
}

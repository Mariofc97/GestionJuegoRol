package service;

import java.util.List;

import dto.EquipamientoDto;
import entities.equipo.Equipamiento;
import exceptions.ReglaJuegoException;

public interface EquipamientoService {

	//Añadir un equipo por "tipo" (CUERDA, PIEDRA, PALO...) al personaje
	EquipamientoDto añadirAlInventario(Long personajeId, Equipamiento nuevo) throws ReglaJuegoException;
	
	//Lista inventario del personaje
	List<EquipamientoDto> listarPorPersonaje(Long personajeId);
	
	//Elimina un equipamiento concreto
	void eliminar(Long equipamientoId);
	
	EquipamientoDto fabricar(Long personajeId, String tipo) throws ReglaJuegoException;

	EquipamientoDto equiparArma(Long personajeId, Long equipamientoId) throws ReglaJuegoException;
	EquipamientoDto equiparEscudo(Long personajeId, Long equipamientoId) throws ReglaJuegoException;

}

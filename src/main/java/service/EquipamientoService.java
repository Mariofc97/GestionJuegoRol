package service;

import java.util.List;

import dto.EquipamientoDto;

public interface EquipamientoService {

	//Añadir un equipo por "tipo" (CUERDA, PIEDRA, PALO...) al personaje
	EquipamientoDto añadirAlInventario(Long personajeId, String tipoEquipamiento);
	
	//Lista inventario del personaje
	List<EquipamientoDto> listarPorPersonaje(Long personaje);
	
	//Elimina un equipamiento concreto
	void eliminar(Long equipamientoId);
}

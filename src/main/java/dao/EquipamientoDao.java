package dao;

import java.util.List;

import entities.equipo.Equipamiento;

public interface EquipamientoDao extends GenericDao <Equipamiento, Long>{
	
	List<Equipamiento> findByPersonajeId(Long personajeId);
	void eliminarEquipamiento(Long personajeId, Long equipoId );

}

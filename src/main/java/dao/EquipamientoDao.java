package dao;

import java.util.List;

import entities.equipo.Equipamiento;

public interface EquipamientoDao extends GenericDao <Equipamiento, Long>{
	
	List<Equipamiento> findByPersonajeId(Long personajeId);
	int deleteByIdAndPersonajeId(Long personajeId, Long equipoId );
	

}

package dao.impl;

import java.util.List;

import dao.EquipamientoDao;
import entities.equipo.Equipamiento;

public class EquipamientoDaoImpl extends GenericDaoHibernate<Equipamiento, Long> implements EquipamientoDao {

	public EquipamientoDaoImpl() {
		super(Equipamiento.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Equipamiento> findByPersonajeId(Long personajeId) {
		// TODO Auto-generated method stub
		return session()
			.createQuery("from Equipamiento e where e.personaje.id = :pid", Equipamiento.class)
			.setParameter("pid", personajeId)
			.getResultList();
	}

	@Override
	public void eliminarEquipamiento(Long personajeId, Long equipoId) {
	    Equipamiento equipamiento = session()
	            .createQuery("FROM Equipamiento e WHERE e.personaje.id = :pid AND e.id = :eid", Equipamiento.class)
	            .setParameter("pid", personajeId)
	            .setParameter("eid", equipoId)
	            .uniqueResult();

	    if (equipamiento != null) {
	        session().delete(equipamiento);
	    }
	}
	
	

}

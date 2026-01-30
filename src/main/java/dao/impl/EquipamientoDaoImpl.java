package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.EquipamientoDao;
import entities.equipo.Equipamiento;
import utilidades.HibernateUtil;

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
    public int deleteByIdAndPersonajeId(Long equipId, Long personajeId) {
        Transaction tx = null;

        try (Session session = HibernateUtil.openSession()) {

            tx = session.beginTransaction();

            int filas = session.createQuery("""
                    delete from Equipamiento e
                    where e.id = :eid and e.personaje.id = :pid
                """)
                .setParameter("eid", equipId)
                .setParameter("pid", personajeId)
                .executeUpdate();

            tx.commit();
            return filas;

        } catch (RuntimeException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }
}


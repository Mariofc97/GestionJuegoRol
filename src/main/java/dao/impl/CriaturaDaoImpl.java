package dao.impl;

import java.util.List;

import org.hibernate.Transaction;

import dao.CriaturaDao;
import entities.Personaje;
import entities.criatura.Criatura;

public class CriaturaDaoImpl extends GenericDaoHibernate<Criatura, Long> implements CriaturaDao {

    public CriaturaDaoImpl() {
        super(Criatura.class);
    }

    @Override
    public List<Criatura> findByPersonajeId(Long personajeId) {
        try {
            return session()
                    .createQuery("from Criatura c where c.personaje.id = :pid order by c.id", Criatura.class)
                    .setParameter("pid", personajeId)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error listando Criaturas por personaje: " + e.getMessage());
        }
    }

    @Override
    public long countByPersonajeId(Long personajeId) {
        try {
            Long count = session()
                    .createQuery("select count(c.id) from Criatura c where c.personaje.id = :pid", Long.class)
                    .setParameter("pid", personajeId)
                    .uniqueResult();
            return (count == null) ? 0 : count.longValue();
        } catch (Exception e) {
            throw new RuntimeException("Error contando Criaturas por personaje: " + e.getMessage());
        }
    }

    @Override
    public Criatura saveToPersonaje(Long personajeId, Criatura criatura) {
        Transaction tx = null;
        try {
            tx = session().beginTransaction();

            Personaje p = session().get(Personaje.class, personajeId);
            if (p == null) {
                throw new RuntimeException("No existe Personaje con id " + personajeId);
            }

            criatura.setPersonaje(p);
            // opcional: mantener coherencia en memoria
            if (p.getCriaturas() != null) {
                p.getCriaturas().add(criatura);
            }

            session().persist(criatura);

            tx.commit();
            return criatura;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new RuntimeException("Error guardando Criatura en personaje: " + e.getMessage());
        }
    }
}


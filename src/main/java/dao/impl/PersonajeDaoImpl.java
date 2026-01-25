package dao.impl;

import java.util.List;

import org.hibernate.Session;

import dao.PersonajeDao;
import entities.Personaje;
import utilidades.HibernateUtil;

public class PersonajeDaoImpl extends GenericDaoHibernate<Personaje, Long> implements PersonajeDao {

	public PersonajeDaoImpl() {
		super(Personaje.class);
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<Personaje> findByUsuarioId(Long usuarioId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personaje findByIdFetchAll(Long id) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        return session.createQuery(
	            "select distinct p from Personaje p " +
	            "left join fetch p.usuario " +
	            "left join fetch p.equipo " +
	            "left join fetch p.criaturas " +
	            "where p.id = :id", Personaje.class
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }

	}
	
	@Override
	public Personaje findByIdFetchEquipo(Long id) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        return session.createQuery(
	            "select distinct p from Personaje p " +
	            "left join fetch p.equipo " +
	            "where p.id = :id", Personaje.class
	        ).setParameter("id", id)
	         .uniqueResult();
	    } finally {
	        session.close();
	    }
	}
	
}

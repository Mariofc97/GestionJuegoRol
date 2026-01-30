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
	    try (Session s = session()) {
	        return s.createQuery(
	                "from Personaje p where p.usuario.id = :uid", Personaje.class)
	            .setParameter("uid", usuarioId)
	            .getResultList(); // esto nunca debería ser null
	    }
	}

	@Override
	public Personaje findByIdFetchAll(Long id) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    try {
	        // 1) Fetch de usuario + UNA colección (equipo)
	        Personaje p = session.createQuery(
	            "select distinct p from Personaje p " +
	            "left join fetch p.usuario " +
	            "left join fetch p.equipo " +
	            "where p.id = :id", Personaje.class
	        ).setParameter("id", id)
	         .uniqueResult();

	        // 2) Inicializar la otra colección (criaturas) en la misma sesión
	        if (p != null && p.getCriaturas() != null) {
	            p.getCriaturas().size(); // fuerza carga
	        }

	        return p;

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


	@Override
	public Personaje findByIdForUpdate(Long id) {
		// TODO Auto-generated method stub
	    try (Session s = session()) {
	        return s.get(Personaje.class, id); // managed dentro de esa sesión
	    }
	}
	
}

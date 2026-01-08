package dao.impl;

import java.util.List;

import org.hibernate.Session;

import dao.PersonajeDao;
import entities.Personaje;

public class PersonajeDaoImpl extends GenericDaoHibernate<Personaje, Long> implements PersonajeDao {

	public PersonajeDaoImpl() {
		super(Personaje.class);
		// TODO Auto-generated constructor stub
	}

    @Override
    public List<Personaje> findByUsuarioId(Long usuarioId) {
        Session s = session();
        return s.createQuery(
                "from Personaje p where p.usuario.id = :uid",
                Personaje.class
        ).setParameter("uid", usuarioId)
         .getResultList();
    }

	
}

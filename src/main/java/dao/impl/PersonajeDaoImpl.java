package dao.impl;

import dao.PersonajeDao;
import entities.Personaje;

public class PersonajeDaoImpl extends GenericDaoHibernate<Personaje, Long> implements PersonajeDao {

	public PersonajeDaoImpl() {
		super(Personaje.class);
		// TODO Auto-generated constructor stub
	}

	
}

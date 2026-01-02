package dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.GenericDao;
import utilidades.HibernateUtil;

public class GenericDaoHibernate<T, ID> implements GenericDao<T, ID> {

	private final Class<T> entityClass;
	

	
	public GenericDaoHibernate(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}
	
	protected Session session() {
		return HibernateUtil.getSessionFactory();
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().persist(entity);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error guardando " + entityClass.getSimpleName() + ": " + e.getMessage());
		}
		
	}

	@Override
	public T update(T entity){
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			T merged = (T) session().merge(entity);
			tx.commit();
			return merged;
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error actualizando " + entityClass.getSimpleName() + ": " + e.getMessage());
		}
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		try {
			tx = session().beginTransaction();
			session().remove(entity);
			tx.commit();
		}catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			throw new RuntimeException("Error eliminando " + entityClass.getSimpleName() + ": " + e.getMessage());
		}
	}

	@Override
	public T findById(ID id){
		// TODO Auto-generated method stub
		try {
			return session().get(entityClass, (Serializable) id);
		} catch (Exception e) {
			throw new RuntimeException("Error buscado " + entityClass.getSimpleName() + " por ID: " + e.getMessage());
		}
	}

	@Override
	public List<T> findAll(){
		// TODO Auto-generated method stub
		try {
			return session()
					.createQuery("from " + entityClass.getName(), entityClass)
					.getResultList();
		} catch(Exception e) {
			throw new RuntimeException("Error listando " + entityClass.getSimpleName() + ": " + e.getMessage());
		}
	}

}

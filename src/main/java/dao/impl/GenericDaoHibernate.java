package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.GenericDao;
import utilidades.HibernateUtil;

public abstract class GenericDaoHibernate<T, ID> implements GenericDao<T, ID> {

    private final Class<T> entityClass;

    public GenericDaoHibernate(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Para los DAOs que ya usan session() en queries custom
    protected Session session() {
        return HibernateUtil.openSession();
    }

    @Override
    public T findById(ID id) {
        try (Session s = HibernateUtil.openSession()) {
            return s.get(entityClass, (java.io.Serializable) id);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session s = HibernateUtil.openSession()) {
            return s.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
        }
    }

    @Override
    public T save(T entity) {
        Transaction tx = null;
        try (Session s = HibernateUtil.openSession()) {
            tx = s.beginTransaction();
            s.persist(entity);
            tx.commit();
            return entity;
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public T update(T entity) {
        Transaction tx = null;
        try (Session s = HibernateUtil.openSession()) {
            tx = s.beginTransaction();
            T merged = (T) s.merge(entity);
            tx.commit();
            return merged;
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(T entity) {
        Transaction tx = null;
        try (Session s = HibernateUtil.openSession()) {
            tx = s.beginTransaction();
            s.remove(s.contains(entity) ? entity : s.merge(entity));
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}

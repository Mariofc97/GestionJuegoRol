package dao;

import java.util.List;

public interface GenericDao <T, ID>{

//	MARIO: Uso de Generic Dao:
//	Para no tener que reecribir el CRUD en 10 clases, tener una estructura mas limpia y reutilizada
//
	    void save(T entity);
	    T update(T entity);
	    void delete(T entity);
	    T findById(ID id);
	    List<T> findAll();
}

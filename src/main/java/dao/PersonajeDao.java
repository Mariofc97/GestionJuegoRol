package dao;

import java.util.List;

import entities.Personaje;

public interface PersonajeDao extends GenericDao<Personaje, Long> {
    List<Personaje> findByUsuarioId(Long usuarioId);
	//mas adelante: findByNombre, findByUsuario
}

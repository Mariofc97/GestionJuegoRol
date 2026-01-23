package dao;

import java.util.List;

import entities.Personaje;

public interface PersonajeDao extends GenericDao<Personaje, Long> {
	Personaje findByIdFetchAll(Long id);
	Personaje findByIdFetchEquipo(Long id);
    List<Personaje> findByUsuarioId(Long usuarioId);
	//mas adelante: findByNombre, findByUsuario
}

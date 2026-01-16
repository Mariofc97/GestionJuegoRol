package dao;

import java.util.List;

import entities.criatura.Criatura;

public interface CriaturaDao extends GenericDao<Criatura, Long> {

    List<Criatura> findByPersonajeId(Long personajeId);

    long countByPersonajeId(Long personajeId);

    // Persistencia directa de criatura asociada al personaje (para que tenga ID al devolverla)
    Criatura saveToPersonaje(Long personajeId, Criatura criatura);
}

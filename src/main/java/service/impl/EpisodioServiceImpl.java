package service.impl;

import dao.PersonajeDao;
import dao.impl.PersonajeDaoImpl;
import entities.Personaje;
import service.EpisodioService;
import service.juego.EpisodioRegistry;
import service.juego.EpisodioRunner;

public class EpisodioServiceImpl implements EpisodioService {

	private final PersonajeDao personajeDao = new PersonajeDaoImpl();
	private final EpisodioRegistry registry = new EpisodioRegistry();
	
	@Override
	public Personaje jugarEpisodioActual(Long personajeId) {
	    if (personajeId == null) throw new RuntimeException("El Id del personaje es obligatorio");

	    Personaje p = personajeDao.findByIdFetchAll(personajeId);
	    if (p == null) throw new RuntimeException("No existe personaje con id=" + personajeId);

	    int actual = p.getEpisodioActual();
	    EpisodioRunner runner = registry.get(actual);
	    if (runner == null) throw new RuntimeException("No existe runner para episodio " + actual);

	    int siguiente = runner.ejecutar(p);

	    p.setEpisodioActual(siguiente);
	    personajeDao.update(p);

	    // DEVUELVE YA CON TODO CARGADO
	    return personajeDao.findByIdFetchAll(personajeId);
	}

}

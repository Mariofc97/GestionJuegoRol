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

	    // 1) Cargas para jugar
	    Personaje p = personajeDao.findByIdFetchAll(personajeId);
	    if (p == null) throw new RuntimeException("No existe personaje con id=" + personajeId);

	    int actual = p.getEpisodioActual();
	    EpisodioRunner runner = registry.get(actual);
	    if (runner == null) throw new RuntimeException("No existe runner para episodio " + actual);

	    // 2) Juegas (runner modifica p en memoria: puntosVida, etc.)
	    int siguiente = runner.ejecutar(p);

	    // 3) ✅ RECARGAR “LA VERDAD” DESDE BD (ya trae exp/nivel/atk/vidaMax correctos)
	    Personaje bd = personajeDao.findByIdFetchAll(personajeId);
	    if (bd == null) throw new RuntimeException("No existe personaje con id=" + personajeId);

	    // 4) ✅ Mezclas SOLO lo que quieres conservar del episodio “en memoria”
	    // - daños/curas/descansos/venenos
	    bd.setPuntosVida(p.getPuntosVida());

	    // 5) Avanzas episodio
	    bd.setEpisodioActual(siguiente);

	    // 6) Guardas SIN machacar stats
	    personajeDao.update(bd);

	    // 7) Devuelves con todo cargado
	    return personajeDao.findByIdFetchAll(personajeId);
	}


}

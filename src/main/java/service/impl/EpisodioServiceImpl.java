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
		// TODO Auto-generated method stub
		if(personajeId == null) throw new RuntimeException("El Id del personaje es obligatorio");
        // 1) cargar personaje de BD
        Personaje p = personajeDao.findById(personajeId);
        if (p == null) throw new RuntimeException("No existe personaje con id=" + personajeId);

        // 2) elegir episodio según progreso
        int actual = p.getEpisodioActual();
        EpisodioRunner runner = registry.get(actual);
        if (runner == null) throw new RuntimeException("No existe runner para episodio " + actual);

        // 3) ejecutar lógica (consola)
        int siguiente = runner.ejecutar(p);

        // 4) guardar progreso + cambios
        p.setEpisodioActual(siguiente);
        return personajeDao.update(p); // merge + commit (según vuestro GenericDaoHibernate)
	}

}

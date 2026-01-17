package service.juego;

import entities.Personaje;

public interface EpisodioRunner {

	int numero();
	int ejecutar(Personaje personaje); // devuelve el siguiente episodio
}

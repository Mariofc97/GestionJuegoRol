package service.juego;

import entities.Personaje;
import entities.episodios.Episodio4Rio;

public class Episodio4Runner implements EpisodioRunner {

	@Override
	public int numero() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int ejecutar(Personaje personaje) {
		// TODO Auto-generated method stub
		Episodio4Rio.episodio4Rio(personaje);;
		return 4; // cuando haya un cuarto episodio ponemos cuatro
	}

}
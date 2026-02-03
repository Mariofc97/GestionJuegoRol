package service.juego;

import entities.Personaje;
import entities.episodios.Episodio5JefeCueva;

public class Episodio5Runner implements EpisodioRunner {

	@Override
	public int numero() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int ejecutar(Personaje personaje) {
		// TODO Auto-generated method stub
		Episodio5JefeCueva.episodio5JefeClan(personaje);
		return 1; // cuando haya un cuarto episodio ponemos cuatro
	}

}

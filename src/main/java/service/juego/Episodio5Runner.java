package service.juego;

import entities.Personaje;
import entities.episodios.Episodio4Rio;
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
		return 5; // cuando haya un cuarto episodio ponemos cuatro
	}

}

package utilidades;

import equipo.Equipamiento;
import personajes.Personaje;

public class utils {

	// TODO
	// Metodos

	// ganarEquipo();

	/**
	 * @param ganaEquipo equipo que queremos añadir al personaje clase Equipamiento
	 * @param person     personaje al que añadimos equipo, clase Personaje
	 */
	public static void ganarEquipo(Equipamiento ganaEquipo, Personaje person) {

		person.getEquipo().add(ganaEquipo);
		System.out.println("Has gando equipo: " + ganaEquipo);

	}
	// invocacion();

	/**
	 * @return devuelve int resultado tirada
	 */
	public static int dadoDiez() {
		int tirada = (int) (Math.random() * 10 + 1);
		return tirada;
	}

}

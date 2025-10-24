package utilidades;

import criaturas.Criatura;
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
		System.out.println("Has gando equipo: " + ganaEquipo.toString());

	}
	// invocacion();
	
	public static void invocacionCompañeroCriatura (Criatura compi, Personaje person) {
		
	boolean	resultado = dadoDiez()>3;
	if (resultado) {
		person.getCriaturas().add(compi);
		System.out.println("Hora tienes un compañero de viaje:" + compi.toString());
	}else {
		System.out.println("No estan pensado en lo que debes, al invocar la criatura se rie de ti y te ataca.");
		person.set
	}
		
	}

	/**
	 * @return devuelve int resultado tirada
	 */
	public static int dadoDiez() {
		int tirada = (int) (Math.random() * 10 + 1);
		return tirada;
	}

}

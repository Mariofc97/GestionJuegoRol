package utilidades;

import java.util.concurrent.ThreadLocalRandom;

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

	public static void invocacionCompañeroCriatura(Criatura compi, Personaje person) {

		boolean resultado = dadoDiez() > 3;
		if (resultado) {
			person.getCriaturas().add(compi);
			System.out.println("Hora tienes un compañero de viaje:" + compi.toString());
		} else {
			System.out.println("No estan pensado en lo que debes, al invocar la criatura se rie de ti y te ataca.");
			person.setPuntosVida(person.getPuntosVida() - compi.getPuntosAtaque());
		}

	}

	/**
	 * @return devuelve int resultado tirada
	 */
	public static int dadoDiez() {
		int tirada = (int) (Math.random() * 10 + 1);
		return tirada;
	}

	// metodo nuevo.
	public static String desgraciaAleatorio() {
		String[] nombres = { "Hay tormenta y te cae un rayos vuelves a la cueva, hueles a pelo quemado.",
				"Hay ventisca tropiezas y te caes por el acantilado de al lado de la cueva, consigues volver a la cueva a duras penas.",
				"Esta helando y no tienes ropa, vuelves a la cueva con hipotermia.",
				"Hay una ola de calor y te deshidratas, te rescatan los niños.",
				"Habia un dientes de sable acechando y sales con vida gracias a que hueles muy mal y no ha querido comerte.",
				"El día es explendido te distraes disfrutando de la tarde y un mamut te arrolla.",
				"Los extraterrestre te secuestran y experimenta contigo, estas para el arrastre.",
				"Esta lloviendo mucho y decides volver, pero al volver te pilla una riada, casi te ahogas." };
		String nombre = nombres[ThreadLocalRandom.current().nextInt(nombres.length)];
		System.out.println(nombre + "tus puntos de via son\t");
		return nombre;
	}

}

package utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import criaturas.Conejo;
import criaturas.Criatura;
import criaturas.Gusano;
import criaturas.Mosquito;
import criaturas.Raton;
import equipo.Equipamiento;
import personajes.Personaje;

public class Utils {

	// TODO
	// Metodos

	// ganarEquipo();

	/**
	 * @param ganaEquipo equipo que queremos añadir al personaje clase Equipamiento
	 * @param person     personaje al que añadimos equipo, clase Personaje
	 */
	public static void ganarEquipo(Equipamiento ganaEquipo, Personaje person) {

		person.getEquipo().add(ganaEquipo);
		System.out.println("Has ganado equipo: " + ganaEquipo.toString());

	}

	// invocacion();

	public static Criatura invocacionCompañeroCriatura(Personaje person) {

		Criatura compi = randomizarCriatura();
		boolean resultado = dadoDiez() > 3;

		if (resultado) {

			person.getCriaturas().add(compi);
			System.out.println("Ahora tienes un compañero de viaje:" + compi.getNombre());
		} else {
			System.out.println("No estas pensado en lo que debes, al invocar la criatura se rie de ti y te ataca.");
			person.setPuntosVida(person.getPuntosVida() - compi.getPuntosAtaque());
		}

		return compi;
	}

	// este random solo esta hecho con 4 criaturas, habrá que meter mas si se
	// generan mas
	public static Criatura randomizarCriatura() {

		int tirada = (int) (Math.random() * 4 + 1);
// tememos que no usar el metodo por def para que añada el nombre
		
		switch (tirada) {
		case 1:
			return new Gusano();
		case 2:
			return new Conejo();
		case 3:
			return new Mosquito();
		default:
			return new Raton();
		}
	}

	/**
	 * @return devuelve int resultado tirada
	 */
	public static int dadoDiez() {
		int tirada = dadoNumeroDefine(10);
		return tirada;
	}

	public static int dadoNumeroDefine(int numero) {
		int tirada = (int) (Math.random() * numero + 1);
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

	public static int pideDatoNumerico(String texto) {
		Scanner scan = new Scanner(System.in);
		int numero;

		while (true) {
			System.out.println(texto);

			if (scan.hasNextInt()) { // Comprueba si es un número entero
				numero = scan.nextInt();
				return numero; // Devuelve el número válido
			} else {
				System.out.println("No has introducido un valor correcto. Inténtalo de nuevo.");
				scan.nextLine(); // Limpia el buffer
			}
		}
	}

	public static void combate(Personaje person, Criatura enemigo) {
		System.out.println("Comienza el combate entre: " + person.getNombre() + " y " + enemigo.getNombre());

		if (person.tieneArmaEquipada() == false) {
			System.out
					.println("El personaje " + person.getNombre() + " no tiene ningun arma equipada, asique el enemigo "
							+ enemigo.getNombre() + " se mea en tu cara y te deja a un punto de vida.");
			person.setPuntosVida(1);
			System.out.println("Vuelves a la cueva con " + person.getPuntosVida() + " puntos de vida.");

		}

		while (person.estaVivo() && enemigo.estaVivo() && person.tieneArmaEquipada()) {

			// Turno personaje
			int danioHecho = person.atacar(enemigo);
			System.out
					.println(person.getNombre() + " hace " + danioHecho + " de daño al enemigo " + enemigo.getNombre());
			System.out.println("La vida del enemigo es de " + enemigo.getPuntosVida());

			if (!enemigo.estaVivo()) {
				System.out.println("¡" + enemigo.getNombre() + " ha sido derrotado!");
				break;
			}

			// Turno criatura

			int danioRecibido = enemigo.atacar(person); // <- aquí nace danioRecibido
			System.out.println(enemigo.getNombre() + " hace " + danioRecibido + " de daño. Vida de "
					+ person.getNombre() + ": " + person.getPuntosVida());

			if (!person.estaVivo()) {
				System.out.println("¡" + person.getNombre() + " ha caído en combate!");
				break;
			}
		}
	}

}

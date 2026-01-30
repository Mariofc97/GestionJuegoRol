package entities.episodios;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.weaver.patterns.PerObject;

import entities.Personaje;
import utilidades.Utils;

public class Episodio5JefeCueva {

	private static final Logger LOGGER = Logger.getLogger(Episodio5JefeCueva.class.getName());
	static {
		LOGGER.setUseParentHandlers(false);
	}

	public static void episodio5JefeClan(Personaje personaje) {

		if (personaje == null) {
			LOGGER.warning("Se llamó a episodio5 con Personaje null");
			System.out.println("Error: personaje no proporcionado.");
			return;
		}

		// Aseguramos listas
		if (personaje.getEquipo() == null) {
			personaje.setEquipo(new java.util.ArrayList<>());
		}

		if (personaje.getCriaturas() == null) {
			personaje.setCriaturas(new java.util.ArrayList<>());
		}

		// Texto inicial (SIEMPRE se muestra)
		System.out.println("Tras atravesar el bosque oscuro, llegas a un río caudaloso que bloquea tu camino. "
				+ "El agua corre lentamente, limpia y clara... enseguida se te ocurren muchas cosas que podrías hacer.");

		boolean jefekey1 = false;
		boolean jefekey2 = false;
		boolean salida = false;
		int contadorCorrer = 0;
		int contadorGolpes = 0;
		int vidaInicial = personaje.getPuntosVida();
		int ataqueInicial = personaje.getPuntosAtaque();
		do {

			System.out.println("1. Salir corriendo por la presión\n" + "2. Darte golpes en el pecho\n"
					+ "3. Descansar\n" + "4. Invocar criaturas\n" + "5. Inventario y estado\n" + "6. Fabricar\n"
					+ "7. Buscar materiales\n" + "8. Desafiar Jefe del Clan");

			int opcion = Utils.pideDatoNumerico("¿Qué quieres hacer?");

			switch (opcion) {

			case 1: {// si corres más de 5 veces pierdes todos los aunemntos y no puedes hacer más

				if (contadorCorrer > 5) {
					System.out.println(
							"Como no lloron, encima cobarde... pierdes todas los aumentos de vida y ya no podras tener más aumentos de vida. ");
					personaje.setPuntosVida(vidaInicial);

				} else {
					System.out.println(
							"Sales corriendo presa del miedo, llorando... pero algo cambia en ti, te paras respiras y empiezas a dominar la congoja, vuelves con + 50 en vida.");
					contadorCorrer++;
					int vidaSubida = personaje.getPuntosVida() + 50;
					personaje.setPuntosVida(vidaSubida);
					jefekey1 = true;

				}
				break;
			}

			case 2: {

				if (contadorGolpes > 5) {
					System.out.println(
							"Eres un bruto te has golpeado tanto que te has roto dos costillas, tu vida es 1 y ya no podras tener más aumentos de ataque");
					personaje.setPuntosVida(1);

				} else {
					System.out.println(
							"Golpeas tu pecho y ruges. El clan te observa con atención demuestra lo que vales!!!! Ganas +10 en ataque");
					contadorGolpes++;
					int ataqueSubido = personaje.getPuntosAtaque() + 10;
					personaje.setPuntosAtaque(ataqueSubido);

				}

				break;
			}

			case 3: {
				Utils.recuperarVida(personaje);
				System.out.println("Has descansado y recuperado toda la vida.");
				LOGGER.info("Descanso completo de: " + personaje.getNombre());
				break;
			}

			case 4: {
				Utils.invocarTodasCriaturas(personaje);
				break;
			}

			case 5: {
				try {
					Utils.menuInventario(personaje);
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error al mostrar inventario", e);
					System.out.println("No se pudo mostrar el inventario.");
				}
				break;
			}

			case 6: {
				Utils.menuFabricar(personaje);
				break;
			}

			case 7: {
				try {
					Utils.buscarObjeto(personaje);
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error al buscar objeto", e);
					System.out.println("No se pudo buscar el objeto.");
				}
				break;
			}

			case 8: {
				System.out.println("Te preparas para desafiar al jefe del clan...");
				// aquí irá el combate
				jefekey2 = true;

				break;
			}

			default:
				System.out.println("Opción no válida.");
			}

			// solo cuando se ha pasado por case 1 y case 2
			salida = jefekey1 && jefekey2;

		} while (!salida);

		System.out.println("Tras demostrar tu valor y determinación, el clan te observa de otra manera.\n"
				+ "Ha llegado el momento de enfrentarte al jefe.");
	}
}

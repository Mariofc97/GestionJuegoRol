package entities.episodios;

import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Personaje;
import entities.equipo.objetos.Baya;
import entities.equipo.objetos.HojaParaLimpiar;
import exceptions.ReglaJuegoException;
import utilidades.Utils;

public class Episodio2 {
	// FIXME: hay que declarar las keys como static para que se mantengan entre
	// TODO: FALTA REPASAR CONTADOR DE EPISODIO2 PARA QUE FUNCIONE BIEN ENTRE
	// llamadas!!!!!!!!
	static int contadorEpisodio2 = 0;
	// Logger específico para esta clase
	private static final Logger LOGGER = Logger.getLogger(Episodio2.class.getName());

	static {
		LOGGER.setUseParentHandlers(false); // evita que el logger escriba en consola
	}

	public static void episodio2(Personaje personaje) {
		// Comprobación inicial: si no nos pasan un personaje, salimos con un mensaje de
		// error

		if (personaje == null) {
			LOGGER.warning("Se llamó a episodio2 con Personaje null");
			System.out.println("Error: personaje no proporcionado.");
			return;
		}

		// Asegurarnos de que la lista de equipo exista para evitar NullPointerException
		if (personaje.getEquipo() == null) {
			try {
				// Inicializamos una lista vacía si no existe
				java.util.List<entities.equipo.Equipamiento> equipoList = new java.util.ArrayList<>();
				personaje.setEquipo(equipoList);
				LOGGER.info("Se inicializó la lista de equipo para el personaje: " + personaje.getNombre());
			} catch (Exception e) {
				// Si falla la inicialización la registramos pero no abortamos el episodio
				LOGGER.log(Level.WARNING, "No se pudo inicializar la lista de equipo", e);
			}
		}

		// Asegurarnos de que la lista de criaturas exista para evitar
		// NullPointerException
		if (personaje.getCriaturas() == null) {
			try {
				java.util.List<entities.criatura.Criatura> criaturasList = new java.util.ArrayList<>();
				personaje.setCriaturas(criaturasList);
				LOGGER.info("Se inicializó la lista de criaturas para el personaje: " + personaje.getNombre());
			} catch (Exception e) {
				LOGGER.log(Level.WARNING, "No se pudo inicializar la lista de criaturas", e);
			}
		}

		boolean episodio2key1 = false;
		boolean episodio2key2 = false;
		boolean episodio2key3 = false;
		if (contadorEpisodio2 == 0) {

			episodio2key1 = false;
			episodio2key2 = false;
			episodio2key3 = false;
		} else {
			episodio2key1 = true;
			episodio2key2 = true;
			episodio2key3 = true;
		}
		boolean salida = false;

		do {

			System.out.println(
					"1. Buscar bayas \t2. Cazar \t3. Crear arma \t4. Esconderse del miedo \t5.Inventario y estado \t6.Buscar materales. \t7.Ir al bosque oscuro. \t8.Descansar");
			System.out.println("dila opcion del menu");
			int opcion = Utils.pideDatoNumerico("Que quieres hacer?");

			switch (opcion) {

			case 1: {
				// buscar bayas
				Utils.buscarBaya(personaje);

				episodio2key1 = true;
			}
				break;

			case 2: {
				// cazar
				try {
					int puntosdeExperienciaAntesCazar = personaje.getExperiencia();
					System.out.println("Intentando cazar...");
					Utils.cazar(personaje);
					if (personaje.getExperiencia() > puntosdeExperienciaAntesCazar) {
						episodio2key2 = true;
						System.out.println("Caza realizada con éxito.");
						LOGGER.info("El personaje " + personaje.getNombre() + " ha cazado con éxito.");
					}
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error al cazar", e);
					System.out.println("No se pudo cazar.");
				}
			}
				break;

			case 3: {
				try {
					Utils.construirArma(personaje);
					episodio2key3 = true;
				} catch (ReglaJuegoException e) {
					System.out.println("No puedes fabricar: " + e.getMessage());
				}
			}
				break;

			case 4: {
				// esconderse del miedo
				System.out.println(
						"Te escondes entre los arbustos, intentando calmar tu respiración agitada y el latido acelerado de tu corazón. Mientras esperas, escuchas los sonidos del bosque que poco a poco vuelven a la normalidad. Después de unos minutos, te sientes lo suficientemente tranquilo como para salir de tu escondite.");

				episodio2key3 = true;

			}
				break;
			case 5: {
				// inventario
				try {
					Utils.menuInventario(personaje);
					LOGGER.info("Mostrando inventario de: " + personaje.getNombre());
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error al mostrar el inventario", e);
					System.out.println("No se pudo mostrar el inventario.");
				}
			}
				break;
			case 6: {
				// buscar materiales
				// Caso 6: buscar objeto
				try {
					Utils.buscarObjeto(personaje);
					LOGGER.info("El personaje " + personaje.getNombre() + " ha buscado un objeto.");
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error al buscar objeto", e);
					System.out.println("No se pudo buscar el objeto.");
				}
			}
				break;
			case 7: {
				// ir al bosque oscuro
				if (episodio2key1 && episodio2key2 && episodio2key3) {
					salida = true;
					System.out.println("Ya puedes ir al bosque oscuro.");
				}
				break;

			}
			case 8: {
				// descansar
				try {
					personaje.setPuntosVida(personaje.getPuntosVidaMax());
					String msg = "Has descansado y recuperado toda la vida.";
					System.out.println(msg);
					LOGGER.info(msg + " Personaje: " + personaje.getNombre());
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "Error al ejecutar opción descansar", e);
					System.out.println("No se pudo descansar correctamente.");
				}
			}
				break;
				
			default:
				System.out.println("Opción no válida");
			}

		} while (!salida);

	}
}

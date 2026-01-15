package entities.episodios;

import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Personaje;
//TODO: FALTA REPASAR CONTADOR DE EPISODIO3 PARA QUE FUNCIONE BIEN ENTRE
import exceptions.ReglaJuegoException;
import utilidades.Utils;

//cosas que hacer en el bosque oscuro, como encontrar objetos, criaturas, luchar contra enemigos.

public class Episodio3ElBosqueOscuro {
	static int contadorEpisodio3 = 0;
	private static final Logger LOGGER = Logger.getLogger(Episodio3ElBosqueOscuro.class.getName());
	static {
		LOGGER.setUseParentHandlers(false); // evita que el logger escriba en consola
	}

	public static void episodio3ElBosqueOscuro(Personaje personaje) {

		if (personaje == null) {
			LOGGER.warning("Se llamó a episodio3ElBosqueOscuro con Personaje null");
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
				LOGGER.log(java.util.logging.Level.WARNING, "No se pudo inicializar la lista de equipo", e);
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
				LOGGER.log(java.util.logging.Level.WARNING, "No se pudo inicializar la lista de criaturas", e);
			}
		}
boolean salida = false;
		boolean bosqueOscurokey1 = false;
		boolean bosqueOscurokey2 = false;
		boolean bosqueOscurokey3 = false;
		if (contadorEpisodio3 == 0) {
			System.out.println(
					"Te adentras en el Bosque Oscuro, un lugar lleno de misterios y peligros. A medida que avanzas, sientes que los árboles susurran a tu alrededor.");
			contadorEpisodio3++;
		}
		do {

			System.out.println(
					"1. Buscar bayas \t2. Cazar \t3. Crear arma \t4. Usar trampa \t5.Inventario y estado \t6.Buscar materales. \7.Ir al rio.");
			System.out.println("di la opcion del menu");
			int opcion = Utils.pideDatoNumerico("Que quieres hacer?");

			switch (opcion) {

			case 1: {
				// buscar bayas
				// TODO: HAY Q AÑADIR QUE AL BUSCAR BAYAS NOS ENCONTRAMOS CON UN JABALI Y PELA
				// BOSH FACIL
				Utils.buscarBaya(personaje);

			}
				break;

			case 2: {
				// cazar
				try {
					int puntosdeExperienciaAntesCazar = personaje.getExperiencia();
					System.out.println("Intentando cazar...");
					Utils.cazar(personaje);
					if (personaje.getExperiencia() > puntosdeExperienciaAntesCazar) {
						bosqueOscurokey1 = true;
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
					// TODO: FALTA AÑADIR TRAMPA PARA PELEA CON JABALI O LOBO
					Utils.construirArma(personaje);

				} catch (ReglaJuegoException e) {
					System.out.println("No puedes fabricar: " + e.getMessage());
				}
			}
				break;

			case 4: {
				// USAR TRAMPA
				// TODO: AQUI CUANDO USAMOS TRAMPO ATRAPAMOS UN CONEJO O SIMILAR, PERO ATRAEMOS
				// UN LOBO PELEA BOSH FACIL

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
				// ir al rio hay q hacer las key
				if (bosqueOscurokey1 && bosqueOscurokey2 && bosqueOscurokey3) {
					salida = true;
					System.out.println("Ya puedes ir al bosque oscuro.");
				}
				break;

			}

			default:
				System.out.println("Opción no válida");
			}

		} while (!salida);
	}

}

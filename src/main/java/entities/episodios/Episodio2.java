package entities.episodios;

import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Personaje;
import entities.equipo.objetos.Baya;
import entities.equipo.objetos.HojaParaLimpiar;
import utilidades.Utils;

public class Episodio2 {
	// FIXME: hay que declarar las keys como static para que se mantengan entre
	// llamadas!!!!!!!!
	static int contadorEpisodio1 = 0;
	// Logger específico para esta clase
	private static final Logger LOGGER = Logger.getLogger(Episodio2.class.getName());

	static {
		LOGGER.setUseParentHandlers(false); // evita que el logger escriba en consola
	}

	public static void episodio2(Personaje personaje) {
		boolean key1 = false;
		boolean key2 = false;
		boolean key3 = false;

		boolean salida = false;

		do {

			System.out.println(
					"1. Buscar bayas \t2. Cazar \t3. Crear arma \t4. Volver a la cueva \t5.Inventario y estado \t6.Buscar materales. \t7.Ir al bosque oscuro \t8.Ir al rio");
			System.out.println("dila opcion del menu");
			int opcion = Utils.pideDatoNumerico("Que quieres hacer?");

			switch (opcion) {

			case 1: {
				// buscar bayas
				Utils.buscarBaya(personaje);

				key1 = true;
			}
				break;

			case 2: {
				// cazar
				Utils.cazar(personaje);
				key2 = true;
			}
				break;

			case 3: {
				// crear arma
				key3 = true; // <-- IMPORTANTE PARA PODER SALIR
			}
				break;

			case 4: {
				// volver a la cueva
				// llamamos al episodio 1 y sumamos uno a contadorEpisodio1!!!!!!!
				Episodio1.contadorEpisodio1++;

				key3 = true;

			}
				break;
			case 5: {
				// inventario
			}
				break;
			case 6: {
				// buscar materiales
				// Caso 6: buscar objeto
				// hacer el control de exdesde aqui.
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
			}
				break;

			case 8: {
				// Ir al rioç
				// Actualizamos la condición de salida **dentro del bucle**
				if (key1 && key2 && key3) {
					salida = true;
					System.out.println("Ya puedes ir al rio a darte lavarte, que se te huele en todo el valle....");
					break;
				}

			}
				break;

			default:
				System.out.println("Opción no válida");
			}

		} while (!salida);

	}
}

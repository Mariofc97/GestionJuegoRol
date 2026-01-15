package entities.episodios;

import java.util.logging.Logger;

import entities.Personaje;
//TODO: FALTA REPASAR CONTADOR DE EPISODIO3 PARA QUE FUNCIONE BIEN ENTRE

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

		boolean bosqueOscurokey1 = false;
		boolean bosqueOscurokey2 = false;
		boolean bosqueOscurokey3 = false;
		if (contadorEpisodio3 == 0) {
			System.out.println(
					"Te adentras en el Bosque Oscuro, un lugar lleno de misterios y peligros. A medida que avanzas, sientes que los árboles susurran a tu alrededor.");
			contadorEpisodio3++;
		}
	}

}

package entities.episodios;

import java.util.logging.Logger;

import entities.Personaje;
import service.CriaturaService;
import service.EquipamientoService;
import service.impl.CriaturaServiceImpl;
import service.impl.EquipamientoServiceImpl;

public class Episodio5JefeCueva {
//combate con el jefe de clan, dos resultados pierdes la mayoria de las vez pero se te reconoce como miembro valioso del clan, recuperas tu familia.....
	// ganas el combate... en un estado de locura matas al jefe brutalmente... y te
	// das cuenta que ha sido un error, todo el clan té mira, con ojos de asombro,
	// miedo, rechazo, no consigues nada, es más lo pierdes todo, eres desterrado.
	static int contadorEpisodio5 = 0;
	private static final Logger LOGGER = Logger.getLogger(Episodio5JefeCueva.class.getName());
	static {
		LOGGER.setUseParentHandlers(false); // evita que el logger escriba en consola
	}

	// comienza las validaciones de personaje y listas

	public static void episodio5JefeClan(Personaje personaje) {
		EquipamientoService equipService = new EquipamientoServiceImpl();
		CriaturaService criaturaService = new CriaturaServiceImpl();
		if (personaje == null) {
			LOGGER.warning("Se llamó a episodio5 con Personaje null");
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

		if (contadorEpisodio5 == 0) {
			System.out.println(
					"Tras atravesar el bosque oscuro, llegas a un río caudaloso que bloquea tu camino. El agua corre lentamente, limpia y clara ... enseguida se te ocurren muchas cosas que podrias haces.");
			contadorEpisodio5++;
		}
		// empezamos do while...
		// aciones:
//		1.SALIR CORRIENDO DE LA CONGOJA (SALES CORRIENDO PERO TE VIENES ARRIBA Y VUELVES A POR EL JEFE), 
//		2.DARTE GOLPES EN EL PECHO (AUMENTO DE ATRIBUTOS HAY QUE LIMITARLO) SI ABUSAS TE ACABAS HACIENDO DAÑO, 
//		3.DESCANSAR, 
//		4.INVOCAR, 
//		5.EQUIPO, 
//		6.CONSTRUIR, 
//		7.BUSCAR MATERIALES 
//		8.VAMOS A POR EL JEFE

	}
}

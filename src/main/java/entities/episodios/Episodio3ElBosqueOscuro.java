package entities.episodios;

import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Personaje;
import entities.criatura.Jabali;
import entities.criatura.Lobo;
import entities.equipo.armas.Trampa;
import entities.equipo.objetos.Baya;
import entities.equipo.objetos.CarneSeca;
import entities.equipo.objetos.HojaParaLimpiar;
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
		boolean controladorAtaqueLobo = false; // false no lo derrotado aun, true derrotado
		boolean controladorJabali = false; // false no lo derrotado aun, true derrotado
		if (contadorEpisodio3 == 0) {
			contadorEpisodio3++;
		}

		System.out.println();
		System.out.println("\"EPISODIO 3: El Bosque Oscuro\"");
		System.out.println("Te adentras en el Bosque Oscuro, un lugar lleno de misterios y peligros.");
		System.out.println("A medida que avanzas, sientes que los árboles susurran a tu alrededor.");
		System.out.println("Algo acecha...");
		System.out.println();

		do {

			System.out.println(
					"1. Buscar bayas \n2. Cazar \n3. Crear arma \n4. Usar trampa \n5.Inventario y estado \n6.Buscar materales. \n7.Ir al rio. \n8.Descansar. \n9.Invocar lobo y jabali.");
			System.out.println("di la opcion del menu");
			int opcion = Utils.pideDatoNumerico("Que quieres hacer?");

			switch (opcion) {

			case 1: {
				// buscar bayas
				// TODO: HAY Q AÑADIR QUE AL BUSCAR BAYAS NOS ENCONTRAMOS CON UN JABALI Y PELA
				// BOSH FACIL
				try {
					Utils.buscarBaya(personaje);
					if (controladorJabali == false) {
						System.out.println(
								"Ummm que ricas las bayas, escuchas un ruido... más alto... de repente un jabali salvaje aparece buscando comida y te ataca.");
						Jabali jabali = new Jabali();
						int puntosdeExperienciaAntesJabali = personaje.getExperiencia();

						Utils.combate(personaje, jabali);
						if (personaje.getExperiencia() > puntosdeExperienciaAntesJabali) {
							System.out.println("Has sobrevivido al ataque del jabali y conseguido bayas.");

							controladorJabali = true;
							// Aquí podrías implementar un menú para seleccionar cuál usar
							personaje.addEquipamiento(new Baya());
							bosqueOscurokey1 = true; // derrotar al lobo es necesario para salir del episodio
						}
					}
					if (controladorJabali == true) {
						System.out.println("Bien has encontrado bayas!!!!.");
						personaje.addEquipamiento(new Baya());
					}

				} catch (ReglaJuegoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
				break;

			case 2: {
				// cazar
				try {
					int puntosdeExperienciaAntesCazar = personaje.getExperiencia();
					System.out.println("Intentando cazar...");
					Utils.cazar(personaje);
					if (personaje.getExperiencia() > puntosdeExperienciaAntesCazar) {
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
				// TODO: HAY QUE HACER LO UN METODO.

				int contadorTrampas = 0;

				for (Object obj : personaje.getEquipo()) {
					if (obj instanceof Trampa) {
						contadorTrampas++;
					}

				}
				if (contadorTrampas == 0) {
					System.out.println("No tienes trampas en tu inventario.");
				}
				if (contadorTrampas > 0 && controladorAtaqueLobo == false) {
					System.out.println(
							"Bien has atrapado un conejo!!!! te acercas despacio pero... siente como algo te esta acechando.... la trampa era para el conejo,,, pero,,, te ataca un lobo que tambien quiere el conejo.");
					Lobo lobo = new Lobo();
					int puntosdeExperienciaAntesLobo = personaje.getExperiencia();

					Utils.combate(personaje, lobo);
					if (personaje.getExperiencia() > puntosdeExperienciaAntesLobo) {
						System.out.println("Has sobrevivido al ataque del lobo y conseguido el conejo.");

						controladorAtaqueLobo = true;
						// Aquí podrías implementar un menú para seleccionar cuál usar
						personaje.addEquipamiento(new CarneSeca());
						bosqueOscurokey1 = true; // derrotar al lobo es necesario para salir del episodio
					}
				}
				if (controladorAtaqueLobo == true) {
					System.out.println("Bien has atrapado un conejo!!!!.");
					personaje.addEquipamiento(new CarneSeca());
				}
			}
				// USAR TRAMPA
				// TODO: AQUI CUANDO USAMOS TRAMPO ATRAPAMOS UN CONEJO O SIMILAR, PERO ATRAEMOS
				// UN LOBO PELEA BOSH FACIL

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
			case 8: {
				// descansar y recuperar vida
				Utils.recuperarVida(personaje);
				String msg = "Has descansado y recuperado toda la vida.";
				System.out.println(msg);
				LOGGER.info(msg + " Personaje: " + personaje.getNombre());
				bosqueOscurokey2 = true;

			}
				break;
			case 9: {
				// invocar lobo y jabali
				if (controladorAtaqueLobo && controladorJabali) {
					System.out.println("ya puedes invocar a tu lobo o jabali compañero.");
					bosqueOscurokey3 = true;
					Utils.invocarLoboJavali(personaje);

				} else {
					System.out.println("Aun no has derrotado a un lobo y un jabali, no puedes invocarlos.");
				}

			}
				break;

			default:
				System.out.println("Opción no válida");
			}

		} while (!salida);
	}

}

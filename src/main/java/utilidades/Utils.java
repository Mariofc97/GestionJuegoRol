package utilidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entities.Personaje;
import entities.criatura.Conejo;
import entities.criatura.Criatura;
import entities.criatura.Gusano;
import entities.criatura.Mosquito;
import entities.criatura.Raton;
import entities.equipo.Equipamiento;
import entities.equipo.Escudos;
import entities.equipo.Pocion;
import entities.equipo.armas.Armas;

public class Utils {

	protected static final Logger log = LoggerFactory.getLogger(Utils.class);
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
		int cantidad = person.getCriaturas().size();
		if (cantidad >= 5) {
			System.out.println("No puedes tener más de cinto compañeros");
			return null;
		}
		Criatura compi = randomizarCriatura();
		boolean resultado = dadoDiez() > 1; // 90% de exito

		if (resultado) {

			person.getCriaturas().add(compi);
			System.out.println("Ahora tienes un compañero de viaje, ¿quieres ponerle un alias?:");
			String alias = pideDatoCadena("Introduce el alias deseado: ");
			if (alias.isEmpty()) {
				alias = compi.getNombre();
			} else {
				System.out.println("Has decidido llamar a tu criatura: " + alias);
			}
			compi.setAlias(alias);
		} else {
			System.out.println("No estas pensado en lo que debes, al invocar la criatura se rie de ti y te ataca.");
			person.setPuntosVida(person.getPuntosVida() - compi.getPuntosAtaque());
			System.out.println("Te ha quitado " + compi.getPuntosAtaque() + " puntos de vida, te quedan "
					+ person.getPuntosVida() + " puntos de vida.");
		}

		return compi;
	}

	// este random solo esta hecho con 4 criaturas, habrá que meter mas si se
	// generan mas
	public static Criatura randomizarCriatura() {

		int tirada = ThreadLocalRandom.current().nextInt(1, 5);

		Criatura c;
		switch (tirada) {
		case 1:
			System.out.println("Tirada de criatura: Gusano.");
			c = new Gusano();
			break;
		case 2:
			System.out.println("Tirada de criatura: Conejo.");
			c = new Conejo();
			break;
		case 3:
			System.out.println("Tirada de criatura: Mosquito.");
			c = new Mosquito();
			break;
		default:
			System.out.println("Tirada de criatura: Raton.");
			c = new Raton();
			break;
		}
		return c;
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
		String[] nombres = { "Hay tormenta y te cae un rayo, hueles a pelo quemado.",
				"Hay ventisca tropiezas y te caes por el acantilado de al lado de la cueva, te partes dos costillas.",
				"Esta helando y no tienes ropa, coges una hipotermia.",
				"Hay una ola de calor y te deshidratas, te rescatan los niños.",
				"Habia un dientes de sable acechando y sales con vida gracias a que hueles muy mal y no ha querido comerte.",
				"El día es explendido te distraes disfrutando de la tarde y un mamut te arrolla.",
				"Los extraterrestre te secuestran y experimenta contigo, estas para el arrastre.",
				"Esta lloviendo mucho y decides volver, pero al volver te pilla una riada, casi te ahogas." };
		String nombre = nombres[ThreadLocalRandom.current().nextInt(nombres.length)];
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
							+ enemigo.getNombre() + " te pisa la cara y te deja a un punto de vida.");
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

	private static void mostrarEquipoCompleto(Personaje person) {
		List<Equipamiento> equipo = person.getEquipo();

		if (equipo == null || equipo.isEmpty()) {
			log.error("No llevas ningun objeto encima");
			return;
		}

		System.out.println("\n--- EQUIPO COMPLETO ---");
		for (int i = 0; i < equipo.size(); i++) {
			Equipamiento e = equipo.get(i);
			String tipoEq = obtenerTipoEquipamiento(e);
			System.out.println((i + 1) + ". [" + tipoEq + "]" + e.getNombre() + " (peso: " + e.getPeso()
					+ ", durabilidad: " + e.getDurabilidad() + ")");
		}
	}
	
	private static void mostrarCompaneros(Personaje person) {
		List<Criatura> criaturasCompis = person.getCriaturas();
		
		for (Criatura criatura : criaturasCompis) {
			if(criaturasCompis == null) {
				log.error("No tienes criaturas aliadas asignadas");
			} else {
				
				System.out.println("Criatura: " + criatura.getNombre() + "|Alias: " + criatura.getAlias() + "|Puntos de vida: " + criatura.getPuntosVida() + "|Puntos de ataque: " + criatura.getPuntosAtaque() );
			}
		}
	}

	private static String obtenerTipoEquipamiento(Equipamiento e) {
		if (e instanceof Armas) {
			return "Arma";
		} else if (e instanceof Escudos) {
			return "Escudo";
		} else if (e instanceof Pocion) {
			return "Pocion";
		} else {
			return "Objeto";
		}
	}

	// Este menu es para ver armas pero tambien para EQUIPAR!
	// Equipar: mover el arma elegida al principio de la lista para que
	// getArmaEquipada() pueda encontrarla primero.
	private static void menuArmas(Personaje person) {
		List<Equipamiento> equipo = person.getEquipo();

		if (equipo == null || equipo.isEmpty()) {
			log.error("No llevas armas ni objetos");
			return;
		}

		List<Armas> armas = new ArrayList<>();
		for (Equipamiento e : equipo) {
			if (e instanceof Armas) {
				// casteo de equipo a arma
				armas.add((Armas) e);
			}
		}

		if (armas.isEmpty()) {
			System.out.println("No tienes ningun arma en el inventario");
			return;
		}
		System.out.println("\n--- ARMAS ---");
		for (int i = 0; i < armas.size(); i++) {
			Armas a = armas.get(i);
			System.out.println((i + 1) + ". " + a.getNombre() + " daño: " + a.getPuntosDaño() + " ,tipo de daño: "
					+ a.getTipoDaño());
		}

		System.out.println((armas.size() + 1) + ". Volver");
		int opcion = pideDatoNumerico("Elige un arma para equipar (o pulsa" + (armas.size() + 1) + " para volver):");

		if (opcion < 1 || opcion > armas.size()) {
			System.out.println("Volviendo sin cambiar el arma.");
			return;
		}

		Armas seleccionada = armas.get(opcion - 1);

		equipo.remove(seleccionada);
		equipo.add(0, seleccionada);

		System.out.println("Has equipado el arma: " + seleccionada.getNombre());

	}

	private static void menuUsarPocion(Personaje person) {
		List<Equipamiento> equipo = person.getEquipo();
		if (equipo == null || equipo.isEmpty()) {
			System.out.println("No tienes objetos en el inventario.");
			return;
		}

		List<Pocion> pociones = new ArrayList<>();
		for (Equipamiento e : equipo) {
			if (e instanceof Pocion) {
				pociones.add((Pocion) e);
			}
		}

		if (pociones.isEmpty()) {
			System.out.println("No tienes ninguna poción.");
			return;
		}

		System.out.println("\n--- POCIONES ---");
		for (int i = 0; i < pociones.size(); i++) {
			Pocion p = pociones.get(i);
			System.out.println((i + 1) + ". " + p.getNombre() + " (cura: " + p.getPuntosVida() + " puntos de vida)");
		}
		System.out.println((pociones.size() + 1) + ". Volver");

		int opcion = pideDatoNumerico("Elige una poción para usar (o " + (pociones.size() + 1) + " para volver):");

		if (opcion < 1 || opcion > pociones.size()) {
			System.out.println("No usas ninguna poción.");
			return;
		}

		Pocion pocionSeleccionada = pociones.get(opcion - 1);
		// Curar al personaje
		person.usarPocion(pocionSeleccionada);
		// Eliminar la poción del inventario
		equipo.remove(pocionSeleccionada);

		System.out.println("Has usado la poción " + pocionSeleccionada.getNombre() + ". Vida actual(PV/PVMax): "
				+ person.getPuntosVida() + "/" + person.getPuntosVidaMax());
	}

	private static void menuTirarObjetoAlaMierda(Personaje person) {
		List<Equipamiento> equipo = person.getEquipo();
		if (equipo == null || equipo.isEmpty()) {
			System.out.println("No tienes nada que tirar.");
			return;
		}

		System.out.println("\n--- TIRAR OBJETO ---");
		for (int i = 0; i < equipo.size(); i++) {
			Equipamiento e = equipo.get(i);
			String tipoOb = obtenerTipoEquipamiento(e);
			System.out.println((i + 1) + ". [" + tipoOb + "] " + e.getNombre());
		}
		System.out.println((equipo.size() + 1) + ". Cancelar");

		int opcion = pideDatoNumerico(
				"Elige el objeto que quieres tirar (o pulsa " + (equipo.size() + 1) + " para cancelar):");

		if (opcion < 1 || opcion > equipo.size()) {
			System.out.println("No tiras ningún objeto.");
			return;
		}

		Equipamiento aLaMierda = equipo.get(opcion - 1);
		System.out.println(
				"Has tirado: " + aLaMierda.getNombre() + " a la mierda, te mereces una hoja de ortiga por arma.");
		equipo.remove(aLaMierda);
	}

	public static int calcularPesoTotal(Personaje person) {
		int totalPeso = 0;
		if (person.getEquipo() != null) {
			for (Equipamiento e : person.getEquipo()) {
				totalPeso += e.getPeso();
			}
		}
		return totalPeso;
	}

	public static void menuInventario(Personaje person) {
		if (person.getEquipo() == null) {
			person.setEquipo(new ArrayList<>());
		}

		boolean salirMenu = false;

		do {
			System.out.println("\n--- INVENTARIO DE " + person.getNombre() + " ---");
			System.out.println("Vida: " + person.getPuntosVida() + "/" + person.getPuntosVidaMax());
			System.out.println("Numero de objetos en inventario: " + calcularPesoTotal(person) + " unidades.");
			System.out.println("---------------------------------------------");
			System.out.println("1. Ver todo el equipo");
			System.out.println("2. Ver armas / equipar arma");
			System.out.println("3. Usar poción");
			System.out.println("4. Tirar objeto a la mierda");
			System.out.println("5. Mostrar criaturas aliadas");
			System.out.println("6. Volver");

			int opcion = pideDatoNumerico("Elige la opción deseada del inventario: ");

			switch (opcion) {
			case 1:
				mostrarEquipoCompleto(person);
				break;
			case 2:
				menuArmas(person);
				break;
			case 3:
				menuUsarPocion(person);
				break;
			case 4:
				menuTirarObjetoAlaMierda(person);
				break;
			case 5:
				mostrarCompaneros(person);;
				break;
			case 6:
				salirMenu = true;
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (!salirMenu);
	}

	public static double pideDatoDecimal(String texto) {
		double numero = 0;
		boolean hayError;
		do {

			System.out.println(texto);
			Scanner scan = new Scanner(System.in);

			try {
				numero = scan.nextDouble();
				hayError = false;
			} catch (InputMismatchException ime) {
				hayError = true;
				System.out.println("Valor introducido no correcto");
			} finally {
				scan.close();
			}

		} while (hayError);

		return numero;

	}

	public static String pideDatoCadena(String texto) {
		String dato = "";
		System.out.println(texto);
		Scanner scan = new Scanner(System.in);
		dato = scan.nextLine();

		return dato;
	}

	public static BigDecimal pideDatoBigDecimal(String texto) {

		try {
			System.out.println(texto);
			Scanner scan = new Scanner(System.in);
			BigDecimal numero = scan.nextBigDecimal();

			return numero;

		} catch (Exception e) {
			System.out.println("Error general " + e.getMessage());
			System.out.println("El dato introducido debe ser un número decimal (ej: 1234.56)");

			// Volvemos a preguntar recursivamente
			return pideDatoBigDecimal(texto);
		}
	}

}

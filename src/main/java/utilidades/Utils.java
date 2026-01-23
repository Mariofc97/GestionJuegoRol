package utilidades;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.CriaturaDto;
import dto.EquipamientoDto;
import entities.Personaje;
import entities.criatura.Conejo;
import entities.criatura.Criatura;
import entities.criatura.Gusano;
import entities.criatura.Jabali;
import entities.criatura.Lobo;
import entities.criatura.Mosquito;
import entities.criatura.Raton;
import entities.equipo.Equipamiento;
import entities.equipo.armas.Armas;
import entities.equipo.escudos.Escudos;
import entities.equipo.objetos.Baya;
import entities.equipo.objetos.CarneSeca;
import entities.equipo.objetos.Cuerda;
import entities.equipo.objetos.HojaParaLimpiar;
import entities.equipo.objetos.MojonSeco;
import entities.equipo.objetos.Palo;
import entities.equipo.objetos.Piedra;
import entities.equipo.objetos.Pocion;
import exceptions.ReglaJuegoException;
import service.CriaturaService;
import service.EquipamientoService;
import service.impl.CriaturaServiceImpl;
import service.impl.EquipamientoServiceImpl;

public class Utils {

	private static final CriaturaService criaturaService = new CriaturaServiceImpl();
	protected static final Logger log = LoggerFactory.getLogger(Utils.class);
	// TODO
	// Metodos

	// ganarEquipo();

	/**
	 * @param ganaEquipo equipo que queremos añadir al personaje clase Equipamiento
	 * @param person     personaje al que añadimos equipo, clase Personaje
	 */
	public static void ganarEquipo(Equipamiento ganaEquipo, Personaje person) {

		person.addEquipamiento(ganaEquipo);
		System.out.println("Has ganado equipo: " + ganaEquipo.toString());

	}

	// invocacion();

	public static Criatura invocacionCompañeroCriatura(Personaje person) {

	    if (person == null || person.getId() == null) {
	        System.out.println("Error: personaje no válido o no persistido.");
	        return null;
	    }

	    // narrativa: “qué sale”
	    Criatura compiRandom = randomizarCriatura();

	    // tirada (90% éxito)
	    boolean ok = dadoDiez() > 1;
	    if (!ok) {
	        System.out.println("No estás pensando en lo que debes, la criatura se ríe de ti y te ataca.");
	        person.setPuntosVida(person.getPuntosVida() - compiRandom.getPuntosAtaque());
	        System.out.println("Te ha quitado " + compiRandom.getPuntosAtaque() +
	                " puntos de vida, te quedan " + person.getPuntosVida() + " puntos de vida.");
	        return null;
	    }

	    System.out.println("Ahora tienes un compañero de viaje, ¿quieres ponerle un alias?:");
	    String alias = pideDatoCadena("Introduce el alias deseado: ");

	    // tipo para el service (MOSQUITO/CONEJO/...)
	    String tipo = compiRandom.getClass().getSimpleName().toUpperCase();

	    try {
	        CriaturaDto dto = criaturaService.invocarCompanero(person.getId(), tipo, alias);

	        System.out.println("Has invocado una criatura: " + dto.getTipo() + " alias=" + dto.getAlias());

	        // mantener coherencia en memoria también:
	        compiRandom.setId(dto.getId());
	        compiRandom.setNombre(dto.getNombre());
	        compiRandom.setAlias(dto.getAlias());
	        compiRandom.setNivel(dto.getNivel());
	        compiRandom.setExperiencia(dto.getExperiencia());
	        compiRandom.setPuntosVida(dto.getPuntosVida());
	        compiRandom.setPuntosAtaque(dto.getPuntosAtaque());
	        person.addCriatura(compiRandom);

	        return compiRandom;

	    } catch (ReglaJuegoException e) {
	        System.out.println("No se pudo invocar: " + e.getMessage());
	        return null;
	    }
	}

	public static int contarHojas(Personaje personaje) {
		int contador = 0;

		for (Object obj : personaje.getEquipo()) {
			if (obj instanceof HojaParaLimpiar) {
				contador++;
			}
		}

		return contador;
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

		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		String callerMethod = "desconocido";

		// 0=getStackTrace, 1=dadoNumeroDefine, 2=dadoDiez (si viene de ahí), 3=llamador
		// real
		if (st.length > 3) {
			callerMethod = st[3].getMethodName(); // SOLO el nombre del método
		}

		System.out.println("[TIRADA DE DADO] d" + numero + " -> " + tirada + " (en " + callerMethod + ")");
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

	public static boolean combate(Personaje person, Criatura enemigo) {

		boolean ganador = false;
		pausa(500);
		System.out.println("\n==============================");
		System.out.println("        EMPIEZA EL COMBATE!		");
		System.out.println("\n " + person.getNombre() + " VS " + enemigo.getNombre());
		System.out.println("==============================\n");

		if (!person.tieneArmaEquipada()) {
			System.out.println(
					"El personaje " + person.getNombre() + " no tiene arma equipada. No puedes combatir sin arma.");
			System.out.println(enemigo.getNombre() + " te revienta y te deja a 1 punto de vida.");
			person.setPuntosVida(1);
			System.out.println("Escapas como puedes. PV: " + person.getPuntosVida());
			return false;
		}

		if (person.getCriaturas() == null || person.getCriaturas().isEmpty()) {
			System.out.println("No puedes combatir sin un compañero criatura. Primero invoca uno.");
			return false;
		}

		int turno = 1;

		while (person.estaVivo() && enemigo.estaVivo() && person.tieneArmaEquipada()) {

			System.out.println("\n--- TURNO " + turno + " ---");
			mostrarEstadoCombate(person, enemigo);

			System.out.println("\nQue haces?");
			System.out.println("1) Atacar");
			System.out.println("2) Consumir objeto (Baya / CarneSeca / Pocion)");
			System.out.println("3) Huir");

			int opcion = pideDatoNumerico("Elige: ");

			if (opcion == 3) {
				System.out.println("Huyes del combate como buen cobarde que eres...");
				return false;
			}

			if (opcion == 2) {
				boolean consumido = consumirCurativo(person);
				if (!consumido) {
					System.out.println("No consumes nada.");
				}
				pausa(300);
			} else {
				int danioHecho = person.atacar(enemigo);
				System.out.println(person.getNombre() + " hace " + danioHecho + " de daño a " + enemigo.getNombre());
				System.out.println("Vida del enemigo: " + enemigo.getPuntosVida());

				pausa(300);

				if (!enemigo.estaVivo()) {
					person.ganarExperiencia();
					System.out.println(enemigo.getNombre() + " ha sido derrotado.");
					ganador = true;
					break;
				}

				// Turno del compañero
				Criatura companero = obtenerCompaneroActivo(person);
				if (companero != null && enemigo.estaVivo()) {
					int danioComp = companero.atacar(enemigo);
					System.out.println(companero.getAlias() + " (" + companero.getNombre() + ") hace " + danioComp
							+ " de dano a " + enemigo.getNombre());
					System.out.println("Vida del enemigo: " + enemigo.getPuntosVida());

					if (!enemigo.estaVivo()) {
						person.ganarExperiencia();
						System.out.println(enemigo.getNombre() + " ha sido derrotado.");
						ganador = true;
						break;
					}
				}
			}

			System.out.println("\nTurno de " + enemigo.getNombre() + "...");
			pausa(300);

			int danioRecibido = enemigo.atacar(person);
			System.out.println(enemigo.getNombre() + " hace " + danioRecibido + " de daño. Vida de "
					+ person.getNombre() + ": " + person.getPuntosVida());

			pausa(300);

			if (!person.estaVivo()) {
				System.out.println("...Has perdido...");
				System.out.println(person.getNombre() + " ha caido en combate.");
				ganador = false;
				break;
			}

			turno++;
		}

		System.out.println("\n==============================");
		System.out.println("        FIN DEL COMBATE");
		System.out.println("==============================\n");
		return ganador;
	}

	private static Criatura obtenerCompaneroActivo(Personaje person) {
		if (person.getCriaturas() == null || person.getCriaturas().isEmpty())
			return null;

		// Si tu Criatura no tiene "estaVivo()", puedes quitar esta comprobación
		for (Criatura c : person.getCriaturas()) {
			if (c != null && c.estaVivo()) {
				return c;
			}
		}
		return null;
	}

	private static boolean consumirCurativo(Personaje person) {
		List<Equipamiento> equipo = person.getEquipo();
		if (equipo == null || equipo.isEmpty()) {
			System.out.println("Inventario vacio.");
			return false;
		}

		// Filtramos consumibles curativos
		List<Equipamiento> curativos = new ArrayList<>();
		for (Equipamiento e : equipo) {
			if (e instanceof Baya || e instanceof CarneSeca || e instanceof Pocion) {
				curativos.add(e);
			}
		}

		if (curativos.isEmpty()) {
			System.out.println("No tienes consumibles curativos (Baya, CarneSeca o Pocion).");
			return false;
		}

		System.out.println("\n--- CONSUMIBLES CURATIVOS ---");
		for (int i = 0; i < curativos.size(); i++) {
			Equipamiento e = curativos.get(i);
			System.out.println((i + 1) + ") " + nombreCurativo(e) + " (cura: " + curacionCurativo(e) + ")");
		}
		System.out.println((curativos.size() + 1) + ") Cancelar");

		int opcion = pideDatoNumerico("Elige un consumible: ");

		if (opcion < 1 || opcion > curativos.size()) {
			return false;
		}

		Equipamiento elegido = curativos.get(opcion - 1);

		int antes = person.getPuntosVida();
		aplicarCuracion(person, elegido);

		// Consumir: eliminar del inventario
		equipo.remove(elegido);

		int despues = person.getPuntosVida();
		System.out.println("Has consumido " + nombreCurativo(elegido) + ". PV: " + antes + " -> " + despues + " / "
				+ person.getPuntosVidaMax());

		return true;
	}

	private static String nombreCurativo(Equipamiento e) {
		if (e instanceof Baya)
			return "Baya";
		if (e instanceof CarneSeca)
			return "CarneSeca";
		if (e instanceof Pocion)
			return "Pocion";
		return e.getNombre();
	}

	private static int curacionCurativo(Equipamiento e) {
		if (e instanceof Pocion) {
			return ((Pocion) e).getPuntosDeVida();
		}
		if (e instanceof CarneSeca) {
			return 12; // ajustable
		}
		if (e instanceof Baya) {
			return 5; // ajustable
		}
		return 0;
	}

	private static void aplicarCuracion(Personaje person, Equipamiento e) {
		int cura = curacionCurativo(e);

		if (cura <= 0)
			return;

		int nuevaVida = person.getPuntosVida() + cura;
		if (nuevaVida > person.getPuntosVidaMax()) {
			nuevaVida = person.getPuntosVidaMax();
		}
		person.setPuntosVida(nuevaVida);
	}

	private static void pausa(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	private static void mostrarEstadoCombate(Personaje person, Criatura enemigo) {
		System.out.println(person.getNombre() + " PV: " + person.getPuntosVida() + "/" + person.getPuntosVidaMax());

		Criatura companero = obtenerCompaneroActivo(person);
		if (companero != null) {
			System.out.println(companero.getAlias() + " (" + companero.getNombre() + ") PV: "
					+ companero.getPuntosVida() + "/" + companero.getPuntosVida());
			// Si tu Criatura tiene "puntosVidaMax", entonces usa: companero.getPuntosVida()
			// + "/" + companero.getPuntosVidaMax()
		} else {
			System.out.println();
		}

		System.out.println(enemigo.getNombre() + " PV: " + enemigo.getPuntosVida());
	}

	public static void invocarLoboJavali(Personaje person) {
		Lobo lobo = new Lobo();

		Jabali jabali = new Jabali();

		int tirada = dadoDiez();

		if (tirada == 1) {
			System.out.println(
					"Mientras invocas al lobo un mosquito te pica y te distraes, el lobo se enfada y te ataca.");
			combate(person, lobo);
		} else if (tirada == 9) {
			System.out.println(
					"Mientras invocas al jabalí un ratón te asusta y te distraes, el jabalí se enfada y te ataca.");
			combate(person, jabali);
		} else if (tirada > 1 && tirada < 5) {
			System.out.println("Has invocado correctamente a un lobo.");
			person.addCriatura(lobo);
		} else if (tirada >= 5 && tirada < 9) {
			System.out.println("Has invocado correctamente a un jabalí.");
			person.addCriatura(jabali);
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
			// añadir diferenciacion entre armas, pociones, escudos, comida, etc

		}
	}

	public static void recuperarVida(Personaje personaje) {

		personaje.setPuntosVida(personaje.getPuntosVidaMax());

	}

	private static void mostrarCompaneros(Personaje person) {
	    if (person == null || person.getId() == null) {
	        System.out.println("No hay personaje válido.");
	        return;
	    }

	    try {
	        List<CriaturaDto> lista = criaturaService.listarPorPersonaje(person.getId());

	        if (lista.isEmpty()) {
	            System.out.println("No tienes criaturas aliadas.");
	            return;
	        }

	        for (CriaturaDto c : lista) {
	            System.out.println("Criatura: " + c.getNombre()
	                    + " | Tipo: " + c.getTipo()
	                    + " | Alias: " + c.getAlias()
	                    + " | PV: " + c.getPuntosVida()
	                    + " | ATQ: " + c.getPuntosAtaque());
	        }

	    } catch (ReglaJuegoException e) {
	        System.out.println("Error listando criaturas: " + e.getMessage());
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
	        System.out.println("No llevas armas ni objetos");
	        return;
	    }

	    List<Armas> armas = new ArrayList<>();
	    for (Equipamiento e : equipo) {
	        if (e instanceof Armas) armas.add((Armas) e);
	    }

	    if (armas.isEmpty()) {
	        System.out.println("No tienes ninguna arma en el inventario");
	        return;
	    }

	    System.out.println("\n--- ARMAS ---");
	    for (int i = 0; i < armas.size(); i++) {
	        Armas a = armas.get(i);
	        System.out.println((i + 1) + ") " + a.getNombre() +
	            " [id=" + a.getId() + "]" +
	            " daño=" + a.getPuntosDaño() +
	            " durabilidad=" + a.getDurabilidad() +
	            " nivelReq=" + a.getNivelRequerido());
	    }

	    System.out.println((armas.size() + 1) + ") Volver");
	    int opcion = pideDatoNumerico("Elige un arma para equipar:");

	    if (opcion < 1 || opcion > armas.size()) {
	        System.out.println("Volviendo sin cambiar arma.");
	        return;
	    }

	    Armas seleccionada = armas.get(opcion - 1);

	    try {
	        EquipamientoService es = new EquipamientoServiceImpl();
	        EquipamientoDto dto = es.equiparArma(person.getId(), seleccionada.getId());
	        System.out.println("Arma equipada OK: " + dto.getNombre());
	    } catch (ReglaJuegoException e) {
	        System.out.println("No puedes equipar: " + e.getMessage());
	    }
	}

	// hay que crear tambien MENUESCUDOS!!!
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
			// FIXME: completar curacion pocion EL SYSO FALTA GETPUNTOSVIDA
			System.out.println((i + 1) + ". " + p.getNombre() + " (cura: " + p.getPuntosDeVida() + " puntos de vida)");
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

	public static void mostrarEstado(Personaje person) {
		System.out.println("\n--- ESTADO DE " + person.getNombre() + " ---");
		System.out.println("Nivel: " + person.getNivel());
		System.out.println("Puntos de Vida: " + person.getPuntosVida() + "/" + person.getPuntosVidaMax());
		System.out.println("Puntos de Ataque: " + person.getPuntosAtaque());
		System.out.println("Peso total del inventario: " + calcularPesoTotal(person) + " unidades.");
	}

	public static void menuInventario(Personaje person) {
		if (person.getEquipo() == null) {
			person.setEquipo(new ArrayList<>());
		}

		boolean salirMenu = false;

		do {
			System.out.println("\n--- INVENTARIO DE " + person.getNombre() + " ---");
			System.out.println("---------------------------------------------");
			System.out.println("1. Ver estado del personaje");
			System.out.println("2. Ver todo el equipo");
			System.out.println("3. Ver armas / equipar arma");
			System.out.println("4. Usar poción");
			System.out.println("5. Tirar objeto a la mierda");
			System.out.println("6. Mostrar criaturas aliadas");
			System.out.println("7. Volver");

			int opcion = pideDatoNumerico("Elige la opción deseada del inventario: ");

			switch (opcion) {
			case 1:
				mostrarEstado(person);
				break;
			case 2:
				mostrarEquipoCompleto(person);
				break;
			case 3:
				menuArmas(person);
				break;
			case 4:
				menuUsarPocion(person);
				break;
			case 5:
				menuTirarObjetoAlaMierda(person);
				break;
			case 6:
				mostrarCompaneros(person);
				break;
			case 7:
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

	public static void buscarBaya(Personaje personaje) throws ReglaJuegoException {

		int tirada = Utils.dadoDiez();
		if (tirada <= 3) {
			System.out.println(
					"Encuentras una baya con una pinta irresistible, no huele a pis de animales... brilla bajo el sol de lo limpia que esta, te la comes... y.... maldición, suenan tus tripas como la peor tormenta que recuerdas, notas una sensación de sudor frio en el cuerpo, y empiezas a ver una luz... un voz que te recuerda.. cuidado con las bayas VENENOSAS.... por que son las que mejor aspecto tienen... Pierdes 5 de vida te has comido una baya venenosa.");
			personaje.setPuntosVida(personaje.getPuntosVida() - 5);
		} else if (tirada > 3 && tirada <= 7) {
			System.out.println("Has encontrado algunas bayas");
			personaje.addEquipamiento(new Baya());
			;
		} else if (tirada > 7) {
			System.out.println("Has encontrado muchas bayas");
			personaje.addEquipamiento(new Baya());
			personaje.addEquipamiento(new Baya());

		}

	}

	public static String cazar(Personaje person) {
		String mensaje = "";
		if (person.getCriaturas().size() == 0) {
			System.out.println("No puedes cazar sin un compañero criatura, primero invoca uno.");
			mensaje = "No puedes cazar sin un compañero criatura, primero invoca uno.";
		}
		boolean exito = dadoDiez() > 3; // 70% de exito
		Criatura presa = randomizarCriatura();
//TODO: AÑADIR METODO COMBATE SIEMPRE.
		boolean resultadoCombate = false;
		if (exito) {

			resultadoCombate = Utils.combate(person, presa);
			if (resultadoCombate) {
				CarneSeca carneSeca = new CarneSeca();
				// MARIO: LA CARNE DEBE DE TENER PESO Y DIFERENTES PUNTOS DE VIDA SEGUN LA
				// CRIATURA
				person.addEquipamiento(carneSeca);
				;
				person.ganarExperiencia();

				mensaje = "Has cazado un " + presa.getNombre() + ", consigues carne seca de " + presa.getNombre()
						+ " en el inventario.";
			}

		} else {
			int danioHecho = presa.atacar(person);
			person.setPuntosVida(person.getPuntosVida() - danioHecho);
			mensaje = "Eres mas debil que un" + presa.getNombre() + ", y al intentar cazarlo te hace " + danioHecho
					+ " de daño, huyes llorando como un niño pequeño. \tLa vida de nuestro personaje es: "
					+ person.getPuntosVida();

		}
		return mensaje;
	}

	public static void buscarObjeto(Personaje personaje) {

		int tirada = Utils.dadoDiez();
		if (tirada <= 2) {
			System.out.println(
					"metes la mano en un agujero, tocas algo y puensas... que suerte!!!! pero... resulta ser el nido de una serpiente que te muerde");
			personaje.setPuntosVida(personaje.getPuntosVida() - 5);
			return;
		} else if (tirada > 2) {
			int tirada2 = Utils.dadoDiez(); // con esta tirada escogemos el objeto
			if (tirada2 == 1 || tirada2 == 2) {
				System.out.println("Despues de buscar un rato encuentras un objeto muy util");
				personaje.addEquipamiento(new MojonSeco());
				System.out.println("Has encontrado un Mojon seco!");
			} else if (tirada2 == 3 || tirada2 == 4) {
				System.out.println("Despues de buscar un rato encuentras un objeto muy util");
				personaje.addEquipamiento(new Cuerda());
				System.out.println("Has encontrado una Cuerda!");
			} else if (tirada2 == 5 || tirada2 == 6) {
				System.out.println("Despues de buscar un rato encuentras un objeto muy util");
				personaje.addEquipamiento(new Piedra());
				System.out.println("Has encontrado una Piedra!");
			} else if (tirada2 == 7 || tirada2 == 8) {
				System.out.println("Despues de buscar un rato encuentras un objeto muy util");
				personaje.addEquipamiento(new Palo());
				System.out.println("Has encontrado un Palo!");
			} else {
				// tienes que ganar otro objeto de momento vacio
				System.out.println("Despues de buscar un rato encuentras un objeto muy util");
				personaje.addEquipamiento(new HojaParaLimpiar());
				System.out.println("Has encontrado una hoja de ortiga!");
			}

		}

		// Utils.buscarObjeto(personaje);

	}

	public static void menuFabricar(Personaje personaje) {
	    if (personaje == null || personaje.getId() == null) {
	        System.out.println("Debes tener un personaje válido.");
	        return;
	    }

	    String tipo = pideDatoCadena(
	        "¿Qué quieres fabricar? (ARCO, BUMERAN, CAZAMARIPOSAS, LANZA, HONDA, CAÑA PESCA, TRAMPA, ESCUDO MADERA, ESCUDO PIEDRA)"
	    );

	    try {
	        EquipamientoService es = new EquipamientoServiceImpl();
	        EquipamientoDto dto = es.fabricar(personaje.getId(), tipo);

	        System.out.println("Fabricado OK: " + dto.getNombre() +
	            " | durabilidad=" + dto.getDurabilidad() +
	            " | nivel requerido=" + dto.getNivelRequerido());
	    } catch (ReglaJuegoException e) {
	        System.out.println("No puedes fabricar: " + e.getMessage());
	    }
	}


}

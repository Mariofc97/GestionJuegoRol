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
import entities.criatura.Jabali;
import entities.criatura.Lobo;
import entities.criatura.Mosquito;
import entities.criatura.Raton;
import entities.equipo.Equipamiento;
import entities.equipo.Escudos;
import entities.equipo.armas.Arco;
import entities.equipo.armas.Armas;
import entities.equipo.armas.Bumeran;
import entities.equipo.armas.CanaPescar;
import entities.equipo.armas.Cazamariposas;
import entities.equipo.armas.Honda;
import entities.equipo.armas.Lanza;
import entities.equipo.armas.Trampa;
import entities.equipo.objetos.Baya;
import entities.equipo.objetos.CarneSeca;
import entities.equipo.objetos.Cuerda;
import entities.equipo.objetos.HojaParaLimpiar;
import entities.equipo.objetos.MojonSeco;
import entities.equipo.objetos.Palo;
import entities.equipo.objetos.Piedra;
import entities.equipo.objetos.Pocion;
import exceptions.ReglaJuegoException;

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

		person.addEquipamiento(ganaEquipo);
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
			person.addCriatura(compi);
			System.out.println("Ahora tienes un compañero de viaje, ¿quieres ponerle un alias?:");
			String alias = pideDatoCadena("Introduce el alias deseado: ");
			if (alias.isEmpty()) {
				alias = compi.getNombre();
			} else {
				System.out.println("Has decidido llamar a tu criatura: " + alias);
			}
			compi.setAlias(alias);
			return compi;
		} else {
			System.out.println("No estas pensado en lo que debes, al invocar la criatura se rie de ti y te ataca.");
			person.setPuntosVida(person.getPuntosVida() - compi.getPuntosAtaque());
			System.out.println("Te ha quitado " + compi.getPuntosAtaque() + " puntos de vida, te quedan "
					+ person.getPuntosVida() + " puntos de vida.");
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

	    // 0=getStackTrace, 1=dadoNumeroDefine, 2=dadoDiez (si viene de ahí), 3=llamador real
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

	public static void combate(Personaje person, Criatura enemigo) {
        pausa(500);
	    System.out.println("\n==============================");
	    System.out.println("        EMPIEZA EL COMBATE!		");
	    System.out.println("\n " + person.getNombre() + " VS " + enemigo.getNombre());
	    System.out.println("==============================\n");

	    if (!person.tieneArmaEquipada()) {
	        System.out.println("El personaje " + person.getNombre()
	                + " no tiene arma equipada. No puedes combatir sin arma.");
	        System.out.println(enemigo.getNombre() + " te revienta y te deja a 1 punto de vida.");
	        person.setPuntosVida(1);
	        System.out.println("Escapas como puedes. PV: " + person.getPuntosVida());
	        return;
	    }

	    if (person.getCriaturas() == null || person.getCriaturas().isEmpty()) {
	        System.out.println("No puedes combatir sin un compañero criatura. Primero invoca uno.");
	        return;
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
	            return;
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
	                break;
	            }
	            
	         // Turno del compañero
	            Criatura companero = obtenerCompaneroActivo(person);
	            if (companero != null && enemigo.estaVivo()) {
	                int danioComp = companero.atacar(enemigo);
	                System.out.println(companero.getAlias() + " (" + companero.getNombre() + ") hace "
	                        + danioComp + " de dano a " + enemigo.getNombre());
	                System.out.println("Vida del enemigo: " + enemigo.getPuntosVida());

	                if (!enemigo.estaVivo()) {
	                    person.ganarExperiencia();
	                    System.out.println(enemigo.getNombre() + " ha sido derrotado.");
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
	            break;
	        }

	        turno++;
	    }

	    System.out.println("\n==============================");
	    System.out.println("        FIN DEL COMBATE");
	    System.out.println("==============================\n");
	}
	
	private static Criatura obtenerCompaneroActivo(Personaje person) {
	    if (person.getCriaturas() == null || person.getCriaturas().isEmpty()) return null;

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
	    System.out.println("Has consumido " + nombreCurativo(elegido) + ". PV: " + antes + " -> " + despues
	            + " / " + person.getPuntosVidaMax());

	    return true;
	}

	private static String nombreCurativo(Equipamiento e) {
	    if (e instanceof Baya) return "Baya";
	    if (e instanceof CarneSeca) return "CarneSeca";
	    if (e instanceof Pocion) return "Pocion";
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

	    if (cura <= 0) return;

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
	        // Si tu Criatura tiene "puntosVidaMax", entonces usa: companero.getPuntosVida() + "/" + companero.getPuntosVidaMax()
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
		List<Criatura> criaturasCompis = person.getCriaturas();

		for (Criatura criatura : criaturasCompis) {
			if (criaturasCompis == null) {
				log.error("No tienes criaturas aliadas asignadas");
			} else {

				System.out.println(
						"Criatura: " + criatura.getNombre() + "|Alias: " + criatura.getAlias() + "|Puntos de vida: "
								+ criatura.getPuntosVida() + "|Puntos de ataque: " + criatura.getPuntosAtaque());
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
					+ a.getTipoDaño() + ", Precision: " + a.getPrecision());
		}

		System.out.println((armas.size() + 1) + ". Volver");
		int opcion = pideDatoNumerico("Elige un arma para equipar (o pulsa " + (armas.size() + 1) + " para volver):");

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
			personaje.addEquipamiento(new Baya());;
		} else if (tirada > 7) {
			System.out.println("Has encontrado muchas bayas");
			personaje.addEquipamiento(new Baya());
			personaje.addEquipamiento(new Baya());

		}

	}

	public static String cazar(Personaje person) {
		// añadir condicion para poder cazar
		if (person.getCriaturas().size() == 0) {
			System.out.println("No puedes cazar sin un compañero criatura, primero invoca uno.");
			return "No puedes cazar sin un compañero criatura, primero invoca uno.";
		}
		boolean exito = dadoDiez() > 3; // 70% de exito
		Criatura presa = randomizarCriatura();

		if (exito) {
			// Comida carne = new Comida(presa.getNombre() + " carne", 10);
			CarneSeca carneSeca = new CarneSeca();
			// MARIO: LA CARNE DEBE DE TENER PESO Y DIFERENTES PUNTOS DE VIDA SEGUN LA
			// CRIATURA
			person.addEquipamiento(carneSeca);;
			person.ganarExperiencia();
			System.out.println("Has cazado un " + presa.getNombre() + ", consigues carne seca de " + presa.getNombre()
					+ " en el inventario.");
			return "Has cazado un " + presa.getNombre() + ", consigues carne seca de " + presa.getNombre()
					+ " en el inventario.";

		} else {
			int danioHecho = presa.atacar(person);
			person.setPuntosVida(person.getPuntosVida() - danioHecho);
			System.out.println("Eres mas debil que un " + presa.getNombre() + ", y al intentar cazarlo te hace "
					+ danioHecho + " de daño, huyes llorando como un niño pequeño. \tLa vida de nuestro personaje es: "
					+ person.getPuntosVida());
			return "Eres mas debil que un" + presa.getNombre() + ", y al intentar cazarlo te hace " + danioHecho
					+ " de daño, huyes llorando como un niño pequeño. \tLa vida de nuestro personaje es: "
					+ person.getPuntosVida();

		}
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

	public static Armas construirArma(Personaje personaje) throws ReglaJuegoException {

		if (personaje == null || personaje.getEquipo() == null) {
			throw new ReglaJuegoException("Personaje o inventario no disponible.");
		}

		String t = Utils.pideDatoCadena(
				"Selecciona el arma que quieres fabricar: (ARCO, BUMERAN, CAZAMARIPOSAS, LANZA, HONDA, CAÑA PESCA, TRAMPA)");
		String tipo = t.trim().toUpperCase();

		Armas nuevaArma = null;

		switch (tipo) {

		case "ARCO": {
			boolean tienePalo = false;
			boolean tieneCuerda = false;

			// 1) Comprobar materiales
			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Palo)
					tienePalo = true;
				if (e instanceof Cuerda)
					tieneCuerda = true;
			}

			if (!tienePalo || !tieneCuerda) {
				throw new ReglaJuegoException("Necesitas un Palo y una Cuerda para fabricar el ARCO.");
			}

			// 2) Consumir materiales (eliminar 1 de cada)
			Equipamiento paloAEliminar = null;
			Equipamiento cuerdaAEliminar = null;

			for (Equipamiento e : personaje.getEquipo()) {
				if (paloAEliminar == null && e instanceof Palo)
					paloAEliminar = e;
				if (cuerdaAEliminar == null && e instanceof Cuerda)
					cuerdaAEliminar = e;
			}

			personaje.getEquipo().remove(paloAEliminar);
			personaje.getEquipo().remove(cuerdaAEliminar);

			// 3) Crear arma
			nuevaArma = new Arco();
			break;
		}

		case "BUMERAN": {
			boolean tienePalo = false;

			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Palo) {
					tienePalo = true;
					break;
				}
			}

			if (!tienePalo) {
				throw new ReglaJuegoException("Necesitas un Palo para fabricar el BUMERAN.");
			}

			Equipamiento paloAEliminar = null;
			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Palo) {
					paloAEliminar = e;
					break;
				}
			}
			personaje.getEquipo().remove(paloAEliminar);

			nuevaArma = new Bumeran();
			break;
		}

		case "CAZAMARIPOSAS": {
			boolean tienePalo = false;
			boolean tieneMojon = false;

			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Palo)
					tienePalo = true;
				if (e instanceof MojonSeco)
					tieneMojon = true;
			}

			if (!tienePalo || !tieneMojon) {
				throw new ReglaJuegoException("Necesitas un Palo y un Mojón Seco para fabricar el CAZAMARIPOSAS.");
			}

			Equipamiento paloAEliminar = null;
			Equipamiento mojonAEliminar = null;

			for (Equipamiento e : personaje.getEquipo()) {
				if (paloAEliminar == null && e instanceof Palo)
					paloAEliminar = e;
				if (mojonAEliminar == null && e instanceof MojonSeco)
					mojonAEliminar = e;
			}

			personaje.getEquipo().remove(paloAEliminar);
			personaje.getEquipo().remove(mojonAEliminar);

			nuevaArma = new Cazamariposas();
			break;
		}

		case "LANZA": {
			boolean tienePalo = false;
			boolean tienePiedra = false;

			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Palo)
					tienePalo = true;
				if (e instanceof Piedra)
					tienePiedra = true;
			}

			if (!tienePalo || !tienePiedra) {
				throw new ReglaJuegoException("Necesitas un Palo y una Piedra para fabricar la LANZA.");
			}

			Equipamiento paloAEliminar = null;
			Equipamiento piedraAEliminar = null;

			for (Equipamiento e : personaje.getEquipo()) {
				if (paloAEliminar == null && e instanceof Palo)
					paloAEliminar = e;
				if (piedraAEliminar == null && e instanceof Piedra)
					piedraAEliminar = e;
			}

			personaje.getEquipo().remove(paloAEliminar);
			personaje.getEquipo().remove(piedraAEliminar);

			nuevaArma = new Lanza();
			break;
		}

		case "HONDA": {
			boolean tieneCuerda = false;

			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Cuerda) {
					tieneCuerda = true;
					break;
				}
			}

			if (!tieneCuerda) {
				throw new ReglaJuegoException("Necesitas una Cuerda para fabricar la HONDA.");
			}

			Equipamiento cuerdaAEliminar = null;
			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Cuerda) {
					cuerdaAEliminar = e;
					break;
				}
			}
			personaje.getEquipo().remove(cuerdaAEliminar);

			nuevaArma = new Honda(); // antes tenías Lanza por error
			break;
		}
		case "CAÑA PESCA": {
			boolean tieneCuerda = false;
			boolean tienePalo = false;
			boolean tieneBaya = false;

			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Cuerda)
					tieneCuerda = true;
				if (e instanceof Palo)
					tienePalo = true;
				if (e instanceof Baya)
					tieneBaya = true;
			}

			if (!tieneCuerda || !tienePalo || !tieneBaya) {
				throw new ReglaJuegoException("Necesitas una Cuerda, Baya y Palo para fabricar la CAÑA PESCA.");
			}

			Equipamiento cuerdaAEliminar = null;
			Equipamiento paloAEliminar = null;
			Equipamiento bayaAEliminar = null;
			for (Equipamiento e : personaje.getEquipo()) {
				if (paloAEliminar == null && e instanceof Palo)
					paloAEliminar = e;
				if (cuerdaAEliminar == null && e instanceof Cuerda)
					cuerdaAEliminar = e;
				if (bayaAEliminar == null && e instanceof Baya)
					bayaAEliminar = e;
			}
			personaje.getEquipo().remove(cuerdaAEliminar);
			personaje.getEquipo().remove(paloAEliminar);
			personaje.getEquipo().remove(bayaAEliminar);

			nuevaArma = new CanaPescar(); // antes tenías Lanza por error
			break;
		}
		case "TRAMPA": {
			boolean tieneCuerda = false;
			boolean tienePalo = false;
			boolean tienePiedra = false;

			for (Equipamiento e : personaje.getEquipo()) {
				if (e instanceof Cuerda)
					tieneCuerda = true;
				if (e instanceof Palo)
					tienePalo = true;
				if (e instanceof Piedra)
					tienePiedra = true;
			}

			if (!tieneCuerda || !tienePalo || !tienePiedra) {
				throw new ReglaJuegoException("Necesitas una Cuerda, Baya y Piedra para fabricar la TRAMPA.");
			}

			Equipamiento cuerdaAEliminar = null;
			Equipamiento paloAEliminar = null;
			Equipamiento piedraAEliminar = null;
			for (Equipamiento e : personaje.getEquipo()) {
				if (paloAEliminar == null && e instanceof Palo)
					paloAEliminar = e;
				if (cuerdaAEliminar == null && e instanceof Cuerda)
					cuerdaAEliminar = e;
				if (piedraAEliminar == null && e instanceof Piedra)
					piedraAEliminar = e;
			}
			personaje.getEquipo().remove(cuerdaAEliminar);
			personaje.getEquipo().remove(paloAEliminar);
			personaje.getEquipo().remove(piedraAEliminar);

			nuevaArma = new Trampa(); // antes tenías Lanza por error
			break;
		}

		default:
			throw new ReglaJuegoException("Opción inválida: " + tipo);
		}

		// Añadir el arma al inventario
		personaje.addEquipamiento(nuevaArma);
		System.out.println("Has fabricado un " + nuevaArma.getNombre() + " y se ha añadido a tu inventario.");

		return nuevaArma;
	}

}

package entities.episodios;

import entities.Personaje;
import entities.equipo.Baya;
import entities.equipo.HojaParaLimpiar;
import utilidades.Utils;

public class Episodio2 {
	public static void episodio2(Personaje personaje) {
		boolean key1 = false;
		boolean key2 = false;
		boolean key3 = false;

		boolean salida = false;

		do {

			System.out.println(
					"1. Buscar bayas \t2. Cazar \t3. Crear arma \t4. Volver a la cueva \t5.Inventario \t6.Ver stado \t7.Ver armas \t8.Ir al rio");
			System.out.println("dila opcion del menu");
			int opcion = Utils.pideDatoNumerico("Que quieres hacer?");

			switch (opcion) {

			case 1: {
				// buscar bayas
				int tirada = Utils.dadoDiez();
				if (tirada <= 3) {
					System.out.println(
							"Encuentras una baya con una pinta irresistible, no huele a pis de animales... brilla bajo el sol de lo limpia que esta, te la comes... y.... maldición, suenan tus tripas como la peor tormenta que recuerdas, notas una sensación de sudor frio en el cuerpo, y empiezas a ver una luz... un voz que te recuerda.. cuidado con las bayas VENENOSAS.... por que son las que mejor aspecto tienen... Pierdes 5 de vida te has comido una baya venenosa.");
					// personaje.setVida(personaje.getVida() - 5);
					personaje.setPuntosVida(personaje.getPuntosVida() - 5);
				} else if (tirada > 3 && tirada <= 7) {
					System.out.println("Has encontrado algunas bayas");
					personaje.getEquipo().add(new Baya("Baya", 10));
				} else if (tirada > 7) {
					System.out.println("Has encontrado muchas bayas");
					personaje.getEquipo().add(new Baya("Baya", 10));
					personaje.getEquipo().add(new Baya("Baya", 10));

				}

				key1 = true;
			}
				break;

			case 2: {
				// cazar
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
				// Usar objeto
			}
				break;
			case 6: {
				// Ver objetos
			}
				break;
			case 7: {
				// Ver armas
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

package episodios;

import equipo.objetos.HojaParaLimpiar;
import personajes.Personaje;
import utilidades.Utils;

public class Episodio2 {
	public static void episodio2(Personaje personaje) {
		boolean key1 = false;
		boolean key2 = false;
		boolean key3 = false;

		boolean salida = false;

		do {

			System.out.println(
					"1. Buscar vallas \t2. Cazar \t3. Crear arma \t4. Volver a la cueva \t5.Usar objeto \t6.Ver objetos \\t7.Ver armas ");
			System.out.println("dila opcion del menu");
			int opcion = Utils.pideDatoNumerico("Que quieres hacer?");

			switch (opcion) {

			case 1: {

				key1 = true;
			}
				break;

			case 2: {
				key2 = true;
			}
				break;

			case 3: {
				key3 = true; // <-- IMPORTANTE PARA PODER SALIR
			}
				break;

			case 4: {
				key3 = true;
			}
				break;
			case 4: {
				key3 = true;
			}
				break;
			case 4: {
				key3 = true;
			}
				break;
			case 4: {
				key3 = true;
			}
				break;

			default:
				System.out.println("Opción no válida");
			}

			// Actualizamos la condición de salida **dentro del bucle**
			if (key1 && key2 && key3) {
				salida = true;
				System.out.println("Has cumplido todas las condiciones. Saliendo del episodio...");
			}

		} while (!salida);

	}
}

package episodios;

import java.util.Scanner;

import equipo.objetos.HojaParaLimpiar;
import personajes.Personaje;
import utilidades.Utils;

public class Episodio1 {

//crear un menu:necesito un switch.

	public static void episodio1(Personaje personaje) {

		boolean key1 = false;
		boolean key2 = false;
		boolean key3 = false;

		Scanner scan = new Scanner(System.in);

		boolean salida = false;
		do {

			System.out.println("1. Llorar \t2. Pensar \t3. Salir \t4. Dormir");

			int opcion = scan.nextInt();

			switch (opcion) {

			case 1: {
				HojaParaLimpiar hojadeortiga = new HojaParaLimpiar("Hoja Ortiga", 1, 1, 1);
				personaje.getEquipo().add(hojadeortiga);
				key1 = true;
				System.out.println("Has obtenido una Hoja de Ortiga.");
			}
				break;

			case 2: {
				Utils.invocacionCompañeroCriatura(personaje);
				key2 = true;
				System.out.println("Has aprendido a invocar criaturas." + notas.getCriaturas());
			}
				break;

			case 3: {
				personaje.setPuntosVida(1);
				System.out.println(Utils.desgraciaAleatorio() + personaje.getPuntosVida());
				key3 = true; // <-- IMPORTANTE PARA PODER SALIR
			}
				break;

			case 4: {
				personaje.setPuntosVida(personaje.getPuntosVidaMax());
				System.out.println("Has dormido y recuperado toda la vida.");
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

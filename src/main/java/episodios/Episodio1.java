package episodios;

import java.util.Scanner;

import equipo.objetos.HojaParaLimpiar;
import personajes.Personaje;
import utilidades.Utils;

public class Episodio1 {

//crear un menu:necesito un switch.

	public static void episodio1(Personaje notas) { // notas es el nombre del personaje.
		boolean key1 = false;
		boolean key2 = false;
		boolean key3 = false;

		boolean salida = false;

		do {

			System.out.println("1. Llorar \t2. Pensar \t3. Salir \t4. Dormir");
			System.out.println("dila opcion del menu");
			int opcion = Utils.pideDatoNumerico("Que quieres hacer?");

			switch (opcion) {

			case 1: {
				HojaParaLimpiar hojadeortiga = new HojaParaLimpiar("Hoja Ortiga", 1, 1, 1);
				notas.getEquipo().add(hojadeortiga);
				key1 = true;
				System.out.println("Has obtenido una Hoja de Ortiga.");
			}
				break;

			case 2: {
				Utils.invocacionCompañeroCriatura(notas);
				key2 = true;
				System.out.println("Has aprendido a invocar criaturas.");
			}
				break;

			case 3: {
				notas.setPuntosVida(1);
				System.out.println(Utils.desgraciaAleatorio() + notas.getPuntosVida());
				key3 = true; // <-- IMPORTANTE PARA PODER SALIR
			}
				break;

			case 4: {
				notas.setPuntosVida(notas.getPuntosVidaMax());
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
			if (key1 == true && key2 == true && key3 == true) {
				salida = true;
			}
		} while (!salida);

	}
}

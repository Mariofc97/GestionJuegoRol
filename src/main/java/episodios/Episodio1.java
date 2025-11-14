package episodios;

import java.util.Scanner;

import equipo.objetos.HojaParaLimpiar;
import personajes.Personaje;
import utilidades.utils;

public class Episodio1 {

//crear un menu:necesito un switch.

	public static void episodio1(Personaje notas) { // notas es el nombre del personaje.
		boolean key1 = false;
		boolean key2 = false;
		boolean key3 = false;

		boolean salida = false;
		if (key1 == true && key2 == true && key3 == true) {
			salida = true;
		}

		do {

			System.out.println("1. Llorar \t2. Pensar \t3. Salir \t4. Dormir");
			// Scanner para leer lo que meta por teclado el jugador.
			Scanner scan = new Scanner(System.in); // tengo que crear una variable"opcion" que sea un numero
			int opcion = scan.nextInt();
// necesitamos un bucle para que el menu se repita hasta que cumpla  una condicion de salida. 
			switch (opcion) {

			case 1: {
				HojaParaLimpiar hojadeortiga = new HojaParaLimpiar("Hoja Ortiga", 1, 1, 1);

				notas.getEquipo().add(hojadeortiga);// añadido una hoja al equipo de notas.
				key1 = true;
				// Llorar crea objeto hoja de ortiga y lo añade al personaje.
			}
				break;
			case 2: {
				// Pensar enseña a invocar criaturas.
				key2=true;
			}
				break;

			case 3: {
				notas.setPuntosVida(1); // Le ponemos la vida a uno.

				System.out.println(utils.desgraciaAleatorio() + notas.getPuntosVida()); // utils.desgraciaAleatorio()
																						// esta llamando directamente a
																						// utils(la clase).
				// Necesitamos que las distintas

				// Salir de la cueva. Sale de la cueva y le pasa algo y le dejamos a un punto de
				// vida.
			}
				break;

			case 4: {
				// Dormir el personaje duerme y restauramos su vida al maximo.
				notas.setPuntosVida(notas.getPuntosVidaMax());
				key3 = true;
			}
				break;

			}
		} while (!salida); // !no (no salida !salida)

	}

}
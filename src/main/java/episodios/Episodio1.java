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
		if (key1 == true && key2 == true && key3 == true) {
			salida = true;
		}

		do {

			System.out.println("1. Llorar \t2. Pensar \t3. Salir \t4. Dormir");
			

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
				Utils.invocacionCompañeroCriatura(notas);
				// Pensar enseña a invocar criaturas.
				key2 = true;
				System.out.println("Has aprendido a invocar criaturas." + personaje.getCriaturas());
				
				personaje.setExperiencia(personaje.getExperiencia()+1);
				personaje.setPuntosVida(personaje.getPuntosVida()+1);
			}
				break;

			case 3: {
				personaje.setPuntosVida(1);
				System.out.println(Utils.desgraciaAleatorio() + personaje.getPuntosVida());
				key3 = true; // <-- IMPORTANTE PARA PODER SALIR
				
=======
				notas.setPuntosVida(1); // Le ponemos la vida a uno.

=======
				System.out.println(utils.desgraciaAleatorio() + notas.getPuntosVida()); // utils.desgraciaAleatorio()
																						// esta llamando
																						// directamente a

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

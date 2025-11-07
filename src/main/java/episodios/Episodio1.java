package episodios;

import java.util.Scanner;

import personajes.Personaje;

public class Episodio1 {

//crear un menu:necesito un switch.

	public static void episodio1(Personaje notas) { //notas es el nombre del personaje.

		boolean salida = false;
		do {		System.out.println("1. Llorar \t2. Pensar \t3. Salir \t4. Dormir");
		// Scanner para leer lo que meta por teclado el jugador.
		Scanner scan = new Scanner(System.in); // tengo que crear una variable"opcion" que sea un numero
		int opcion = scan.nextInt();
// necesitamos un bucle para que el menu se repita hasta que cumpla  una condicion de salida. 
		switch (opcion) {

		case 1: {
			//llorar crea objeto hoja de ortiga y lo añade al personaje.
		}
		case 2: {
			//Pensar enseña a invocar criaturas.
		}
		case 3: { notas.setPuntosVida(1); //Le ponemos la vida a uno.
		
			System.out.println("Sales de la cueva y te cae un rayo." + notas.getPuntosVida());
		//Necesitamos que las distintas
		
		
			//Salir de la cueva. Sale de la cueva y le pasa algo y le dejamos a un punto de vida.
		}
		case 4: {
			//Dormir el personaje duerme y restauramos su vida al maximo.
		}

		}
		} while (!salida); //!no (no salida     !salida)
		


	}

}
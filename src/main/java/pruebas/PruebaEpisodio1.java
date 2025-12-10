package pruebas;

import java.util.ArrayList;
import java.util.List;

import entities.Personaje;
import entities.criatura.Criatura;
import entities.episodios.Episodio1;
import entities.equipo.Equipamiento;

public class PruebaEpisodio1 {

	public static void main(String[] args) {
		// Crear listas vacías para evitar NullPointerException al añadir objetos
		List<Equipamiento> equipo = new ArrayList<>();
		List<Criatura> criaturas = new ArrayList<>();

		// Crear un personaje de prueba usando el constructor disponible
		Personaje p = new Personaje("Humano", 5, 5, 5, equipo, criaturas, "Tester");

		// Inicializar PV máximos y actuales para una prueba coherente
		p.setPuntosVidaMax(100);
		p.setPuntosVida(50);
		p.setPuntosAtaque(10);

		System.out.println("Iniciando prueba de Episodio 1 con el personaje: " + p.getNombre());
		Episodio1.episodio1(p);
		System.out.println("Fin de la prueba de Episodio 1.");
	}
}

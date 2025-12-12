package entities.equipo.objetos;

import entities.equipo.Equipamiento;

public class Comida extends Equipamiento {

	private int puntosVida;

	public Comida(String nombre, int nivelRequerido, int peso, int durabilidad, int puntosVida) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.puntosVida = puntosVida;
	}

	public Comida(String nombre, int puntosVida) {
		super();
		this.setNombre(nombre);
		this.puntosVida = puntosVida;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

}

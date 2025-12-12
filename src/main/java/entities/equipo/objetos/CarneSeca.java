package entities.equipo.objetos;

import entities.equipo.Equipamiento;

public class CarneSeca extends Equipamiento  {
	private int puntosVida;

	public CarneSeca(String nombre, int nivelRequerido, int peso, int durabilidad, int puntosVida) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.puntosVida = puntosVida;
	}

	public CarneSeca(String nombre, int puntosVida) {
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

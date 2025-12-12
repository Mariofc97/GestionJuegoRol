package entities.equipo;

public class Baya extends Equipamiento {

	private int puntosVida;

	public Baya(String nombre, int nivelRequerido, int peso, int durabilidad, int puntosVida) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.puntosVida = puntosVida;
	}

	public Baya(String nombre, int puntosVida) {
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

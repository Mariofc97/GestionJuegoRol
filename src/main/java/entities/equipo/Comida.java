package entities.equipo;

public class Comida extends Equipamiento {

	private int puntosVida;
	private int puntosMagia;
	

	public Comida(String nombre, int nivelRequerido, int peso, int durabilidad, int puntosVida, int puntosMagia) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.puntosVida = puntosVida;
		this.puntosMagia = puntosMagia;
	}

	public int getPuntosVida() {
		return puntosVida;
	}
	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}
	public int getPuntosMagia() {
		return puntosMagia;
	}
	public void setPuntosMagia(int puntosMagia) {
		this.puntosMagia = puntosMagia;
	}
}

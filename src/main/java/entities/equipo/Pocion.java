package entities.equipo;

public class Pocion extends Equipamiento {
	
	private int puntosVida;
	private int puntosMagia;

	public Pocion(String nombre, int nivelRequerido, int peso, int durabilidad) {
		super(nombre, nivelRequerido, peso, durabilidad);
		// TODO Auto-generated constructor stub
	}

	public Pocion(String nombre, int nivelRequerido, int peso, int durabilidad, int puntosVida, int puntosMagia) {
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
	
	//TODO
	//Metodos:
	// public void consumir(Criatura);
	
	

}

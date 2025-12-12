package entities.equipo;

public class Comida {

	private String nombre;
	private int puntosVida;
	private int puntosMagia;
	
	public Comida(int puntosVida, int puntosMagia) {
		this.puntosVida = puntosVida;
		this.puntosMagia = puntosMagia;
	}
	
	public Comida(String nombre, int puntosVida, int puntosMagia) {
		super();
		this.nombre = nombre;
		this.puntosVida = puntosVida;
		this.puntosMagia = puntosMagia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

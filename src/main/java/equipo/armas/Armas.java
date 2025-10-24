package equipo.armas;

import equipo.Equipamiento;

public abstract class Armas extends Equipamiento {
	
	private String tipoDaño; // (fisico, fuego, hielo, etc)
	private int alcance;
	private int precision;
	private int puntosDaño;
	private int probCritico;

	public Armas(String nombre, int nivelRequerido, int peso, int durabilidad) {
		super(nombre, nivelRequerido, peso, durabilidad);
		// TODO Auto-generated constructor stub
	}

	public Armas(String nombre, int nivelRequerido, int peso, int durabilidad, String tipoDaño, int alcance,
			int precision, int puntosDaño, int probCritico) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.tipoDaño = tipoDaño;
		this.alcance = alcance;
		this.precision = precision;
		this.puntosDaño = puntosDaño;
		this.probCritico = probCritico;
	}

	public String getTipoDaño() {
		return tipoDaño;
	}

	public void setTipoDaño(String tipoDaño) {
		this.tipoDaño = tipoDaño;
	}

	public int getAlcance() {
		return alcance;
	}

	public void setAlcance(int alcance) {
		this.alcance = alcance;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getPuntosDaño() {
		return puntosDaño;
	}

	public void setPuntosDaño(int puntosDaño) {
		this.puntosDaño = puntosDaño;
	}

	public int getProbCritico() {
		return probCritico;
	}

	public void setProbCritico(int probCritico) {
		this.probCritico = probCritico;
	}
	
	//TODO
	//IMPLEMENTA INTERFAZ EQUIPABLE

}

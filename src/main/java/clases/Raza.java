package clases;



public abstract class Raza {

	private int fuerza;
	private int inteligencia;
	private int suerte;

	// Constructor

	public Raza(int fuerza, int inteligencia, int suerte) {
		this.fuerza = fuerza;
		this.inteligencia = inteligencia;
		this.suerte = suerte;
	}

	// Getters Setters

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getSuerte() {
		return suerte;
	}

	public void setSuerte(int suerte) {
		this.suerte = suerte;
	}

}

package clases;

abstract class Raza {

	private String tipo;
	private int fuerza;
	private int velocidad;
	private int magia;
	private int destreza;

	public Raza(String tipo, int fuerza, int velocidad, int magia, int destreza) {
		super();
		this.tipo = tipo;
		this.fuerza = fuerza;
		this.velocidad = velocidad;
		this.magia = magia;
		this.destreza = destreza;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getMagia() {
		return magia;
	}

	public void setMagia(int magia) {
		this.magia = magia;
	}

	public int getDestreza() {
		return destreza;
	}

	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	//TODO
	//Metodos:
	// public int modAtaque();
	// public int modDefensa();
	// public int modCritico();

}

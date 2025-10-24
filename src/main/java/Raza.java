
public abstract class Raza {

	private String tipo;
	private int fuerza;
	private int destreza;
	private int suerte;
	
	//Constructor
	
	public Raza(String tipo, int fuerza, int destreza, int suerte) {
		this.tipo=tipo;
		this.fuerza=fuerza;
		this.destreza=destreza;
		this.suerte=suerte;
}
	
	//Getters Setters
	
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

	public int getDestreza() {
		return destreza;
	}

	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	public int getSuerte() {
		return suerte;
	}

	public void setSuerte(int suerte) {
		this.suerte = suerte;
	}



	
	
	
	
	
	
}

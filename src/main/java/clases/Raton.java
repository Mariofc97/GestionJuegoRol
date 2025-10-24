package clases;

import criaturas.Criatura;

public class Raton extends Criatura {
	
	private String morder;
	private int PuntosDeVida = 12;
	private int PuntosDeAtaque = 6;

	public Raton(String nombre, int nivel, int experiencia) {
		super(nombre, nivel, experiencia);
		
	}

	public Raton(String nombre, int nivel, int experiencia, String morder) {
		super(nombre, nivel, experiencia);
		this.morder = morder;
		
	}

	public String getMorder() {
		return morder;
	}

	public void setMorder(String morder) {
		this.morder = morder;
	}

	@Override
	public String toString() {
		return "Raton [morder=" + morder + "]";
	}
	
	


	
	
	
}

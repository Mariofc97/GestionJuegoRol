package clases;

import criaturas.Criatura;

public class Gusano extends Criatura {

	private String subcionar;
	private int PuntosDeVida = 5;
	private int PuntosDeAtaque = 1;
	
public Gusano(String subcionar, String nombre, int nivel, int experiencia, int puntosVida, int puntosAtaque) {
		super(nombre, nivel, experiencia, puntosVida, puntosAtaque);
	this.subcionar = subcionar;
		
		
		
	}

public String getSubcionar() {
	return subcionar;
}

public void setSubcionar(String subcionar) {
	this.subcionar = subcionar;
}

public int getPuntosDeVida() {
	return PuntosDeVida;
}

public void setPuntosDeVida(int puntosDeVida) {
	PuntosDeVida = puntosDeVida;
}

public int getPuntosDeAtaque() {
	return PuntosDeAtaque;
}

public void setPuntosDeAtaque(int puntosDeAtaque) {
	PuntosDeAtaque = puntosDeAtaque;
}

@Override
public String toString() {
	return "Gusano [Subcionar=" + subcionar + ", PuntosDeVida=" + PuntosDeVida + ", PuntosDeAtaque=" + PuntosDeAtaque
			+ "]";
}

}

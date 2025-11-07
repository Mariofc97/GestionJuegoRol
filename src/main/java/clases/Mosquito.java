package clases;

import criaturas.Criatura;

public class Mosquito extends Criatura{
	
	private String picar;
	private int PuntosDeVida = 4;
	private int PuntosDeAtaque = 8;
	
	
public Mosquito(String nombre, int nivel, int experiencia, String picar) {
		super(nombre, nivel, experiencia);
		this.picar = picar;
		
	}





public String getPicar() {
	return picar;
}





public void setPicar(String picar) {
	this.picar = picar;
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






	

}

package clases;

import criaturas.Criatura;

public class Mosquito extends Criatura{
	
	private String picar;
	private int PuntoDeVida = 4;
	private int PuntoDeAtaque = 8;
	
	
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


public int getPuntoDeVida() {
	return PuntoDeVida;
}


public void setPuntoDeVida(int puntoDeVida) {
	PuntoDeVida = puntoDeVida;
}


public int getPuntoDeAtaque() {
	return PuntoDeAtaque;
}


public void setPuntoDeAtaque(int puntoDeAtaque) {
	PuntoDeAtaque = puntoDeAtaque;
}


@Override
public String toString() {
	return "Mosquito [picar=" + picar + ", PuntoDeVida=" + PuntoDeVida + ", PuntoDeAtaque=" + PuntoDeAtaque + "]";
}
	

}

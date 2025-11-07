package clases.razas;

import clases.Raza;
import personajes.Personaje;

public class Trogodita extends Raza {
	
	
	public Trogodita() {
		super("Trogodita", 8, 2, 1); //tipo, fuerza, inteligencia, suergencia, int suertete
		// TODO Auto-generated constructor stub
	}

	@Override
	public void aplicarBonos(Personaje p) {
		// TODO Auto-generated method stub
		if(p.getPuntosVida() >= 80) {
			p.setPuntosAtaque(p.getPuntosAtaque() + p.getFuerza() + 2);
		};
	}

	@Override
	public String descripcionRasgos() {
		// TODO Auto-generated method stub
		return "Eres tan feo y gordo como fuerte. +2 (Ataque ;
	}
	
	
	
	

}

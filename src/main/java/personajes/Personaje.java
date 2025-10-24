package personajes;

import java.util.List;

import clases.Raza;
import equipo.Equipamiento;

public class Personaje extends Raza{

	private List<Equipamiento> equipo;
	
	public Personaje(String tipo, int fuerza, int inteligencia, int suerte) {
		super(tipo, fuerza, inteligencia, suerte);
		// TODO Auto-generated constructor stub
	}
	
	

	
	
	

	//PERSONAJE
	// public void ganarExperiencia(int experiencia);
	// public void curar()
	// public void usarPocion();
	// public void recibirDaño(int);
	// public int atacar (Atacable a); IMPLEMENTA ATACABLE
	// public int defender(Defendible d); IMPLEMENTA DEFENDIBLE
}

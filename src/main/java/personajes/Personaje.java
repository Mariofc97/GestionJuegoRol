package personajes;

import java.util.List;

import clases.Raza;
import criaturas.Criatura;
import equipo.Equipamiento;

public class Personaje extends Raza {

	private List<Equipamiento> equipo;
	private List<Criatura> criaturas;
	private String nombre;

	public Personaje(String tipo, int fuerza, int inteligencia, int suerte) {
		super(tipo, fuerza, inteligencia, suerte);
		// TODO Auto-generated constructor stub
	}

	public Personaje(String tipo, int fuerza, int inteligencia, int suerte, List<Equipamiento> equipo,
			List<Criatura> criaturas, String nombre) {
		super(tipo, fuerza, inteligencia, suerte);
		this.equipo = equipo;
		this.criaturas = criaturas;
		this.nombre = nombre;
	}

	public List<Equipamiento> getEquipo() {
		return equipo;
	}

	public void setEquipo(List<Equipamiento> equipo) {
		this.equipo = equipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Criatura> getCriaturas() {
		return criaturas;
	}

	public void setCriaturas(List<Criatura> criaturas) {
		this.criaturas = criaturas;
	}

	// PERSONAJE
	// public void ganarExperiencia(int experiencia);
	// public void curar()
	// public void usarPocion();
	// public void recibirDaño(int);
	// public int atacar (Atacable a); IMPLEMENTA ATACABLE
	// public int defender(Defendible d); IMPLEMENTA DEFENDIBLE
}

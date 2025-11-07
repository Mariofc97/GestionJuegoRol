package personajes;

import java.util.List;

import clases.Raza;
import criaturas.Criatura;
import equipo.Equipamiento;
import equipo.objetos.Pocion;

public class Personaje extends Raza {

	private Raza raza;
	private List<Equipamiento> equipo;
	private List<Criatura> criaturas;
	private String nombre;
	private int experiencia; // sube putnos de vida y ataque
	private int puntasVidaMax;
	private int puntosVida;
	private int puntosAtaque; // modificamos si raza
	private int inteligencia; // nos vale par pensar y crear

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

	public Personaje(String tipo, int fuerza, int inteligencia, int suerte, List<Equipamiento> equipo,
			List<Criatura> criaturas, String nombre, int experiencia, int puntosVida, int puntosAtaque) {
		super(tipo, fuerza, inteligencia, suerte);
		this.equipo = equipo;
		this.criaturas = criaturas;
		this.nombre = nombre;
		this.experiencia = experiencia;
		this.puntosVida = puntosVida;
		this.puntosAtaque = puntosAtaque;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

	public int getPuntosAtaque() {
		return puntosAtaque;
	}

	public void setPuntosAtaque(int puntosAtaque) {
		this.puntosAtaque = puntosAtaque;
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

	public int ganarExperiencia(int experienciaAñadida) {
		this.experiencia = experiencia + experienciaAñadida;

		System.out.println("El personaje " + nombre + " ha ganado " + experienciaAñadida + " puntos de experiencia");
		System.out.println("Ahora mismo tiene " + experiencia + " de experiencia");

		return this.experiencia;
	}

	public int usarPocion(Pocion pocion) {

		int puntosVidaPocion = pocion.getPuntosVida();

		this.puntosVida = puntosVida + puntosVidaPocion;
		System.out.println("El personaje " + nombre + " se ha curado " + puntosVidaPocion + " puntos de vida gracias");

		return this.puntosVida;
	}

	// PERSONAJE
	// public void ganarExperiencia(int experiencia);
	// public void curar()
	// public void usarPocion();
	// public void recibirDaño(int);
	// public int atacar (Atacable a); IMPLEMENTA ATACABLE
	// public int defender(Defendible d); IMPLEMENTA DEFENDIBLE
}

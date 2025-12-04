package entities.criatura;

import core.Atacable;
import core.Defendible;

public abstract class Criatura implements Atacable, Defendible {
	
	//TODO: MANUEL
	// Debemos de crear criaturas:
	// Son clases PUBLICAS que van extendidas de criatura, heredando sus atributos
		//Conejo, raton, gusano (una criatura que le va a ayudar)

	private String Nombre;
	private int nivel;
	private int experiencia;
	private int puntosVida; 
	private int puntosAtaque;
	
	public Criatura(String nombre, int nivel, int experiencia) {
		super();
		Nombre = nombre;
		this.nivel = nivel;
		this.experiencia = experiencia;
	}
	
	public Criatura() {
	}

	public Criatura(String nombre, int nivel, int experiencia, int puntosVida, int puntosAtaque) {
		super();
		Nombre = nombre;
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.puntosVida = puntosVida;
		this.puntosAtaque = puntosAtaque;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
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

	@Override
	public void recibirDanio(int danio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estaVivo() {
		// TODO Auto-generated method stub
		return this.puntosVida > 0;
	}

	@Override
	public int atacar(Defendible objetivo) {
		// TODO Auto-generated method stub
		 int danio = this.puntosAtaque;
	     objetivo.recibirDanio(danio);
	     return danio;
	}
	
	
	

	// TODO
	// Metodos:
	// public int atacar (Atacable a); IMPLEMENTA ATACABLE
	// public int defender(Defendible d); IMPLEMENTA DEFENDIBLE
	// public void recibirDaño(int);
	
}

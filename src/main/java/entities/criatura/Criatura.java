package entities.criatura;

import core.Atacable;
import core.Defendible;

public abstract class Criatura implements Atacable, Defendible {

	// TODO: MANUEL
	// Debemos de crear criaturas:
	// Son clases PUBLICAS que van extendidas de criatura, heredando sus atributos
	// Conejo, raton, gusano (una criatura que le va a ayudar)

	private String nombre;
	private String alias;
	private int nivel;
	private int experiencia;
	private int puntosVida;
	private int puntosAtaque;
	private String tipoAtaque;

	public Criatura(String alias, int nivel, int experiencia, int puntosVida, int puntosAtaque) {
		super();
		this.alias = alias;
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.puntosVida = puntosVida;
		this.puntosAtaque = puntosAtaque;
	}

	public Criatura() {
		super();
	}

	public Criatura(String nombre, String alias, int nivel, int experiencia, int puntosVida, int puntosAtaque,
			String tipoAtaque) {
		super();
		this.nombre = nombre;
		this.alias = alias;
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.puntosVida = puntosVida;
		this.puntosAtaque = puntosAtaque;
		this.tipoAtaque = tipoAtaque;
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoAtaque() {
		return tipoAtaque;
	}

	public void setTipoAtaque(String tipoAtaque) {
		this.tipoAtaque = tipoAtaque;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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

	@Override
	public String toString() {
		return "Criatura [nombre=" + nombre + ", alias=" + alias + ", nivel=" + nivel + ", experiencia=" + experiencia
				+ ", puntosVida=" + puntosVida + ", puntosAtaque=" + puntosAtaque + ", tipoAtaque=" + tipoAtaque + "]";
	}
	
	

	// TODO
	// Metodos:
	// public int atacar (Atacable a); IMPLEMENTA ATACABLE
	// public int defender(Defendible d); IMPLEMENTA DEFENDIBLE
	// public void recibirDaño(int);

}

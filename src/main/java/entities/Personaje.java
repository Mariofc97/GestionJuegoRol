package entities;

import java.util.List;

import core.Atacable;
import core.Defendible;
import entities.criatura.Criatura;
import entities.equipo.Armas;
import entities.equipo.Equipamiento;
import entities.equipo.Pocion;
import entities.raza.Raza;

public class Personaje implements Atacable, Defendible{

	private Raza raza;
	private List<Equipamiento> equipo;
	private List<Criatura> criaturas;
	private String nombre;
	private int experiencia; // sube putnos de vida y ataque
	private int nivel; // nivel de experiencia
	private int puntosVidaMax; // maximo 100 puntos de vida
	private int puntosVida;
	private int puntosAtaque; // modificamos si raza
	private int inteligencia; // nos vale par pensar y crear
	private int suerte;

    public Personaje(Raza raza, String nombre) {
        if (raza == null) {
        	throw new IllegalArgumentException("La raza no puede ser null");
        }
        this.raza = raza;
        this.nombre = nombre;

        // bases desde la raza
        this.puntosVida = puntosVida;
        this.puntosAtaque = puntosAtaque;

        // aplica pasivas/bonos iniciales DESPUÉS de setear PV/ATQ
        raza.aplicarBonos(this);
    }
    
    

	public Personaje(String tipo, int fuerza, int inteligencia, int suerte, List<Equipamiento> equipo,
			List<Criatura> criaturas, String nombre) {
		super();
		this.equipo = equipo;
		this.criaturas = criaturas;
		this.nombre = nombre;
	}

	public Personaje(String tipo, int fuerza, int inteligencia, int suerte, List<Equipamiento> equipo,
			List<Criatura> criaturas, String nombre, int experiencia, int puntosVida, int puntosAtaque, int nivel) {
		super();
		this.equipo = equipo;
		this.criaturas = criaturas;
		this.nombre = nombre;
		this.experiencia = 0; // la experiencia se inicializa desde 0
		this.puntosVida = puntosVida;
		this.puntosAtaque = puntosAtaque;
		this.nivel = 1; // siempre se creara el personaje con nivel 1
	}
	
	public int getSuerte() {
		return suerte;
	}

	public void setSuerte(int suerte) {
		this.suerte = suerte;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public int getPuntosVidaMax() {
		return puntosVidaMax;
	}

	public void setPuntosVidaMax(int puntosVidaMax) {
		this.puntosVidaMax = puntosVidaMax;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
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

	public int usarPocion(Pocion pocion) {

		int puntosVidaPocion = pocion.getPuntosVida();

		this.puntosVida = puntosVida + puntosVidaPocion;
		System.out.println("El personaje " + nombre + " se ha curado " + puntosVidaPocion + " puntos de vida gracias");

		return this.puntosVida;
	}
	
	

	public int getNivel() {
		return nivel;
	}



	public void setNivel(int nivel) {
		this.nivel = nivel;
	}



	@Override
	public void recibirDanio(int danio) {
		// TODO Auto-generated method stub
		this.puntosVida -= danio;
		if(this.puntosVida < 0) {
			System.out.println(this.nombre + " esta muerto, tiene 0 puntos de vida");
			this.puntosVida = 0;
		}
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
	
	public Armas getArmaEquipada() {
		if (equipo == null) {
			return null;
		}
		
		for (Equipamiento e: equipo) {
			if (e instanceof Armas) {
				// casteamos e como objeto tipo Armas
				return (Armas) e;
			}
		}
		
		return null;
	}
	
	public boolean tieneArmaEquipada() {
		Armas arma = getArmaEquipada();
		if (arma!= null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void ganarExperiencia(int experienciaAñadida) {
		if (experienciaAñadida <=0) {
			System.out.println("El personaje no puede perder experiencia");
			return;
		}
		
		System.out.println("El personaje " + nombre + " gana " + experienciaAñadida + " de experiencia.");
		this.experiencia += experienciaAñadida;
		System.out.println("Ahora mismo tiene " + experiencia + " puntos de experiencia");
		subirNivelSiToca();
	}
	
	// sencillo, se sube de nivel cada 100 puntos de experiencia.
	
	private int experienciaParaSiguienteNivel() {
		return nivel * 100;
	}
	
	public void subirNivelSiToca() {
		boolean haSubido = false;
		
		while(this.experiencia >= experienciaParaSiguienteNivel()) {
			this.experiencia -= experienciaParaSiguienteNivel();
			this.nivel++;
			haSubido = true;
			
            this.puntosVidaMax += 10;
            this.puntosAtaque += 2;
			//MARIO: PENDIENTE DISTINGUIR ATRIBUTOS A MEJORA SI SUBE DE NIVEL EN FUNCION DE SI ES MONGOL, RAPANUI O TROGODITA
			// QUIZA CREANDO INSTANCIA Y REALIZANDO CASTEO
			
		}
		
        if (haSubido) {
            System.out.println("¡" + nombre + " ha subido al nivel " + nivel + "!");
            System.out.println("Vida máxima: " + puntosVidaMax + " | Ataque: " + puntosAtaque);
        }
	}
	
	
}
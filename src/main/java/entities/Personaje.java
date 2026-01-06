package entities;

import java.util.List;

import core.Atacable;
import core.Defendible;
import entities.criatura.Criatura;
import entities.equipo.Equipamiento;
import entities.equipo.armas.Armas;
import entities.equipo.objetos.Pocion;
import entities.raza.Raza;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "TB_PERSONAJE")
public class Personaje implements Atacable, Defendible {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personaje_seq")
    @SequenceGenerator(name = "personaje_seq", sequenceName = "SEQ_PERSONAJE", allocationSize = 1)
	private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "experiencia", nullable = false)
	private int experiencia; // sube putnos de vida y ataque
    
    @Column(name = "nivel", nullable = false)
	private int nivel; // nivel de experiencia
    
    @Column(name = "puntos_vida_max", nullable = false)
	private int puntosVidaMax; // maximo 100 puntos de vida
    
    @Column(name = "puntos_vida", nullable = false)
	private int puntosVida;
    
    @Column(name = "puntos_ataque", nullable = false)
	private int puntosAtaque; // modificamos si raza
    
    @Column(name = "inteligencia", nullable = false)
	private int inteligencia; // nos vale par pensar y crear
    
    @Column(name = "suerte", nullable = false)
	private int suerte;
    
    @Column(name="episodio_actual", nullable = false)
    private int episodioActual = 1;
	
    // Guardamos la raza como String por simplicidad
    @Column(name = "raza_tipo", nullable = false, length = 30)
    private String razaTipo;
    
	// utilizamos Transient para no persistir esto todavia, ya que esto complica mas el proyecto
	
	@Transient
	private Raza raza;
	
	@OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Equipamiento> equipo = new java.util.ArrayList<>();

	@Transient
	private List<Criatura> criaturas = new java.util.ArrayList<>();

	
	public Personaje() {
		super();
	}

	public Personaje(String nombre, String razaTipo) {
		super();
		this.nombre = nombre;
		this.razaTipo = razaTipo;
	}

	public Personaje(Raza raza, String nombre) {
		if (raza == null) {
			throw new IllegalArgumentException("La raza no puede ser null");
		}
		this.raza = raza;
		this.nombre = nombre;

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
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaTipo() {
		return razaTipo;
	}

	public void setRazaTipo(String razaTipo) {
		this.razaTipo = razaTipo;
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
		if (this.puntosVida < 0) {
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

		for (Equipamiento e : equipo) {
			if (e instanceof Armas) {
				// casteamos e como objeto tipo Armas
				return (Armas) e;
			}
		}

		return null;
	}

	public boolean tieneArmaEquipada() {
		Armas arma = getArmaEquipada();
		if (arma != null) {
			return true;
		} else {
			return false;
		}
	}

	public void ganarExperiencia() {
		ganarExperiencia(10); // por defecto gana 10 puntos de experiencia
	}

	public void ganarExperiencia(int experienciaAñadida) {
		if (experienciaAñadida <= 0) {
			System.out.println("El personaje no puede perder experiencia");
			return;
		}

		System.out.println("El personaje " + nombre + " gana " + experienciaAñadida + " de experiencia.");
		this.experiencia += experienciaAñadida;
		System.out.println("Experiencia acumulada: " + experiencia);
		subirNivelSiToca();
	}

	// sencillo, se sube de nivel cada 100 puntos de experiencia.

	private int experienciaParaSiguienteNivel() {
		return nivel * 100;
	}

	public void subirNivelSiToca() {

		boolean haSubido = false;

		while (this.experiencia >= experienciaParaSiguienteNivel()) {
			this.experiencia -= experienciaParaSiguienteNivel();
			this.nivel++;
			haSubido = true;

			this.puntosVidaMax += 10;
			this.puntosAtaque += 2;
			// MARIO: PENDIENTE DISTINGUIR ATRIBUTOS A MEJORA SI SUBE DE NIVEL EN FUNCION DE
			// SI ES MONGOL, RAPANUI O TROGODITA
			// QUIZA CREANDO INSTANCIA Y REALIZANDO CASTEO

		}

		if (haSubido) {
			System.out.println("¡" + nombre + " ha subido al nivel " + nivel + "!");
			System.out.println("Vida máxima: " + puntosVidaMax + " | Ataque: " + puntosAtaque);
		}
	}

	@Override
	public String toString() {
		return "Personaje [id=" + id + ", nombre=" + nombre + ", experiencia=" + experiencia + ", nivel=" + nivel
				+ ", puntosVidaMax=" + puntosVidaMax + ", puntosVida=" + puntosVida + ", puntosAtaque=" + puntosAtaque
				+ ", inteligencia=" + inteligencia + ", suerte=" + suerte + ", razaTipo=" + razaTipo + "]";
	}
	
	

}
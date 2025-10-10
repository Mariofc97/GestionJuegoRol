package clases;

abstract class Criatura {

	private String Nombre;
	private Raza raza;
	private int nivel;
	private int experiencia;
	private List<Escudos> escudos;
	private List<Armas> armas;
	private int puntosVida;
	private int puntosMagia;
	private String objetos;

	public Criatura(String nombre, Raza raza, int nivel, int experiencia, List<Escudos> escudos, List<Armas> armas,
			int puntosVida, int puntosMagia, String objetos) {
		super();
		Nombre = nombre;
		this.raza = raza;
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.escudos = escudos;
		this.armas = armas;
		this.puntosVida = puntosVida;
		this.puntosMagia = puntosMagia;
		this.objetos = objetos;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
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

	public List<Escudos> getEscudos() {
		return escudos;
	}

	public void setEscudos(List<Escudos> escudos) {
		this.escudos = escudos;
	}

	public List<Armas> getArmas() {
		return armas;
	}

	public void setArmas(List<Armas> armas) {
		this.armas = armas;
	}

	public int getPuntosVida() {
		return puntosVida;
	}

	public void setPuntosVida(int puntosVida) {
		this.puntosVida = puntosVida;
	}

	public int getPuntosMagia() {
		return puntosMagia;
	}

	public void setPuntosMagia(int puntosMagia) {
		this.puntosMagia = puntosMagia;
	}

	public String getObjetos() {
		return objetos;
	}

	public void setObjetos(String objetos) {
		this.objetos = objetos;
	}

	// TODO
	// Metodos:
	// public int atacar (Atacable a); IMPLEMENTA ATACABLE
	// public int defender(Defendible d); IMPLEMENTA DEFENDIBLE
	// public void recibirDaño(int);
	// public void curar ()
	// public void recuperarMagia();
	// public void usarPocion();
	// public void equipar();
	// public void equipar();
	// public void ganarExperiencia(int experiencia);

}

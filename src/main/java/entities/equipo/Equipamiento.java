package entities.equipo;

public abstract class Equipamiento {
	
	//TODO
	
	//Tenemos que crear 

	private String nombre;
	private int nivelRequerido;
	private int peso;
	private int durabilidad;

	public Equipamiento(String nombre, int nivelRequerido, int peso, int durabilidad) {
		super();
		this.nombre = nombre;
		this.nivelRequerido = nivelRequerido;
		this.peso = peso;
		this.durabilidad = durabilidad;
	}
	
	

	public Equipamiento() {
		super();
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivelRequerido() {
		return nivelRequerido;
	}

	public void setNivelRequerido(int nivelRequerido) {
		this.nivelRequerido = nivelRequerido;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getDurabilidad() {
		return durabilidad;
	}

	public void setDurabilidad(int durabilidad) {
		this.durabilidad = durabilidad;
	}
	
	//TODO
	//Metodos
	// Esta en principio no lleva metodos

}

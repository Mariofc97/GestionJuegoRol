package entities.equipo.objetos;

import entities.equipo.Equipamiento;

public class Cuerda extends Equipamiento {
	private String nombre = "Cuerda";

	public Cuerda(String nombre, int nivelRequerido, int peso, int durabilidad, String nombre2) {
		super(nombre, nivelRequerido, peso, durabilidad);
		nombre = nombre2;
	}

	public Cuerda(String nombre) {
		super();
	}
	public Cuerda() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.setNombre(nombre);
	}

	@Override
	public String toString() {
		return "Cuerda [nombre=" + nombre + "]";
	}
	
	

}

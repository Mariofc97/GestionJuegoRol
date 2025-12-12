
package entities.equipo.objetos;

import entities.equipo.Equipamiento;

public class Palo extends Equipamiento {

	String nombre = "Palo";

	public Palo(String nombre) {
		super();
		this.setNombre(nombre);
	}

	public Palo() {
		super();
	}

	public Palo(String nombre, int nivelRequerido, int peso, int durabilidad) {
		super(nombre, nivelRequerido, peso, durabilidad);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Palo [nombre=" + nombre + "]";
	}

}

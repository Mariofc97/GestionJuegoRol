package entities.equipo.objetos;

import entities.equipo.Equipamiento;

public class MojonSeco extends Equipamiento {
	private String nombre = "Mojon Seco";

	public MojonSeco(String nombre, int nivelRequerido, int peso, int durabilidad) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.nombre = nombre;
	}

	public MojonSeco() {
		super();
	}

	public MojonSeco(String nombre) {
		super();
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "MojonSeco [nombre=" + nombre + "]";
	}

}

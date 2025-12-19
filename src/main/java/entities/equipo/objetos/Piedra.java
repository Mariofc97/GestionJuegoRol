package entities.equipo.objetos;

import entities.equipo.Equipamiento;

public class Piedra extends Equipamiento{

	String piedra = "Piedra";
	
	public Piedra(String nombre) {
		super();
		this.setNombre(nombre);
		}
	public Piedra() {
		super();
		
	}
	
	public Piedra(String nombre, int nivelRequerido, int peso, int durabilidad, String piedra) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.piedra = piedra;
	}
	public String getPiedra() {
		return piedra;
	}

	public void setPiedra(String piedra) {
		this.piedra = piedra;
	}
@Override
public String toString() {
	return "Piedra [nombre= " + nombre + "]";
	
	
	
	
	
}
	
	}
	
	
	
	
	
	



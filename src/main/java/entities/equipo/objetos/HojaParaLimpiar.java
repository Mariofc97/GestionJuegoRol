package entities.equipo.objetos;

import entities.equipo.Equipamiento;

public class HojaParaLimpiar extends Equipamiento {
	
	private String origen;

	public HojaParaLimpiar(String nombre, int nivelRequerido, int peso, int durabilidad) {
		super(nombre, nivelRequerido, peso, durabilidad);
		// TODO Auto-generated constructor stub
	}

	public HojaParaLimpiar(String nombre, int nivelRequerido, int peso, int durabilidad, String origen) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.origen = origen;
	}
	

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
	

	
}

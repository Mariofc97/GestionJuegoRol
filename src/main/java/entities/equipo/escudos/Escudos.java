package entities.equipo.escudos;

import entities.equipo.Equipamiento;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Escudos extends Equipamiento {
	
	@Column(name = "puntos_resistencia")
	private int puntosResistencia;

	public Escudos(String nombre, int nivelRequerido, int peso, int durabilidad) {
		super(nombre, nivelRequerido, peso, durabilidad);
		// TODO Auto-generated constructor stub
	}

	public Escudos(String nombre, int nivelRequerido, int peso, int durabilidad, int puntosResistencia) {
		super(nombre, nivelRequerido, peso, durabilidad);
		this.puntosResistencia = puntosResistencia;
	}

	public int getPuntosResistencia() {
		return puntosResistencia;
	}

	public void setPuntosResistencia(int puntosResistencia) {
		this.puntosResistencia = puntosResistencia;
	}
	
	
	
	

}

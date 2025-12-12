package entities.equipo.armas;

public class Arco extends Armas{
	
	String nombre = "Arco";

	public Arco(String nombre, int nivelRequerido, int peso, int durabilidad) {
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
		return "Arco [nombre=" + nombre + "]";
	}
	
	


}

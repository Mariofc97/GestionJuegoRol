package entities.criatura;

public class Conejo extends Criatura{


	public Conejo() {
		// TODO Auto-generated constructor stub
	}
	


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntosDeVida() {
		return PuntosDeVida;
	}

	public void setPuntosDeVida(int puntosDeVida) {
		PuntosDeVida = puntosDeVida;
	}

	public int getPuntosDeAtaque() {
		return PuntosDeAtaque;
	}

	public void setPuntosDeAtaque(int puntosDeAtaque) {
		PuntosDeAtaque = puntosDeAtaque;
	}

	public String getOrejazo() {
		return orejazo;
	}

	public void setOrejazo(String orejazo) {
		this.orejazo = orejazo;
	}

	@Override
	public String toString() {
		return "Conejo [PuntosDeVida=" + PuntosDeVida + ", PuntosDeAtaque=" + PuntosDeAtaque + ", orejazo=" + orejazo
				+ ", toString()=" + super.toString() + "]";
	}

}

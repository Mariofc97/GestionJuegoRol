package entities.criatura;

public class Conejo extends Criatura{

	private int PuntosDeVida = 10;
	private int PuntosDeAtaque = 4;
	private String orejazo; // esto como funciona exactamente??
	private String nombre = "Conejo";

	public Conejo() {
		// TODO Auto-generated constructor stub
	}
	
	public Conejo(String alias, int nivel, int experiencia,
			int puntosDeAtaque, String orejazo, String nombre) {
		super(alias, nivel, experiencia);
		PuntosDeAtaque = puntosDeAtaque;
		this.orejazo = orejazo;
		this.nombre = nombre;
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

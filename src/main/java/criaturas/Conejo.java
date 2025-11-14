package criaturas;

public class Conejo extends Criatura{

	private int PuntosDeVida = 10;
	private int PuntosDeAtaque = 4;
	private String orejazo;
	
	public Conejo(String nombre, int nivel, int experiencia) {
		super(nombre, nivel, experiencia);
	//Tenemos dos constructores.
	}

	public Conejo(String nombre, int nivel, int experiencia, String orejazo) {
		super(nombre, nivel, experiencia);
		this.orejazo = orejazo;
	}

	public Conejo() {
		// TODO Auto-generated constructor stub
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
	


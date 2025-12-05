package entities.criatura;

public class Mosquito extends Criatura {

	public Mosquito(String nombre, String alias, int nivel, int experiencia, int puntosVida, int puntosAtaque,
			String tipoAtaque) {
		super("Mosquito", alias, 1, 0, 20, 1, "PicaduraFatal");
	}

	public Mosquito() {
		super();
	}

}

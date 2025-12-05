package entities.criatura;

public class Raton extends Criatura {

	public Raton(String nombre, String alias, int nivel, int experiencia, int puntosVida, int puntosAtaque,
			String tipoAtaque) {
		super("Raton", alias, 1, 0, 20, 1, "MordiscoInfeccioso");

	}

	public Raton() {
		super();
	}

}

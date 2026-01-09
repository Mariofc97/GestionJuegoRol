package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LANZA")
public class Lanza extends Armas {
	public Lanza() {

		super("Lanzamiento de lanza", 8, 1, 10, 6);

	}
}
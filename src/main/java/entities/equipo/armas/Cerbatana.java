package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CERBATANA")
public class Cerbatana extends Armas {
	public Cerbatana() {

		super("Dardo venenoso", 8, 1, 10, 6);

	}
}
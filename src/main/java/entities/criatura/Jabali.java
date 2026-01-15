package entities.criatura;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("JABALI")


public class Jabali extends Criatura {

	public Jabali() {
		super("Jabalí", null, 11, 35,40, 25, "Cornada");
	}

}

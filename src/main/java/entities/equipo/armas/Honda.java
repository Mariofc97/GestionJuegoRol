package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HONDA")		


public class Honda extends Armas {

	public Honda() {
		super("Honda", 6, 2,16, 10);
	}
	
}

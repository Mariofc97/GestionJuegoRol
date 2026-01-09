package entities.equipo.armas;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUMERAN")

public class Bumeran extends Armas {

	public Bumeran() {
		super("Bumeran", 7, 7, 13, 6);
	}

	
}

package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ARCO")

public class Arco extends Armas{
	
	String nombre = "Arco";

	public Arco() {
		super("Arco", 9, 1, 17,15); // ajusta valores
	}
	


}

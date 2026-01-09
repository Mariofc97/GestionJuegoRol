package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity	
@DiscriminatorValue("CAZAMARIPOSAS")


public class Cazamariposas extends Armas {

	public Cazamariposas() {
		super("Malla limita visión",3,2,1,6);
		
		
		
		
		
		
	}
	
	
	
}

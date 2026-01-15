package entities.criatura;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PEZ_PREHISTORICO_GIGANTE")

public class PezPrehistoricoGigante  extends Criatura {

	
	
	
	
	public PezPrehistoricoGigante() {
		super("Pez Prehistorico Gigante", null, 10, , 30, 50, "Mordisco Devastador");
	}

}

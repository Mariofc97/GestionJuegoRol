package entities.equipo.objetos;

import entities.equipo.Equipamiento;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMIDA")
public class Comida extends Equipamiento {

	public Comida() {
		super("Comida", 1, 3, 2); // ajusta valores
	}

}

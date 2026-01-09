package entities.equipo.objetos;

import entities.equipo.Equipamiento;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity	
@DiscriminatorValue("MOJON_SECO")

public class MojonSeco extends Equipamiento {
public MojonSeco() {
		super("Mojón Seco", 1, 3, 5); // ajusta valores
	}
}

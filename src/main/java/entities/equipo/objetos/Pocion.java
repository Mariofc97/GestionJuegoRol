package entities.equipo.objetos;

import entities.equipo.Equipamiento;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;




@Entity
@DiscriminatorValue("POCION")

public class Pocion extends Equipamiento {
	
	public Pocion() {
		super("Pocion", 1, 1, 1); // ajusta valores
	}
	
	

}

package entities.equipo.objetos;

import entities.equipo.Equipamiento;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CUERDA")
public class Cuerda extends Equipamiento {

    public Cuerda() {
        super("Cuerda", 1, 4, 95); // ajusta valores
    }
}

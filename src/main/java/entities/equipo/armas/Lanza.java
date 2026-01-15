package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LANZA")
public class Lanza extends Armas {

    public Lanza() {
        super("PUNZANTE", 2, 65, 7, 15);
        setNombre("Lanza");
    }
}
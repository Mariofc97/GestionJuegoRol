package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ARCO")
public class Arco extends Armas {

    public Arco() {
        super("FLECHAS", 4, 70, 6, 10); // alcance, precision, daño, crit
        setNombre("Arco");
    }
}

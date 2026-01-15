package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CAZAMARIPOSAS")
public class Cazamariposas extends Armas {

    public Cazamariposas() {
        super("GOLPE", 2, 80, 4, 8);
        setNombre("Cazamariposas");
    }
}

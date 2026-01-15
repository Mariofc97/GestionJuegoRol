package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HONDA")
public class Honda extends Armas {

    public Honda() {
        super("PIEDRA", 3, 60, 5, 10);
        setNombre("Honda");
    }
}

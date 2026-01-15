package entities.equipo.armas;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUMERAN")
public class Bumeran extends Armas {

    public Bumeran() {
        super("GOLPE", 3, 75, 5, 12);
        setNombre("Bumerán");
    }
}
package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue(value = "CC")
public class CompteCourant extends Compte{

    private double decouvert;
}

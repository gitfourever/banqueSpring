package ang.neggaw.entities;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue(value = "CC")
public class CompteCourant extends Compte{

    private double decouvert;

    public CompteCourant(String numCte, double solde, Date dateCreation, Employe employe, Client client, Collection<Operation> operations, double decouvert) {
        super(numCte, solde, dateCreation, employe, client, operations);
        this.decouvert = decouvert;
    }
}

package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "CE")
public class CompteEpargne extends Compte{

    private double taux;

    public CompteEpargne(String numCte, double solde, Date dateCreation, Employe employe, Client client, @NonNull Collection<Operation> operations, double taux) {
        super(numCte, solde, dateCreation, employe, client, operations);
        this.taux = taux;
    }
}

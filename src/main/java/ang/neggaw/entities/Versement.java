package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "V")
@AllArgsConstructor
public class Versement extends Operation{

    public Versement(Date dateOperation, double montant,
                     Compte compte, Employe employe) {
        super(dateOperation, montant, compte, employe);
    }
}

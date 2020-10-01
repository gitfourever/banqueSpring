package ang.neggaw.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "CE")
@XmlType(name = "CE")
public class CompteEpargne extends Compte {

    @Column(name = "taux_interet")
    private double taux;

    public CompteEpargne(String numCte, Date dateCreation, double solde,
                         Client client, Employe employe, double taux) {
        super(numCte, dateCreation, solde, client, employe);
        this.taux = taux;
    }
}

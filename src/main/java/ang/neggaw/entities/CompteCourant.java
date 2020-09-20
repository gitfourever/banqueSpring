package ang.neggaw.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue(value = "CC")
@XmlType(name = "CC")
public class CompteCourant extends Compte {

    @Column(name = "decouvert")
    private double decouvert;

    public CompteCourant(String numCte, Date dateCreation, double solde,
                         Client client, Employe employe, double decouvert) {
        super(numCte, dateCreation, solde, client, employe);
        this.decouvert = decouvert;
    }
}

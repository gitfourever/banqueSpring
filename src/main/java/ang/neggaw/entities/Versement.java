package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "V")
@AllArgsConstructor
@XmlType(name = "V")
public class Versement extends Operation{

    public Versement(Date dateOperation, double montant,
                     Compte compte, Employe employe) {
        super(dateOperation, montant, compte, employe);
    }
}

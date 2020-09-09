package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "R")
@AllArgsConstructor
@XmlType(name = "R")
public class Retrait extends Operation{
    public Retrait(Date dateOperation, double montant,
                   Compte compte, Employe employe) {
        super(dateOperation, montant, compte, employe);
    }
}

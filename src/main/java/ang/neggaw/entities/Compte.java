package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "comptes")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeCte", length = 2, discriminatorType = DiscriminatorType.STRING)
public abstract class Compte implements Serializable {

    @Id
    private String numCte;

    private double solde;

    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "compte")
    private Collection<Operation> operations;
}

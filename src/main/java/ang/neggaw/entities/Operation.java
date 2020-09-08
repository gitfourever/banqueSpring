package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "operations")
@AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeOp", length = 2, discriminatorType = DiscriminatorType.STRING)
public abstract class Operation implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numOperation;

    private Date dateOperation;

    private double montant;

    @ManyToOne
    @JoinColumn(name = "num_compte")
    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    private Employe employe;
}

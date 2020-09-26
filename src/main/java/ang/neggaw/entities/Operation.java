package ang.neggaw.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "BK_operations")
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeOp", length = 2, discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeOp")
@JsonSubTypes(value = {
        @JsonSubTypes.Type(name = "V", value = Versement.class),
        @JsonSubTypes.Type(name = "R", value = Retrait.class)
})
@XmlSeeAlso(value = { Versement.class, Retrait.class })
public abstract class Operation implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_operation", length = 17)
    private Long numOperation;

    @Transient
    private String typeOperation;

    @Column(name = "date_operation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOperation;

    @Column(name = "montant")
    private double montant;

    @ManyToOne
    @JoinColumn(name = "num_compte")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Compte compte;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Employe employe;

    public Operation(Date dateOperation, double montant,
                     Compte compte, Employe employe) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.compte = compte;
        this.employe = employe;
    }
}

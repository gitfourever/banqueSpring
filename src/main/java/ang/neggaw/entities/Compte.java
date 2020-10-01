package ang.neggaw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "BK_comptes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeCte",length = 2, discriminatorType = DiscriminatorType.STRING )
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeCte")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "CC", value = CompteCourant.class),
        @JsonSubTypes.Type(name = "CE", value = CompteEpargne.class),
})
@XmlSeeAlso(value = { CompteCourant.class, CompteEpargne.class })
public abstract class Compte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numCte", length = 24)
    private Long numCte;

    // @Transient
    // private String typeCte;

    @Column(name = "solde")
    private double solde;

    @Column(name = "dateCreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "id_client")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

    @OneToMany(mappedBy = "compte")
    @XmlTransient
    @JsonIgnore
    private Collection<Operation> operations;

    // contructeur
    public Compte(Date dateCreation, double solde, Client client, Employe employe) {
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
        this.employe = employe;
    }
}

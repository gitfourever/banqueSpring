package ang.neggaw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "clients")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", length = 17)
    private Long idClient;

    @Column(name = "nom_client", length = 33)
    private String nomClient;

    @OneToMany(mappedBy = "client")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @XmlTransient
    @JsonIgnore
    private Collection<Compte> comptes;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    @JsonIgnore
    private Employe employe;

    public Client(String nomClient, Employe employe) {
        this.nomClient = nomClient;
        this.employe = employe;
    }
}

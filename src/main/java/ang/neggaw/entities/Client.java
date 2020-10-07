package ang.neggaw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

@Entity(name = "BK_clients")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 17)
    private Long idClient;

    @Column(name = "nom", length = 33)
    private String nomClient;

    @Column(length = 40, name = "email")
    private String emailClient;

    @OneToOne
    @Transient
    private ClientOnline clientOnline;

    @OneToMany(mappedBy = "client")
    @XmlTransient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Compte> comptes;

    @ManyToOne
    @JoinColumn(name = "employe_id_employe")
    @JsonIgnore
    private Employe employe;

    public Client(String nomClient, String email, Employe employe) {
        this.nomClient = nomClient;
        this.emailClient = email;
        this.employe = employe;
    }
}

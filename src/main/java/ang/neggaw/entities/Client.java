package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "clients")
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nomClient;

    @OneToMany(mappedBy = "client")
    private Collection<Compte> comptes;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    private Employe employe;
}

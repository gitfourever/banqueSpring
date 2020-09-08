package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "clients")
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nomClient;

    @OneToMany(mappedBy = "client")
    @NonNull
    private Collection<Compte> comptes;

    @ManyToOne
    @JoinColumn(name = "id_employe")
    private Employe employe;
}

package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "employes")
@AllArgsConstructor @NoArgsConstructor
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    private String nomEmploye;

    @ManyToOne
    @JoinColumn(name = "id_patron")
    @ToString.Exclude
    private Employe empSuperieur;

    @OneToMany(mappedBy = "employe")
    @ToString.Exclude
    @NonNull
    private Collection<Compte> comptes;
}

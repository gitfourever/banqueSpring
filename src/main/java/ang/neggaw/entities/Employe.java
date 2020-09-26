package ang.neggaw.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "BK_employes")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Employe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employe", length = 17)
    private Long idEmploye;

    @Column(name = "nom_Employe", length = 33)
    private String nomEmploye;

    @ManyToOne
    @JoinColumn(name = "id_superieur")
    @ToString.Exclude
    @XmlTransient
    private Employe empSuperieur;

    @OneToMany(mappedBy = "employe")
    @ToString.Exclude
    @JsonIgnore
    @XmlTransient
    private Collection<Compte> comptes;

    @OneToMany(mappedBy = "employe")
    @JsonIgnore
    @XmlTransient
    private Collection<Operation> operations;

    public Employe(String nomEmploye, Employe superieur) {
        this.nomEmploye = nomEmploye;
        this.empSuperieur = superieur;
    }
}

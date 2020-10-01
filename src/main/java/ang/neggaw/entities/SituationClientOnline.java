package ang.neggaw.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "BK_situationClientOnline")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class SituationClientOnline implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStuationClientOnline;

    @Column(unique = true)
    private String nomSituation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BK_groupe_user_situations",
            joinColumns = @JoinColumn(name = "SituationClientOnline", referencedColumnName = "nomSituation"),
            inverseJoinColumns = @JoinColumn(name = "ClientOnline", referencedColumnName = "emailClient")
    )
    private Collection<ClientOnline> clientOnlines;
}

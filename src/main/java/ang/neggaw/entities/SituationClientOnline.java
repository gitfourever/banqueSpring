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

    @Column(unique = true, name = "type_situation")
    private String nomSituation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BK_groupe_user_situations",
            joinColumns = @JoinColumn(name = "situation_client_online", referencedColumnName = "type_situation"),
            inverseJoinColumns = @JoinColumn(name = "client_online", referencedColumnName = "email_client")
    )
    private Collection<ClientOnline> clientsOnline;
}

package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "BK_clientOnline")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class ClientOnline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client_online", length = 17)
    private Long idClientOnline;

    @Column(length = 40, unique = true, name = "email_client")
    private String emailClient;

    @Column(length = 64, nullable = false)
    private String password;

    @Transient
    private String repassword;

    private boolean enabled;

    @OneToOne
    private Client client;

    @ManyToMany(mappedBy = "clientsOnline", fetch = FetchType.EAGER)
    private Collection<SituationClientOnline> situationClientOnlines;
}

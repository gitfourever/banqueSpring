package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "BK_clientOnline")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class ClientOnline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClientOnline;

    @Column(length = 40, unique = true, name = "username")
    private String username;

    @Column(length = 64, nullable = false)
    private String password;

    @Transient
    private String repassword;

    private boolean enabled;

    @OneToOne
    private Client client;

    @ManyToMany(mappedBy = "clientOnlines", fetch = FetchType.EAGER)
    private Collection<SituationClientOnline> situationClientOnlines;

    @Override
    public String toString() {
        return "ClientOnline{" +
                "idClientOnline=" + idClientOnline +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repassword='" + repassword + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

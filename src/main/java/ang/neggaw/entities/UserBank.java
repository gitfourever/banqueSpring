package ang.neggaw.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userBanks")
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class UserBank implements Serializable {

    @Id
    @Column(length = 40, unique = true, name = "email")
    private String userEmail;

    @Column(length = 64, nullable = false)
    private String password;

    @Transient
    private String repassword;

    private boolean situation;

    @OneToOne
    private Client client;
}

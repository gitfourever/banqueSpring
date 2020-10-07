package ang.neggaw.entities;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class AngularModule {

    private String typeCte;
    private double solde;
    private double decouvert;
    private double taux;
    private long idClient;

    private String emailClient;
    private String passwordOld;
    private String passwordNew;
    private String repasswordNew;

    private Long numCte01;
    private Long numCte02;
    private double montant;
}

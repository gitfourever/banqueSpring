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

    @Override
    public String toString() {
        return "AngularModule{" +
                "typeCte='" + typeCte + '\'' +
                ", solde=" + solde +
                ", decouvert=" + decouvert +
                ", taux=" + taux +
                ", idClient=" + idClient +
                ", emailClient='" + emailClient + '\'' +
                ", passwordOld='" + passwordOld + '\'' +
                ", passwordNew='" + passwordNew + '\'' +
                ", repasswordNew='" + repasswordNew + '\'' +
                '}';
    }
}

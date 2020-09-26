package ang.neggaw.metier;

import ang.neggaw.entities.ClientOnline;
import ang.neggaw.entities.SituationClientOnline;

public interface IClientOnlineMetier {
    public void addSituation2ClientOnline(ClientOnline clientOnline, SituationClientOnline situationClientOnline);
}

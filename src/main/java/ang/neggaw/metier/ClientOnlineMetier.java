package ang.neggaw.metier;

import ang.neggaw.dao.ClientOnlineRepository;
import ang.neggaw.dao.SituationClientOnlineRepository;
import ang.neggaw.entities.ClientOnline;
import ang.neggaw.entities.SituationClientOnline;
import org.springframework.stereotype.Service;

@Service
public class ClientOnlineMetier implements IClientOnlineMetier {

    private ClientOnlineRepository clientOnlineRepository;
    private SituationClientOnlineRepository situationClientOnlineRepository;

    public ClientOnlineMetier(ClientOnlineRepository clientOnlineRepository, SituationClientOnlineRepository situationClientOnlineRepository) {
        this.clientOnlineRepository = clientOnlineRepository;
        this.situationClientOnlineRepository = situationClientOnlineRepository;
    }

    @Override
    public void addSituation2ClientOnline(ClientOnline clientOnline, SituationClientOnline situationClientOnline) {
        clientOnline.getSituationClientOnlines().add(situationClientOnline);
        situationClientOnline.getClientsOnline().add(clientOnline);

        clientOnlineRepository.saveAndFlush(clientOnline);
        situationClientOnlineRepository.saveAndFlush(situationClientOnline);
    }
}

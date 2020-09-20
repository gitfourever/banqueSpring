package ang.neggaw.metier;

import ang.neggaw.dao.ClientRepository;
import ang.neggaw.entities.Client;
import ang.neggaw.entities.UserBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientMetier implements IClientMetier {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private IUserBankMetier userBankMetier;

    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
        userBankMetier.addUserBank(new UserBank(client.getEmailClient(), "1234", "1234", true, client));
    }
}

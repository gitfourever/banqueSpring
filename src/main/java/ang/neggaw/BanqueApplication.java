package ang.neggaw;

import ang.neggaw.dao.*;
import ang.neggaw.entities.*;
import ang.neggaw.metier.IClientOnlineMetier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;


@SpringBootApplication
//@ImportResource("spring-beans.xml")
public class BanqueApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BanqueApplication.class, args);

        RepositoryRestConfiguration repositoryRestConfiguration = context.getBean(RepositoryRestConfiguration.class);
        repositoryRestConfiguration.exposeIdsFor(Client.class, Compte.class, Operation.class);


        /*
        EmployeRepository employeRepository = context.getBean(EmployeRepository.class);
        ClientRepository clientRepository = context.getBean(ClientRepository.class);
        ClientOnlineRepository clientOnlineRepository = context.getBean(ClientOnlineRepository.class);
        SituationClientOnlineRepository situationClientOnlineRepository = context.getBean(SituationClientOnlineRepository.class);
        CompteRepository compteRepository = context.getBean(CompteRepository.class);
        OperationRepository operationRepository = context.getBean(OperationRepository.class);

        IClientOnlineMetier clientOnlineMetier = context.getBean(IClientOnlineMetier.class);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // création des employés
        employeRepository.save(new Employe("empSuperieur", null));
        employeRepository.save(new Employe("emp001", employeRepository.getOne(1l)));
        employeRepository.save(new Employe("emp002", employeRepository.getOne(1l)));

        // création des clients
        clientRepository.save(new Client("client001", "client001@email.com", employeRepository.getOne(2l)));
        clientRepository.save(new Client("client002",  "client002@email.com",employeRepository.getOne(3l)));
        clientRepository.save(new Client("client003",  "client003@email.com",employeRepository.getOne(2l)));

        // création de situationClientOnline
        situationClientOnlineRepository.save(new SituationClientOnline(0L,"ACTIF", null));
        situationClientOnlineRepository.save(new SituationClientOnline(0L, "INACTIF", null));
        situationClientOnlineRepository.save(new SituationClientOnline(0L, "SUSPENDU", null));
        situationClientOnlineRepository.save(new SituationClientOnline(0L, "BLOQUE", null));

        // création de clientOnline
        Client c001 = clientRepository.findByNomClient("client001");
        Client c002 = clientRepository.findByNomClient("client002");
        Client c003 = clientRepository.findByNomClient("client003");

        clientOnlineRepository.save(new ClientOnline(0L, c001.getEmailClient(), passwordEncoder.encode("1234"), passwordEncoder.encode("1234"), true, c001, null));
        clientOnlineRepository.save(new ClientOnline(0L, c002.getEmailClient(), passwordEncoder.encode("1234"), passwordEncoder.encode("1234"), true, c002, null ));
        clientOnlineRepository.save(new ClientOnline(0L, c003.getEmailClient(), passwordEncoder.encode("1234"), passwordEncoder.encode("1234"), true, c003, null ));

        clientOnlineMetier.addSituation2ClientOnline(clientOnlineRepository.findByEmailClient(c001.getEmailClient()), situationClientOnlineRepository.findByNomSituation("ACTIF"));
        clientOnlineMetier.addSituation2ClientOnline(clientOnlineRepository.findByEmailClient(c002.getEmailClient()), situationClientOnlineRepository.findByNomSituation("ACTIF"));
        clientOnlineMetier.addSituation2ClientOnline(clientOnlineRepository.findByEmailClient(c003.getEmailClient()), situationClientOnlineRepository.findByNomSituation("INACTIF"));
        clientOnlineMetier.addSituation2ClientOnline(clientOnlineRepository.findByEmailClient(c003.getEmailClient()), situationClientOnlineRepository.findByNomSituation("SUSPENDU"));
        */
    }
}

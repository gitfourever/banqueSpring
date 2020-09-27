package ang.neggaw;

import ang.neggaw.dao.*;
import ang.neggaw.entities.*;
import ang.neggaw.metier.IClientOnlineMetier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
//@ImportResource("spring-beans.xml")
public class BanqueApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BanqueApplication.class, args);


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

        clientOnlineMetier.addSituation2ClientOnline(clientOnlineRepository.findByUsername(c001.getEmailClient()), situationClientOnlineRepository.findByNomSituation("ACTIF"));
        clientOnlineMetier.addSituation2ClientOnline(clientOnlineRepository.findByUsername(c002.getEmailClient()), situationClientOnlineRepository.findByNomSituation("ACTIF"));
        clientOnlineMetier.addSituation2ClientOnline(clientOnlineRepository.findByUsername(c003.getEmailClient()), situationClientOnlineRepository.findByNomSituation("INACTIF"));
        clientOnlineMetier.addSituation2ClientOnline(clientOnlineRepository.findByUsername(c003.getEmailClient()), situationClientOnlineRepository.findByNomSituation("SUSPENDU"));


        // création des comptes
        compteRepository.save(new CompteCourant("CC001", new Date(), Math.random() * 10000, clientRepository.getOne(1l), employeRepository.getOne(2l), Math.random() * 3000));
        compteRepository.save(new CompteCourant("CC002", new Date(), Math.random() * 10000, clientRepository.getOne(2l), employeRepository.getOne(3l), Math.random() * 3000));
        compteRepository.save(new CompteCourant("CC003", new Date(), Math.random() * 10000, clientRepository.getOne(3l), employeRepository.getOne(2l), Math.random() * 3000));

        compteRepository.save(new CompteEpargne("CE001", new Date(), Math.random() * 50000, clientRepository.getOne(1l), employeRepository.getOne(2l), Math.random() * 7));
        compteRepository.save(new CompteEpargne("CE002", new Date(), Math.random() * 50000, clientRepository.getOne(2l), employeRepository.getOne(3l), Math.random() * 7));
        compteRepository.save(new CompteEpargne("CE003", new Date(), Math.random() * 50000, clientRepository.getOne(3l), employeRepository.getOne(2l), Math.random() * 7));

        // créations des opérations
        operationRepository.save(new Versement(new Date(), Math.random() * 5000, compteRepository.getOne("CC001"), employeRepository.getOne(2l)));
        operationRepository.save(new Versement(new Date(), Math.random() * 3000, compteRepository.getOne("CC002"), employeRepository.getOne(2l)));
        operationRepository.save(new Versement(new Date(), Math.random() * 7000, compteRepository.getOne("CC003"), employeRepository.getOne(2l)));

        operationRepository.save(new Retrait(new Date(), Math.random() * 3000, compteRepository.getOne("CC003"), employeRepository.getOne(3l)));
        operationRepository.save(new Retrait(new Date(), Math.random() * 2000, compteRepository.getOne("CC001"), employeRepository.getOne(3l)));
        operationRepository.save(new Retrait(new Date(), Math.random() * 1000, compteRepository.getOne("CC002"), employeRepository.getOne(3l)));
        */

    }
}

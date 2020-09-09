package ang.neggaw;

import ang.neggaw.dao.ClientRepository;
import ang.neggaw.dao.CompteRepository;
import ang.neggaw.dao.EmployeRepository;
import ang.neggaw.dao.OperationRepository;
import ang.neggaw.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
//@ImportResource("spring-beans.xml")
public class BanqueApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BanqueApplication.class, args);

        EmployeRepository employeRepository = context.getBean(EmployeRepository.class);
        ClientRepository clientRepository = context.getBean(ClientRepository.class);
        CompteRepository compteRepository = context.getBean(CompteRepository.class);
        OperationRepository operationRepository = context.getBean(OperationRepository.class);


        employeRepository.save(new Employe("empSuperieur", null));
        employeRepository.save(new Employe("emp001", employeRepository.getOne(1l)));
        employeRepository.save(new Employe("emp002", employeRepository.getOne(1l)));

        clientRepository.save(new Client("client001", employeRepository.getOne(2l)));
        clientRepository.save(new Client("client002", employeRepository.getOne(3l)));
        clientRepository.save(new Client("client003", employeRepository.getOne(2l)));


        compteRepository.save(new CompteCourant("CC001", new Date(), Math.random() * 10000, clientRepository.getOne(1l), employeRepository.getOne(2l), Math.random() * 3000));
        compteRepository.save(new CompteCourant("CC002", new Date(), Math.random() * 10000, clientRepository.getOne(2l), employeRepository.getOne(3l), Math.random() * 3000));
        compteRepository.save(new CompteCourant("CC003", new Date(), Math.random() * 10000, clientRepository.getOne(3l), employeRepository.getOne(2l), Math.random() * 3000));

        compteRepository.save(new CompteEpargne("CE001", new Date(), Math.random() * 50000, clientRepository.getOne(1l), employeRepository.getOne(2l), Math.random() * 7));
        compteRepository.save(new CompteEpargne("CE002", new Date(), Math.random() * 50000, clientRepository.getOne(2l), employeRepository.getOne(3l), Math.random() * 7));
        compteRepository.save(new CompteEpargne("CE003", new Date(), Math.random() * 50000, clientRepository.getOne(3l), employeRepository.getOne(2l), Math.random() * 7));

        operationRepository.save(new Versement(new Date(), Math.random() * 5000, compteRepository.getOne("CC001"), employeRepository.getOne(2l)));
        operationRepository.save(new Versement(new Date(), Math.random() * 3000, compteRepository.getOne("CC002"), employeRepository.getOne(2l)));
        operationRepository.save(new Versement(new Date(), Math.random() * 7000, compteRepository.getOne("CC003"), employeRepository.getOne(2l)));

        operationRepository.save(new Retrait(new Date(), Math.random() * 3000, compteRepository.getOne("CC003"), employeRepository.getOne(3l)));
        operationRepository.save(new Retrait(new Date(), Math.random() * 2000, compteRepository.getOne("CC001"), employeRepository.getOne(3l)));
        operationRepository.save(new Retrait(new Date(), Math.random() * 1000, compteRepository.getOne("CC002"), employeRepository.getOne(3l)));
    }
}

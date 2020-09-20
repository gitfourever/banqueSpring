package ang.neggaw;

import ang.neggaw.dao.ClientRepository;
import ang.neggaw.dao.CompteRepository;
import ang.neggaw.dao.EmployeRepository;
import ang.neggaw.dao.OperationRepository;
import ang.neggaw.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BanqueApplicationTests {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    OperationRepository operationRepository;


    @Test
    public void whenFindByName_ThenReturnEmploye() {

        // given
        Employe employe001 = new Employe("Employe001", null);
        employeRepository.save(employe001);

        // when
        Employe employeFound = employeRepository.findByNomEmploye(employe001.getNomEmploye());

        // then
        employeRepository.findAll().forEach(System.out::println);
        assertEquals(employeFound.getNomEmploye(), employe001.getNomEmploye());
    }

    @Test
    public void whenFindByName_ThenReturnClient() {

        // given
        Client client001 = new Client(null, "Client001", "client001@email.com", null, employeRepository.getOne(1L));
        clientRepository.save(client001);

        // when
        Client clientFound = clientRepository.findByNomClient(client001.getNomClient());

        // then
        assertEquals(client001.getNomClient(), clientFound.getNomClient());
    }


    @Test
    public void whenFinByNumCte_ThenReturnNumCte(){

        // given
        Compte compte001 = new CompteCourant("CC001", new Date(), Math.random() + 1000, clientRepository.getOne(1l), employeRepository.getOne(1l), 1000);

        // when
        Compte compteFound = compteRepository.getOne("CC001");

        // then
        assertEquals(compte001.getNumCte(), compteFound.getNumCte());
    }

    @Test
    public void whenFinByNumOp_ThenReturnNumOp(){

        // given
        Operation opV001 = new Versement(new Date(), Math.random() + 500, compteRepository.getOne("CC001"), employeRepository.getOne(1L));

        // when
        Compte compte = compteRepository.getOne("CC001");

        // then
        assertEquals(opV001.getCompte().getNumCte(), compte.getNumCte());
    }
    /*
    */
}
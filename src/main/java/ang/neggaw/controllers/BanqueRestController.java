package ang.neggaw.controllers;

import ang.neggaw.dao.*;
import ang.neggaw.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.List;

@RestController
public class BanqueRestController {

    private final EmployeRepository employeRepository;
    private final ClientRepository clientRepository;
    private final ClientOnlineRepository clientOnlineRepository;
    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;

    public BanqueRestController(EmployeRepository employeRepository,
                                ClientRepository clientRepository,
                                ClientOnlineRepository clientOnlineRepository,
                                CompteRepository compteRepository,
                                OperationRepository operationRepository) {
        this.clientOnlineRepository = clientOnlineRepository;
        this.employeRepository = employeRepository;
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }

    private BCryptPasswordEncoder passwordEncoder;

    /// employes ///
    @GetMapping(value = "/apiRest/employe/{code}")
    public Employe getEmploye(@PathVariable Long code) {
        return employeRepository.getOne(code);
    }


    /// clients ///
    @GetMapping(value = "/apiRest/client/clientById/{code}")
    public Client getClient(@PathVariable(name = "code") Long code) {
        return clientRepository.findByIdClient(code);
    }

    @GetMapping(value = "/apiRest/client/clientByEmail/{email}")
    public Client getClientXemail(@PathVariable(name = "email") String email) {
        Client client = clientRepository.findByEmailClient(email);
        if (client == null) throw new RuntimeException("Erreur dans les données saisies. Client not found !!!");
        return client;
    }

    @GetMapping(value = "/apiRest/clientOnline/{emailClient}")
    public ClientOnline getClientOnlineByEmail(@PathVariable String emailClient) {
        ClientOnline clientOnline = clientOnlineRepository.findByEmailClient(emailClient);
        if (clientOnline == null) throw new RuntimeException("Erreur dans les données saisies. Client not found !!!");
        return clientOnline;
    }

    @PutMapping(value = "/apiRest/clientOnline/updatePassword/")
    @ResponseBody
    public boolean updatePasswordClientOnline(@RequestBody AngularModule angularModule) {

        System.out.println(angularModule.toString());
        passwordEncoder = new BCryptPasswordEncoder();
        ClientOnline clientOnline = clientOnlineRepository.getOne(angularModule.getIdClient());

        if(passwordEncoder.matches(angularModule.getPasswordOld(), clientOnline.getPassword())) {
            System.out.println(angularModule.getPasswordNew() + " : " + angularModule.getRepasswordNew());
            if (angularModule.getPasswordNew().equals(angularModule.getRepasswordNew())) {
                clientOnline.setPassword(passwordEncoder.encode(angularModule.getPasswordNew()));
                clientOnlineRepository.saveAndFlush(clientOnline);
            }
            throw new RuntimeException("Error: Not match password new !!!");
        }
        else throw new RuntimeException("Error: bad credentials !!!");
    }

    /// comptes ///
    @PostMapping(value = "/apiRest/compte/addCte")
    public void addCompte(@RequestBody AngularModule newCte) {

         if (newCte.getTypeCte().equals("CC")) {
             compteRepository.save(new CompteCourant(new Date(), 0, clientRepository.findByIdClient(newCte.getIdClient()), null, 50));
         } else if (newCte.getTypeCte().equals("CE")) {
             compteRepository.save(new CompteEpargne(new Date(), 0, clientRepository.findByIdClient(newCte.getIdClient()), null, 0.10));
         }
    }

    @PutMapping(value = "/apiRest/compte/updateCte")
    public void updateCompte(@RequestParam(name = "compte") Compte c) {
        compteRepository.saveAndFlush(c);
    }

    @DeleteMapping(value = "/apiRest/compte/deleteCte/{numCte}")
    public void deleteCompte(@PathVariable(name = "numCte") long code) {
        compteRepository.delete(compteRepository.getOne(code));
    }

    @GetMapping(value = "/apiRest/compte/comptesClient/{idClient}")
    public Collection<Compte> comptesClient(@PathVariable long idClient) {
        return compteRepository.findComptesByClient(clientRepository.getOne(idClient));
    }

    @GetMapping(value = "/apiRest/compte/{numCte}")
    public Compte getCompte(@PathVariable long numCte) {
        if(compteRepository.findById(numCte).get() == null) throw new RuntimeException("Compte avec numéro: " + numCte + " est introuvable !!!");
        else {
            return compteRepository.findById(numCte).get();
        }
    }

    /// operations ///
    @PutMapping(value = "/apiRest/operations/versement")
    @ResponseBody
    public void verser(@RequestBody AngularModule ngModule) {

        Compte cte = compteRepository.findById(ngModule.getNumCte01()).get();
        Operation op = operationRepository.save(new Versement(new Date(), ngModule.getMontant(), cte, null));
        cte.setSolde(cte.getSolde() + ngModule.getMontant());
        cte.getOperations().add(op);
        compteRepository.saveAndFlush(cte);
    }

    @PutMapping(value = "/apiRest/operations/retrait")
    public void retirer(@RequestBody AngularModule ngModule ) {
        Compte cte = compteRepository.findById(ngModule.getNumCte01()).get();
        if(ngModule.getMontant() > cte.getSolde()) throw new RuntimeException("Solde insuffisant !!!");
        else {
            Operation op = operationRepository.save(new Retrait(new Date(), ngModule.getMontant(), cte, null));
            cte.setSolde(cte.getSolde() - ngModule.getMontant());
            cte.getOperations().add(op);
            compteRepository.saveAndFlush(cte);
        }
    }

    @Transactional
    @PutMapping(value = "/apiRest/operations/virement")
    public void virement(@RequestBody AngularModule ngModule) {
        AngularModule opVerser = new AngularModule();
        opVerser.setNumCte01(ngModule.getNumCte02());
        opVerser.setMontant(ngModule.getMontant());

        retirer(ngModule);
        verser(opVerser);
    }

    @GetMapping(value = "/apiRest/operations/compte/{numCte}")
    public List<Operation> operationsCompte(@PathVariable long numCte) {
        return operationRepository.findOperationsByCompte(compteRepository.findById(numCte).get());
    }

    @GetMapping(value = "/apiRest/operations/compte/{numCte}/{page}/{reg}")
    public Page<Operation> operationsComptexPage(@PathVariable long numCte, @PathVariable int page, @PathVariable int reg) {
        return operationRepository.findOperationsByCompte(compteRepository.findById(numCte).get(), PageRequest.of(page, reg));
    }

    @GetMapping(value = "/apiRest/operations/{ref}")
    public Operation getOperation(@PathVariable Long ref) {
        return operationRepository.getOne(ref);
    }
}

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
    @GetMapping(value = "/apiRest/clientById/{code}")
    public Client getClient(@PathVariable(name = "code") Long code) {
        return clientRepository.findByIdClient(code);
    }

    @GetMapping(value = "/apiRest/clientByEmail/{email}")
    public Client getClientXemail(@PathVariable(name = "email") String email) {
        Client client = clientRepository.findByEmailClient(email);
        if (client == null) throw new RuntimeException("Erreur dans les données saisies. Client not found !!!");
        return client;
    }

    @GetMapping(value = "/apiRest/getClientOnline/{emailClient}")
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
    @PostMapping(value = "/apiRest/compte", consumes = "application/json", produces = "application/json")
    public void addCompte(@RequestBody AngularModule newCte) {
         // System.out.println(newCte.toString());

         if (newCte.getTypeCte().equals("CC")) {
             CompteCourant c = new CompteCourant();
             c.setClient(clientRepository.getOne(newCte.getIdClient()));
             c.setDateCreation(new Date());
             c.setEmploye(null);
             c.setSolde(newCte.getSolde());
             c.setDecouvert(newCte.getDecouvert());
             compteRepository.save(c);
         } else if (newCte.getTypeCte().equals("CE")) {
             CompteEpargne c = new CompteEpargne();
             c.setClient(clientRepository.getOne(newCte.getIdClient()));
             c.setDateCreation(new Date());
             c.setEmploye(null);
             c.setSolde(newCte.getSolde());
             c.setTaux(newCte.getTaux());
             compteRepository.save(c);
         }
    }

    @PutMapping(value = "/apiRest/comptes")
    public void updateCompte(@RequestParam(name = "compte") Compte c) {
        compteRepository.saveAndFlush(c);
    }

    @DeleteMapping(value = "/apiRest/comptes/{numCte}")
    public void deleteCompte(@PathVariable(name = "numCte") long code) {
        compteRepository.delete(compteRepository.getOne(code));
    }

    @GetMapping(value = "/apiRest/comptes")
    public List<Compte> listeComptes() {
        return compteRepository.findAll();
    }

    @GetMapping(value = "/apiRest/comptesByClient/{idClient}")
    public Collection<Compte> comptesByClient(@PathVariable long idClient) {
        return compteRepository.findComptesByClient(clientRepository.getOne(idClient));
    }

    @GetMapping(value = "/apiRest/comptes/pages")
    public Page<Compte> comptesXpages(int p, int r) {
        return compteRepository.findAll(PageRequest.of(p, r));
    }

    @GetMapping(value = "/apiRest/compte/{numCte}")
    public Compte getCompte(@PathVariable long numCte) {
        // Compte cte = compteRepository.getOne(numCte);
        // if(cte == null) throw new RuntimeException("Compte " + numCte + " est introuvable !!!");
        return compteRepository.getOne(numCte);
    }
    /*
    */

    /// operations ///
    @PutMapping(value = "/apiRest/versement")
    public void verser(@RequestParam(name = "montant") double mt,
                       @RequestParam(name = "numCte") long numCte,
                       @RequestParam(name = "idEmploye") Long idEmploye) {
        Compte cte = compteRepository.getOne(numCte); //getCompte(numCte);

        Operation op = operationRepository.save(new Versement(new Date(), mt, cte, employeRepository.getOne(idEmploye)));
        cte.setSolde(cte.getSolde() + mt);

        cte.getOperations().add(op);
    }

    @PutMapping(value = "/apiRest/retrait")
    public void retirer(@RequestParam(name = "montant") double mt,
                        @RequestParam(name = "code") long numCte,
                        @RequestParam(name = "idEmploye") Long idEmploye) {
        Compte cte = compteRepository.getOne(numCte);
        if(mt > cte.getSolde()) throw new RuntimeException("Solde insuffisant !!!");
        else {
            Operation op = operationRepository.save(new Retrait(new Date(), mt, cte, employeRepository.getOne(idEmploye)));
            cte.setSolde(cte.getSolde() - mt);

            cte.getOperations().add(op);
        }
    }

    @Transactional
    @PutMapping(value = "/apiRest/virement")
    public void virement(@RequestParam(name = "montant") double mt,
                         @RequestParam(name = "code01") long numCte01,
                         @RequestParam(name = "code02") long numCte02,
                         @RequestParam(name = "idEmploye") Long idEmploye) {
        retirer(mt, numCte01, idEmploye);
        verser(mt, numCte02, idEmploye);
    }


    @GetMapping(value = "/apiRest/operations/{numCte}")
    public List<Operation> operationsByCte(@PathVariable long numCte) {
        return operationRepository.findOperationByCompte(numCte);
    }

    @GetMapping(value = "/apiRest/operations/{numCte}/{page}/{reg}")
    public Page<Operation> operationsByCtexPage(@PathVariable long numCte, @PathVariable int page, @PathVariable int reg) {
        return operationRepository.findOperationsByCompte(numCte, PageRequest.of(page, reg));
    }

    @GetMapping(value = "/apiRest/operations/{ref}")
    public Operation getOperation(@PathVariable Long ref) {
        return operationRepository.getOne(ref);
    }
}

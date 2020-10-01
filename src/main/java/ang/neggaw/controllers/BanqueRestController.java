package ang.neggaw.controllers;

import ang.neggaw.dao.ClientRepository;
import ang.neggaw.dao.CompteRepository;
import ang.neggaw.dao.EmployeRepository;
import ang.neggaw.dao.OperationRepository;
import ang.neggaw.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
public class BanqueRestController {

    private final EmployeRepository employeRepository;
    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;

    public BanqueRestController(EmployeRepository employeRepository,
                                ClientRepository clientRepository,
                                CompteRepository compteRepository,
                                OperationRepository operationRepository) {
        this.employeRepository = employeRepository;
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }

    /// employes ///
    @PostMapping(value = "/employe")
    public void addEmploye(@RequestBody Employe e) {
        employeRepository.save(e);
    }

    @GetMapping(value = "/apiRest/employe/{code}")
    public Employe getEmploye(@PathVariable Long code) {
        return employeRepository.getOne(code);
    }


    /// clients ///
    @PostMapping(value = "/client")
    public void addClient(@RequestBody Client c) {
        clientRepository.save(c);
    }

    @GetMapping(value = "/clients")
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @GetMapping(value = "/clients/pages")
    public Page<Client> clientsXpages(int p, int r) {
        return clientRepository.findAll(PageRequest.of(p, r));
    }

    @GetMapping(value = "/apiRest/client/{code}")
    public Client getClient(@PathVariable(name = "code") Long code) {
        return clientRepository.getOne(code);
    }

    @GetMapping(value = "/apiRest/client/{email}")
    public Client getClientXemail(@PathVariable(name = "email") String email) {
        return clientRepository.findByEmailClient(email);
    }

    /// comptes ///
    @PostMapping(value = "/apiRest/compte")
    public void addCompte(@RequestBody Compte c) {
        System.out.println("addCompte: " + c.getClient().getNomClient());
        compteRepository.save(c);
        // Operation op = operationRepository.save(new Versement(c.getDateCreation(), c.getSolde(), c, c.getEmploye()));
        // c.getOperations().add(op);
    }

    @PutMapping(value = "/apiRest/comptes")
    public void updateCompte(@RequestParam(name = "compte") Compte c) {
        compteRepository.saveAndFlush(c);
    }

    @DeleteMapping(value = "/apiRest/comptes/{numCte}")
    public void deleteCompte(@PathVariable(name = "numCte") String code) {
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
    public Compte getCompte(@PathVariable String numCte) {
        // Compte cte = compteRepository.getOne(numCte);
        // if(cte == null) throw new RuntimeException("Compte " + numCte + " est introuvable !!!");
        return compteRepository.getOne(numCte);
    }
    /*
    */

    /// operations ///
    @PutMapping(value = "/apiRest/versement")
    public void verser(@RequestParam(name = "montant") double mt,
                       @RequestParam(name = "numCte") String numCte,
                       @RequestParam(name = "idEmploye") Long idEmploye) {
        Compte cte = compteRepository.getOne(numCte); //getCompte(numCte);

        Operation op = operationRepository.save(new Versement(new Date(), mt, cte, employeRepository.getOne(idEmploye)));
        cte.setSolde(cte.getSolde() + mt);

        cte.getOperations().add(op);
    }

    @PutMapping(value = "/apiRest/retrait")
    public void retirer(@RequestParam(name = "montant") double mt,
                        @RequestParam(name = "code") String numCte,
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
                         @RequestParam(name = "code01") String numCte01,
                         @RequestParam(name = "code02") String numCte02,
                         @RequestParam(name = "idEmploye") Long idEmploye) {
        retirer(mt, numCte01, idEmploye);
        verser(mt, numCte02, idEmploye);
    }


    @GetMapping(value = "/apiRest/operations/{numCte}")
    public List<Operation> operationsByCte(@PathVariable String numCte) {
        return operationRepository.findOperationByCompte(numCte);
    }

    @GetMapping(value = "/apiRest/operations/{numCte}/{page}/{reg}")
    public Page<Operation> operationsByCtexPage(@PathVariable String numCte, @PathVariable int page, @PathVariable int reg) {
        return operationRepository.findOperationsByCompte(numCte, PageRequest.of(page, reg));
    }

    @GetMapping(value = "/apiRest/operations/{ref}")
    public Operation getOperation(@PathVariable Long ref) {
        return operationRepository.getOne(ref);
    }
}

package ang.neggaw.controllers;

import ang.neggaw.dao.CompteRepository;
import ang.neggaw.dao.EmployeRepository;
import ang.neggaw.dao.OperationRepository;
import ang.neggaw.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController()
//@RequestMapping(value = "/apiRest")
@CrossOrigin
public class BanqueRestController {

    private final EmployeRepository employeRepository;
    private final CompteRepository compteRepository;
    private final OperationRepository operationRepository;

    public BanqueRestController(EmployeRepository employeRepository, CompteRepository compteRepository, OperationRepository operationRepository) {
        this.employeRepository = employeRepository;
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }

    /*
    /// employes ///
    @PostMapping(value = "/employe/{employe}")
    public void addEmploye(@RequestBody Employe e) {
        employeRepository.save(e);
    }

    @GetMapping(value = "/employe/{code}")
    public Employe getEmploye(@PathVariable Long code) {
        return employeRepository.getOne(code);
    }


    /// clients ///
    @PostMapping(value = "/client/{client}")
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

    @GetMapping(value = "/client/{code}")
    public Client getClient(@PathVariable(name = "code") Long code) {
        return clientRepository.getOne(code);
    }

    /// comptes ///
    @PostMapping(value = "/compte")
    public void addCompte(@RequestBody Compte c) {
        compteRepository.save(c);
        Operation op = operationRepository.save(new Versement(c.getDateCreation(), c.getSolde(), c, c.getEmploye()));

        c.getOperations().add(op);
    }

    @PutMapping(value = "/comptes/{compte}")
    public void updateCompte(@RequestParam(name = "compte") Compte c) {
        compteRepository.saveAndFlush(c);
    }

    @DeleteMapping(value = "/comptes/{code}")
    public void deleteCompte(@PathVariable(name = "numCte") String code) {
        compteRepository.delete(compteRepository.getOne(code));
    }

    @GetMapping(value = "/comptes")
    public List<Compte> listeComptes() {
        return compteRepository.findAll();
    }

    @GetMapping(value = "/comptes/pages")
    public Page<Compte> comptesXpages(int p, int r) {
        return compteRepository.findAll(PageRequest.of(p, r));
    }

    @GetMapping(value = "/compte/{code}")
    public Compte getCompte(@PathVariable String numCte) {
        Compte cte = compteRepository.getOne(numCte);
        if(cte == null) throw new RuntimeException("Compte " + numCte + " est introuvable !!!");
        return cte;
    }
    */

    /// operations ///
    @PutMapping(value = "/versement")
    public void verser(@RequestParam(name = "montant") double mt,
                       @RequestParam(name = "numCte") String numCte,
                       @RequestParam(name = "idEmploye") Long idEmploye) {
        Compte cte = compteRepository.getOne(numCte); //getCompte(numCte);

        Operation op = operationRepository.save(new Versement(new Date(), mt, cte, employeRepository.getOne(idEmploye)));
        cte.setSolde(cte.getSolde() + mt);

        cte.getOperations().add(op);
    }

    @PutMapping(value = "/retrait")
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
    @PutMapping(value = "/virement")
    public void virement(@RequestParam(name = "montant") double mt,
                         @RequestParam(name = "code01") String numCte01,
                         @RequestParam(name = "code02") String numCte02,
                         @RequestParam(name = "idEmploye") Long idEmploye) {
        retirer(mt, numCte01, idEmploye);
        verser(mt, numCte02, idEmploye);
    }


    @GetMapping(value = "/operations/{numCte}")
    public List<Operation> operationsByCte(@PathVariable String numCte) {
        return operationRepository.findOperationByCompte(numCte);
    }

    @GetMapping(value = "/operations/{numCte}/{page}/{reg}")
    public Page<Operation> operationsByCtexPage(@PathVariable String numCte, @PathVariable int page, @PathVariable int reg) {
        return operationRepository.findOperationsByCompte(numCte, PageRequest.of(page, reg));
    }

    @GetMapping(value = "/operations/{ref}")
    public Operation getOperation(@PathVariable Long ref) {
        return operationRepository.getOne(ref);
    }
}

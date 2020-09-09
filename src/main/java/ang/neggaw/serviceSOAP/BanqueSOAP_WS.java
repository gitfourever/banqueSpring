package ang.neggaw.serviceSOAP;

import ang.neggaw.dao.ClientRepository;
import ang.neggaw.dao.EmployeRepository;
import ang.neggaw.dao.OperationRepository;
import ang.neggaw.entities.Client;
import ang.neggaw.entities.Employe;
import ang.neggaw.entities.Operation;
import ang.neggaw.metier.IOperationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@Component
@WebService
public class BanqueSOAP_WS {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private IOperationMetier operationMetier;


    // employes
    @WebMethod(operationName = "addEmploye")
    public Employe addEmploye(@WebParam(name = "nomEmploye") String nomEmpl, @WebParam(name = "idSuperieur") long idSup) {
        return employeRepository.save(new Employe(nomEmpl, employeRepository.getOne(idSup)));
    }

    @WebMethod(operationName = "employes")
    public List<Employe> employes() {
        return employeRepository.findAll();
    }

    // clients
    @WebMethod(operationName = "addClient")
    public Client addClient (@WebParam(name = "nomClient") String nomCli, @WebParam(name = "idEmploye") long idEmp){
        return clientRepository.save(new Client(nomCli, employeRepository.getOne(idEmp)));
    }

    @WebMethod(operationName = "getClient")
    public Client getClient (@WebParam(name = "idClient") Long idCli){
        return clientRepository.getOne(idCli);
    }

    @WebMethod(operationName = "clients")
    public List<Client> clients(){
        return clientRepository.findAll();
    }

    // operations
    @WebMethod(operationName = "versement")
    public void verser(@WebParam(name = "montant") double mt,
                       @WebParam(name = "numCte") String numCte,
                       @WebParam(name = "idEmploye") Long idEmpl) {
        operationMetier.verser(mt, numCte, idEmpl);
    }

    @WebMethod(operationName = "retrait")
    public void retirer(@WebParam(name = "montant") double mt,
                        @WebParam(name = "numCte") String numCte,
                        @WebParam(name = "idEmploye") Long idEmploye) {
        operationMetier.retirer(mt, numCte, idEmploye);
    }

    @WebMethod(operationName = "virement")
    public void virement(@WebParam(name = "montant") double mt,
                         @WebParam(name = "numCte01") String numCte01,
                         @WebParam(name = "numCte02") String numCte02,
                         @WebParam(name = "idEmploye") Long idEmploye) {
        operationMetier.virement(mt, numCte01, numCte02, idEmploye);
    }

    @WebMethod(operationName = "operationsByCte")
    public List<Operation> operationsByCte(@WebParam(name = "numCte") String numCte) {
        return operationRepository.findOperationsByCompte(numCte);
    }
}

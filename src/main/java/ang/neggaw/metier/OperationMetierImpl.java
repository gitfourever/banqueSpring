package ang.neggaw.metier;

import java.util.Date;

import ang.neggaw.dao.CompteRepository;
import ang.neggaw.dao.EmployeRepository;
import ang.neggaw.dao.OperationRepository;
import ang.neggaw.entities.*;
import ang.neggaw.web.OperationPageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OperationMetierImpl implements IOperationMetier {

	private OperationRepository operationRepository;
	private CompteRepository compteRepository;
	private EmployeRepository employeRepository;

	public OperationMetierImpl(OperationRepository operationRepository, CompteRepository compteRepository, EmployeRepository employeRepository) {
		this.operationRepository = operationRepository;
		this.compteRepository = compteRepository;
		this.employeRepository = employeRepository;
	}

	@Override
	public OperationPageConfig operationsByCte(long numCte, int page, int reg){
		
		Page<Operation> liste = operationRepository.findOperationsByCompte(numCte, PageRequest.of(page, reg));
		OperationPageConfig pop = new OperationPageConfig();
		
		pop.setNumPages(liste.getNumber());
		pop.setTotalElements(liste.getNumberOfElements());
		pop.setNumRegistres(liste.getSize());
		pop.setTotalPages(liste.getTotalPages());
		pop.setListeOperations(liste.getContent());
		pop.setNumElements(liste.getNumberOfElements());
		
		return pop;		
	}

	@Override
	public void verser(double mt, long numCte, Long idEmploye) {
		Compte cte = compteRepository.getOne(numCte);

		Operation op = operationRepository.save(new Versement(new Date(), mt, cte, employeRepository.getOne(idEmploye)));
		cte.setSolde(cte.getSolde() + mt);

		cte.getOperations().add(op);
	}

	@Override
	public void retirer(double mt, long numCte, Long idEmploye) {
		Compte cte = compteRepository.getOne(numCte);
		if(mt > cte.getSolde()) throw new RuntimeException("Solde insuffisant !!!");
		else {
			Operation op = operationRepository.save(new Retrait(new Date(), mt, cte, employeRepository.getOne(idEmploye)));
			cte.setSolde(cte.getSolde() - mt);

			cte.getOperations().add(op);
		}
		
	}

	@Override
	public void virement(double mt, long numCte01, long numCte02, Long idEmploye) {
		retirer(mt, numCte01, idEmploye);;
		verser(mt, numCte02, idEmploye);
	}
}

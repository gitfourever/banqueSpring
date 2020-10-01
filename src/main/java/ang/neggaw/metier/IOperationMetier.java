package ang.neggaw.metier;

import ang.neggaw.web.OperationPageConfig;

public interface IOperationMetier {

	OperationPageConfig operationsByCte(String numCte, int page, int reg);
	void verser(double mt, String numCte, Long idEmploye);
	void retirer(double mt, String numCte, Long idEmploye);
	void virement(double mt, String numCte01, String numCte02, Long idEmploye);
}

package ang.neggaw.metier;

import ang.neggaw.web.OperationPageConfig;

public interface IOperationMetier {

	OperationPageConfig operationsByCte(long numCte, int page, int reg);
	void verser(double mt, long numCte, Long idEmploye);
	void retirer(double mt, long numCte, Long idEmploye);
	void virement(double mt, long numCte01, long numCte02, Long idEmploye);
}

package ang.neggaw.metier;

import ang.neggaw.web.OperationPageConfig;

public interface IOperationMetier {

	public OperationPageConfig operationsByCte(String numCte, int page, int reg);
	public void verser(double mt, String numCte, Long idEmploye);
	public void retirer(double mt, String numCte, Long idEmploye);
	public void virement(double mt, String numCte01, String numCte02, Long idEmploye);
}
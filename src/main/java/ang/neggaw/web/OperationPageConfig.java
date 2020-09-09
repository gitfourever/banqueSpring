package ang.neggaw.web;

import ang.neggaw.entities.Operation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class OperationPageConfig {

    private int totalElements;
    private int totalPages;
    private int numRegistres;
    private int numPages;
    private int numElements;
    private List<Operation> listeOperations;
}

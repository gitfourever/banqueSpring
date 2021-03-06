package ang.neggaw.dao;

import ang.neggaw.entities.Compte;
import ang.neggaw.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OperationRepository extends JpaRepository<Operation, Long> {
    Page<Operation> findOperationsByCompte(Compte cte, Pageable pageable);
    List<Operation> findOperationsByCompte(Compte cte);

}

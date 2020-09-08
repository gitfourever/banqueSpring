package ang.neggaw.dao;

import ang.neggaw.entities.Compte;
import ang.neggaw.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EmployeRepository extends JpaRepository<Employe, Long> {
}

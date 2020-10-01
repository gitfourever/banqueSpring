package ang.neggaw.dao;

import ang.neggaw.entities.Client;
import ang.neggaw.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte, String> {
    Collection<Compte> findComptesByClient(Client c);

}

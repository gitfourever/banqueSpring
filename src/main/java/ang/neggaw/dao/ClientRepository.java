package ang.neggaw.dao;

import ang.neggaw.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByNomClient(String nomClient);
    Client findByEmailClient(String email);
}

package ang.neggaw.dao;

import ang.neggaw.entities.Client;
import ang.neggaw.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findByNomClient(String nomClient);
}

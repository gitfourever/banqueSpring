package ang.neggaw.dao;

import ang.neggaw.entities.ClientOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientOnlineRepository extends JpaRepository<ClientOnline, String> {
    ClientOnline findByUsername(String email);
}

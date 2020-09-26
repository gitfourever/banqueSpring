package ang.neggaw.dao;

import ang.neggaw.entities.SituationClientOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SituationClientOnlineRepository extends JpaRepository<SituationClientOnline, String> {
    SituationClientOnline findByNomSituation(String situation);
}

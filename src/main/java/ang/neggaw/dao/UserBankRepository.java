package ang.neggaw.dao;

import ang.neggaw.entities.UserBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserBankRepository extends JpaRepository<UserBank, String> {
    public UserBank findUserBankByUserEmail(String email);
}

package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.SiptisUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiptisUserRepository extends JpaRepository<SiptisUser, Integer> {

    Optional<SiptisUser> findOneByEmail(String email);

    Optional<SiptisUser> existsByEmail(String email);

}

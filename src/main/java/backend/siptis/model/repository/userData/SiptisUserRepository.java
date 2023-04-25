package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.SiptisUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiptisUserRepository extends JpaRepository<SiptisUser, Long> {

    @Override
    List<SiptisUser> findAll();

    Optional<SiptisUser> findOneByEmail(String email);

    boolean existsByEmail(String email);

    Optional<SiptisUser> findOneById(Long id);

}

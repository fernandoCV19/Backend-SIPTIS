package backend.siptis.auth.repository;

import backend.siptis.auth.entity.SiptisUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<SiptisUser, Integer> {

    Optional<SiptisUser> findOneByEmail(String email);
}

package backend.siptis.auth.repository;

import backend.siptis.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> {

    Optional<User> findOneByEmail(String email);
}

package backend.siptis.model.repository.auth;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByToken(String token);

    boolean existsBySiptisUser(SiptisUser user);

    RefreshToken findBySiptisUser(SiptisUser user);

    Optional<RefreshToken> findByToken(String token);
}

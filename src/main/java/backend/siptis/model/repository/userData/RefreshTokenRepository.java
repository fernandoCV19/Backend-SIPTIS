package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByToken(String token);

    boolean existsBySiptisUser(SiptisUser user);

    RefreshToken findBySiptisUser(SiptisUser user);

    RefreshToken findByToken(String token);

    @Modifying
    int deleteBySiptisUser(SiptisUser user);
}

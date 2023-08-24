package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByToken(String token);

    boolean existsBySiptisUser(SiptisUser user);

    @Query(value = "SELECT rt.siptis_user_id " +
            "FROM refresh_token rt " +
            "WHERE rt.token LIKE CONCAT( '%', :searchToken, '%') ", nativeQuery = true)
    Long getSiptisUserId(String searchToken);

    RefreshToken findBySiptisUser(SiptisUser user);

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteBySiptisUser(SiptisUser user);
}

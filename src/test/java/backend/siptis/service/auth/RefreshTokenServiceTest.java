package backend.siptis.service.auth;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.user_data.UserInformationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RefreshTokenServiceTest {
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private SiptisUserRepository siptisUserRepository;
    private RefreshToken refreshToken;

    private void startToken() {
        refreshToken = new RefreshToken();
        LocalDate date = LocalDate.of(2022, 7, 7);
        refreshToken.setExpireDate(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private RefreshToken registerToken() {
        SiptisUser user = new SiptisUser();
        return refreshTokenService.createRefreshToken(user);
    }

    @Test
    @DisplayName("test for find by token null")
    void givenTokenWhenFindByTokenThenNull() {
        assertNull(refreshTokenService.findByToken(""));
    }

    @Test
    @DisplayName("test for find by token success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenTokenWhenFindByTokenThenRefreshToken() {
        Optional<SiptisUser> oUser = siptisUserRepository.findById(100L);
        RefreshToken token = refreshTokenService.createRefreshToken(oUser.get());
        assertNotNull(refreshTokenService.findByToken(token.getToken()));
    }

    @Test
    @DisplayName("test for create refresh token with siptis user")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenNullWhenCreateRefreshTokenBySiptisUserThenNull() {
        assertNull(refreshTokenService.createRefreshToken((SiptisUser) null));
    }

    @Test
    @DisplayName("test for create refresh token with siptis user")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUserWhenCreateRefreshTokenBySiptisUserThenRefreshToken() {
        Optional<SiptisUser> oUser = siptisUserRepository.findById(100L);
        assertNotNull(refreshTokenService.createRefreshToken(oUser.get()));
    }

    @Test
    @DisplayName("test for get create refresh token with user detail null")
    void givenNullWhenCreateRefreshTokenByUserDetailImpThenRefreshToken() {
        assertNull(refreshTokenService.createRefreshToken((UserInformationService.UserDetailImp) null));
    }

    @Test
    @DisplayName("test for verify expiration date refresh token false")
    void givenRefreshTokenWhenVerifyExpirationDateThenFalse() {
        startToken();
        assertFalse(refreshTokenService.verifyValidExpirationDate(refreshToken));
    }

    @Test
    @DisplayName("test for verify expiration date refresh token")
    void givenRefreshTokenWhenVerifyExpirationDateThenTrue() {
        startToken();
        LocalDate date = LocalDate.of(2032, 7, 7);
        refreshToken.setExpireDate(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        assertTrue(refreshTokenService.verifyValidExpirationDate(refreshToken));
    }
}

package backend.siptis.service.auth;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.service.user_data.UserInformationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class RefreshTokenServiceTest {
    @Autowired
    private RefreshTokenService refreshTokenService;
    private RefreshToken refreshToken;

    private void startToken() {
        refreshToken = new RefreshToken();
        LocalDate date = LocalDate.of(2022, 7, 7);
        refreshToken.setExpireDate(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Test
    @DisplayName("test for get allowed roles")
    void givenTokenWhenFindByTokenThenRefreshToken() {
        assertNull(refreshTokenService.findByToken(""));
    }

    @Test
    @DisplayName("test for create refresh token with siptis user")
    @Sql(scripts = {"/custom_imports/refresh_token_service_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenNullWhenCreateRefreshTokenBySiptisUserThenRefreshToken() {
        assertNull(refreshTokenService.createRefreshToken((SiptisUser) null));
    }

    @Test
    @DisplayName("test for get create refresh token with user detail")
    void givenNullWhenCreateRefreshTokenByUserDetailImpThenRefreshToken() {
        assertNull(refreshTokenService.createRefreshToken((UserInformationService.UserDetailImp) null));
    }

    @Test
    @DisplayName("test for verify expiration date refresh token")
    void givenRefreshTokenWhenVerifyExpirationDateThenTrue() {
        startToken();
        assertFalse(refreshTokenService.verifyValidExpirationDate(refreshToken));
    }

}

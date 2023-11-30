package backend.siptis.service.auth;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
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
/*
    @Test
    @DisplayName("test for find by token null")
    void givenToken_WhenFindByToken_ThenNull() {
        assertNull(refreshTokenService.findByToken(""));
    }

    @Test
    @DisplayName("test for success find by token")
    @Sql(scripts = {"/custom_imports/refresh_token_service_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void givenTokenWhenFindByTokenThenRefreshToken() {
        assertNotNull(refreshTokenService.findByToken("e954f412-d3f8-457f-8c47-a249043140d69"));
    }

    @Test
    @DisplayName("test for create refresh token with siptis user")
    @Sql(scripts = {"/custom_imports/refresh_token_service_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void givenNullWhenCreateRefreshTokenBySiptisUserThenNull() {
        assertNull(refreshTokenService.createRefreshToken((SiptisUser) null));
    }
    @Test
    @DisplayName("test for create refresh token with siptis user")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql(scripts = {"/custom_imports/refresh_token_service_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUserWhenCreateRefreshTokenBySiptisUserThenRefreshToken() {
        Optional<SiptisUser> oUser = siptisUserRepository.findById(44l);
        assertNotNull(refreshTokenService.createRefreshToken(oUser.get()));
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
*/
}

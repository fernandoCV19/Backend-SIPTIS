package backend.siptis.service.auth;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class RefreshTokenServiceTest {
    private RefreshTokenService refreshTokenService;

    @Autowired
    RefreshTokenServiceTest(RefreshTokenService refreshTokenService){
        this.refreshTokenService = refreshTokenService;
    }

    @Test
    @DisplayName("test for get allowed roles")
    public void GivenTokenWhenFindByTokenThenRefreshToken(){
        assertNull(refreshTokenService.findByToken(""));
    }

    @Test
    @DisplayName("test for get create refresh token")
    @Sql(scripts = {"/custom_imports/refresh_token_service_test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void GivenSiptisUserWhenCreateRefreshTokenThenRefreshToken(){
        assertTrue(true);
    }
    /*
    @Test
    @DisplayName("test for get create refresh token")
    public void GivenSiptisUbserWhenCreateRefreshTokenThenRefreshToken(){
        SiptisUser user = new SiptisUser();
        assertNull(refreshTokenService.createRefreshToken(user));
    }
*/

    /**
     * RefreshToken findByToken(String token);
     *     RefreshToken createRefreshToken(UserInformationService.UserDetailImp userDI);
     *     RefreshToken createRefreshToken(SiptisUser user);
     *     boolean verifyValidExpirationDate(RefreshToken refreshToken);
     */
}

package backend.siptis.model.repository.auth;


import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.SiptisUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RefreshTokenRepositoryTest {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private SiptisUserRepository siptisUserRepository;
    private SiptisUser siptisUser;

    private void createRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken("token");
        siptisUser = siptisUserRepository.findById(100L).get();
        refreshToken.setSiptisUser(siptisUser);
        refreshToken.setExpireDate(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        refreshTokenRepository.save(refreshToken);
    }
    @Test
    @DisplayName("Test for exist by Token")
    void givenBadToken_whenExistByToken_thenFalse() {
        assertFalse(refreshTokenRepository.existsByToken("bad token"));
    }
    @Test
    @DisplayName("Test for exist by Token")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadToken_whenExistByToken_thenTrue() {
        createRefreshToken();
        assertTrue(refreshTokenRepository.existsByToken("token"));
    }

    @Test
    @DisplayName("Test for exist by Siptis User false")
    void givenBadSiptisUser_whenExistBySiptisUser_thenFalse() {
        assertFalse(refreshTokenRepository.existsBySiptisUser(siptisUser));
    }
    @Test
    @DisplayName("Test for exist by Siptis User true")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadSiptisUser_whenExistBySiptisUser_thenTrue() {
        createRefreshToken();
        assertTrue(refreshTokenRepository.existsBySiptisUser(siptisUser));
    }

    @Test
    @DisplayName("Test for find by Siptis User null")
    void givenBadSiptisUser_whenFindBySiptisUser_thenNull() {
        assertNull(refreshTokenRepository.findBySiptisUser(siptisUser));
    }
    @Test
    @DisplayName("Test for find by Siptis User Refresh Token")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadSiptisUser_whenFindBySiptisUser_thenRefreshToken() {
        createRefreshToken();
        assertNotNull(refreshTokenRepository.findBySiptisUser(siptisUser));
    }

    @Test
    @DisplayName("Test for find by token null")
    void givenBadToken_whenFindByToken_thenNull() {
        assertTrue(refreshTokenRepository.findByToken("bad token").isEmpty());
    }
    @Test
    @DisplayName("Test for find by token Refresh Token")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadToken_whenFindByToken_thenRefreshToken() {
        createRefreshToken();
        assertFalse(refreshTokenRepository.findByToken("token").isEmpty());
    }
}

package backend.siptis.model.repository.auth;


import backend.siptis.auth.entity.SiptisUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class RefreshTokenRepositoryTest {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    private SiptisUser siptisUser;

    @Test
    @DisplayName("Test for exist by Token")
    void givenBadToken_whenExistByToken_thenFalse(){
        assertFalse(refreshTokenRepository.existsByToken("bad token"));
    }
    @Test
    @DisplayName("Test for exist by Siptis User")
    void givenBadSiptisUser_whenExistBySiptisUser_thenFalse(){
        assertFalse(refreshTokenRepository.existsBySiptisUser(siptisUser));
    }
    @Test
    @DisplayName("Test for find by Siptis User")
    void givenBadSiptisUser_whenFindBySiptisUser_thenNull(){
        assertNull(refreshTokenRepository.findBySiptisUser(siptisUser));
    }
    @Test
    @DisplayName("Test for find by token")
    void givenBadToken_whenFindByToken_thenNull(){
        assertTrue(refreshTokenRepository.findByToken("bad token").isEmpty());
    }
}

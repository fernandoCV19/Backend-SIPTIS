package backend.siptis.model.repository.auth;

import backend.siptis.auth.entity.SiptisUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RefreshTokenRepositoryTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    private SiptisUser siptisUser;

    @BeforeEach
    public void setupTestData(){
        siptisUser = new SiptisUser();
        siptisUser.setEmail("exampleEmail@gmail.com");
        siptisUser.setPassword("12121212");
    }
    private SiptisUser createNewUser(){
        SiptisUser siptisUser2 = new SiptisUser();
        siptisUser2.setEmail("exampleEmail2@gmail.com");
        siptisUser2.setPassword("12121212");
        siptisUser2.setTokenPassword("abcd1234");
        return siptisUser2;
    }

    @Test
    @DisplayName("Test for get empty user list")
    public void givenSiptisUser_whenExistBySiptisUser_thenTrue(){
        // refreshTokenRepository.existsBySiptisUser()
    }


}

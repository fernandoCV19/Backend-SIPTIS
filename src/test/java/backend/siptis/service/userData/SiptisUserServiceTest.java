package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SiptisUserServiceTest {

    private SiptisUserService service;
    private UserAuthService authService;

    @Autowired
    SiptisUserServiceTest(SiptisUserService service, UserAuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @Test
    void getByEmailThatNoExist(){
        a
        Boolean answer = service.existsByEmail("fake@gmail.com");
        assertFalse(answer);
    }

    @Test
    void getByEmailThatExist(){
        SiptisUser user = new SiptisUser("maury.vargasl@gmail.com","mavl");
        service.save(user);

        Boolean answer = service.existsByEmail("maury.vargasl@gmail.com");
        assertTrue(answer);
    }
}

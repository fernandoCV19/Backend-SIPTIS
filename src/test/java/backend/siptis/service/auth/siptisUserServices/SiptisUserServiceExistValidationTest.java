package backend.siptis.service.auth.siptisUserServices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceExistValidationTest {
    @Autowired
    private SiptisUserServiceExistValidation siptisUserServiceExistValidation;

    @Test
    @DisplayName("test for check if exist user by id")
    void givenIdWhenExistByIdThenTrue(){
        assertFalse(siptisUserServiceExistValidation.existsUserById(123456l));
    }
    @Test
    @DisplayName("test for check if exist user by email")
    void givenEmailWhenExistUserByEmailThenFalse(){
        assertFalse(siptisUserServiceExistValidation.existsUserByEmail("fake email"));
    }

    @Test
    @DisplayName("test for check if exist director role")
    void givenDirectorRoleWhenExistCareerDirectorFalse(){
        assertFalse(siptisUserServiceExistValidation.existCareerDirector("fake email"));
    }
}

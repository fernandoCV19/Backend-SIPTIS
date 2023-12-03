package backend.siptis.service.auth.siptis_user_services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SiptisUserServiceExistValidationTest {
    @Autowired
    private SiptisUserServiceExistValidation siptisUserServiceExistValidation;
    @Autowired
    private SiptisUserServiceCareerDirectorOperations siptisUserServiceCareerDirectorOperations;

    private void registerDirector(){
        siptisUserServiceCareerDirectorOperations.registerUserAsCareerDirector(120L, "INF_DIRECTOR");
    }
    @Test
    @DisplayName("test for check if exist user by id false")
    void givenIdWhenExistsUserByIdThenFalse() {
        assertFalse(siptisUserServiceExistValidation.existsUserById(123456L));
    }
    @Test
    @DisplayName("test for check if exist user by id true")
    void givenIdWhenExistsUserByIdThenTrue() {
        assertTrue(siptisUserServiceExistValidation.existsUserById(100L));
    }

    @Test
    @DisplayName("test for check if exist user by email false")
    void givenEmailWhenExistUserByEmailThenFalse() {
        assertFalse(siptisUserServiceExistValidation.existsUserByEmail("fake email"));
    }
    @Test
    @DisplayName("test for check if exist user by email true")
    void givenEmailWhenExistUserByEmailThenTrue() {
        assertTrue(siptisUserServiceExistValidation.existsUserByEmail("laura.admin@mail.com"));
    }


    @Test
    @DisplayName("test for check if exist director role")
    void givenDirectorRoleWhenExistCareerDirectorFalse() {
        assertFalse(siptisUserServiceExistValidation.existCareerDirector("INF_DIRECTOR"));
    }
    @Test
    @DisplayName("test for check if exist director role")
    void givenDirectorRoleWhenExistCareerDirectorTrue() {
        registerDirector();
        assertTrue(siptisUserServiceExistValidation.existCareerDirector("INF_DIRECTOR"));
    }
}

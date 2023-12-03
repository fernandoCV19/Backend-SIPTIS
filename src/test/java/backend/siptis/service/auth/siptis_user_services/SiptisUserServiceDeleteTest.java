package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SiptisUserServiceDeleteTest {

    @Autowired
    private SiptisUserServiceDelete siptisUserServiceDelete;
    @Autowired
    private SiptisUserServiceCareerDirectorOperations siptisUserServiceCareerDirectorOperations;

    private void registerDirector() {
        siptisUserServiceCareerDirectorOperations.registerUserAsCareerDirector(120L, "INF_DIRECTOR");
    }

    @Test
    @DisplayName("test for delete user by wrong id")
    void givenUserIdWhenDeleteUserThenServiceMessageID_DOES_NOT_EXIST() {
        ServiceAnswer answer = siptisUserServiceDelete.deleteUser(123456L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test for delete user success")
    void givenUserIdWhenDeleteUserThenServiceMessageUSER_DELETED() {
        ServiceAnswer answer = siptisUserServiceDelete.deleteUser(119L);
        assertEquals(ServiceMessage.USER_DELETED, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test for remove director role not found")
    void givenDierectorRoleWhenRemoveDirectorRoleThenServiceMessageNOT_FOUND() {
        ServiceAnswer answer = siptisUserServiceDelete.removeDirectorRole("wrong role");
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test for remove director role success")
    void givenDierectorRoleWhenRemoveDirectorRoleThenServiceMessageOK() {
        registerDirector();
        ServiceAnswer answer = siptisUserServiceDelete.removeDirectorRole("INF_DIRECTOR");
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for verify if exist director false")
    void givenDirectorRoleWhenExistCareerDirectorThenFalse() {
        assertFalse(siptisUserServiceDelete.existCareerDirector("wrong role"));
    }

    @Test
    @DisplayName("Test for verify if exist director true")
    void givenDirectorRoleWhenExistCareerDirectorThenTrue() {
        registerDirector();
        assertTrue(siptisUserServiceDelete.existCareerDirector("INF_DIRECTOR"));
    }
}

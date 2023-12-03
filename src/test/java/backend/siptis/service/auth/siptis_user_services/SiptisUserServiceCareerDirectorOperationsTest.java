package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SiptisUserServiceCareerDirectorOperationsTest {
    @Autowired
    private SiptisUserServiceCareerDirectorOperations siptisUserServiceCareerDirectorOperations;

    @Test
    @DisplayName("test register user as career director role not found")
    void givenWrongRoleAndUserIDWhenRegisterUserAsCareerDirectorThenServiceMessageDIRECTOR_ROLE_NOT_FOUND() {
        ServiceAnswer answer = siptisUserServiceCareerDirectorOperations.registerUserAsCareerDirector(123L, "wrong role");
        assertEquals(ServiceMessage.DIRECTOR_ROLE_NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test register user as career director success")
    void givenWrongRoleAndUserIDWhenRegisterUserAsCareerDirectorThenServiceMessageOK() {
        ServiceAnswer answer = siptisUserServiceCareerDirectorOperations.registerUserAsCareerDirector(120L, "INF_DIRECTOR");
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test get director personal information not found")
    void givenWrongRoleWhenGetDirectorPersonalInformationThenServiceMessageNOT_FOUND() {
        ServiceAnswer answer = siptisUserServiceCareerDirectorOperations.getDirectorPersonalInformation("wrong role");
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }
    @Test
    @DisplayName("test get director personal information success")
    void givenWrongRoleWhenGetDirectorPersonalInformationThenServiceMessageOK() {
        siptisUserServiceCareerDirectorOperations.registerUserAsCareerDirector(120L, "INF_DIRECTOR");
        ServiceAnswer answer = siptisUserServiceCareerDirectorOperations.getDirectorPersonalInformation("INF_DIRECTOR");
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test get Career director name null")
    void givenWrongRoleWhenGetCareerDirectorNameThenNull() {
        String answer = siptisUserServiceCareerDirectorOperations.getCareerDirectorName("wrong career");
        assertNull(answer);
    }
    @Test
    @DisplayName("Test get Career director name success")
    void givenRoleNameWhenGetCareerDirectorNameThenString() {
        siptisUserServiceCareerDirectorOperations.registerUserAsCareerDirector(120L, "INF_DIRECTOR");
        String answer = siptisUserServiceCareerDirectorOperations.getCareerDirectorName("wrong career");
        assertNull(answer);
    }
}

package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceCareerDirectorOperationsTest {
    @Autowired
    private SiptisUserServiceCareerDirectorOperations siptisUserServiceCareerDirectorOperations;

    @Test
    @DisplayName("test register user as career director")
    void givenWrongRoleAndUserIDWhenRegisterUserAsCareerDirectorThenServiceMessageError() {
        ServiceAnswer answer = siptisUserServiceCareerDirectorOperations.registerUserAsCareerDirector(123L, "wrong role");
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test get director personal information")
    void givenWrongRoleWhenGetDirectorPersonalInformationThenServiceMessageError() {
        ServiceAnswer answer = siptisUserServiceCareerDirectorOperations.getDirectorPersonalInformation("wrong role");
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test get Career director name")
    void givenWhenThenServiceMessage() {
        String answer = siptisUserServiceCareerDirectorOperations.getCareerDirectorName("wrong career");
        assertNull(answer);
    }
}

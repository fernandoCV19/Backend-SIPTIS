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
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceDeleteTest {

    @Autowired
    private SiptisUserServiceDelete siptisUserServiceDelete;

    @Test
    @DisplayName("test for delete user by wrong id")
    void givenUserIdWhenDeleteUserThenServiceMessageID_DOES_NOT_EXIST() {
        ServiceAnswer answer = siptisUserServiceDelete.deleteUser(123L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test for remove director role")
    void givenDierectorRoleWhenRemoveDirectorRoleThenServiceMessageNOT_FOUND() {
        ServiceAnswer answer = siptisUserServiceDelete.removeDirectorRole("wrong role");
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for verify if exist director")
    void givenWhenThenServiceMessage() {
        assertFalse(siptisUserServiceDelete.existCareerDirector("wrong role"));
    }
}

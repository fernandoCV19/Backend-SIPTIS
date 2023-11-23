package backend.siptis.service.auth.siptisUserServices;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceRolesOperationsTest {
    @Autowired
    private SiptisUserServiceRolesOperations siptisUserServiceRolesOperations;

    @Test
    @DisplayName("test get roles by id")
    void givenUserIdWhenGetRolesByIdThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceRolesOperations.getRolesById(123123L).getServiceMessage());
    }

    @Test
    @DisplayName("test get update roles by id")
    void givenUserIdWhenUpdateRolesByIdThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceRolesOperations.updateRoles(123123L, null).getServiceMessage());
    }
}

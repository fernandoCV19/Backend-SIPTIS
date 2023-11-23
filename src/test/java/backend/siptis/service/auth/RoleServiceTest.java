package backend.siptis.service.auth;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class RoleServiceTest {
    private final RoleService roleService;

    @Autowired
    RoleServiceTest(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    @DisplayName("test for get allowed roles")
    void givenRolesWhenGetAllowedRolesThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, roleService.getAllowedRoles().getServiceMessage());
    }

    @Test
    @DisplayName("test for get allowed roles")
    void givenRoleNameWhenGetRoleByNameThenServiceMessageERROR() {
        assertEquals(ServiceMessage.ERROR, roleService.getRoleByName("wrong name").getServiceMessage());
    }

    @Test
    @DisplayName("test for get not assignable roles")
    void givenRolesWhenGetNotAssignableRolesThenServiceMessageOk() {
        Set<String> list = roleService.notAssignableRoles();
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("test for get director roles")
    void givenRolesWhenGetDirectorRolesThenServiceMessageOk() {
        Set<String> list = roleService.directorRoles();
        assertFalse(list.isEmpty());
    }
}

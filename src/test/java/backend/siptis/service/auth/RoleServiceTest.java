package backend.siptis.service.auth;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class RoleServiceTest {
    private RoleService roleService;

    @Autowired
    RoleServiceTest(RoleService roleService){
        this.roleService = roleService;
    }

    @Test
    @DisplayName("test for get allowed roles")
    public void GivenRolesWhenGetAllowedRolesThenServiceMessageOk(){
        assertEquals(ServiceMessage.OK,roleService.getAllowedRoles().getServiceMessage());
    }
    @Test
    @DisplayName("test for get allowed roles")
    public void GivenRoleNameWhenGetRoleByNameThenServiceMessageERROR(){
        assertEquals(ServiceMessage.ERROR,roleService.getRoleByName("wrong name").getServiceMessage());
    }
    @Test
    @DisplayName("test for get not assignable roles")
    public void GivenRolesWhenGetNotAssignableRolesThenServiceMessageOk(){
        Set<String> list = roleService.notAssignableRoles();
        assertFalse(list.isEmpty());
    }
    @Test
    @DisplayName("test for get director roles")
    public void GivenRolesWhenGetDirectorRolesThenServiceMessageOk(){
        Set<String> list = roleService.directorRoles();
        assertFalse(list.isEmpty());
    }
}

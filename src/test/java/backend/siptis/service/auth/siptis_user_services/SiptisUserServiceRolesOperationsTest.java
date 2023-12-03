package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.RolesListDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SiptisUserServiceRolesOperationsTest {
    @Autowired
    private SiptisUserServiceRolesOperations siptisUserServiceRolesOperations;
    private RolesListDTO rolesListDTO;

    private void createRolesListDTO(){
        rolesListDTO = new RolesListDTO();
        rolesListDTO.setRoles(List.of(104L));
    }

    @Test
    @DisplayName("test get roles by id not found")
    void givenUserIdWhenGetRolesByIdThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceRolesOperations.getRolesById(123123L).getServiceMessage());
    }
    @Test
    @DisplayName("test get roles by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserIdWhenGetRolesByIdThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceRolesOperations.getRolesById(100L).getServiceMessage());
    }

    @Test
    @DisplayName("test get update roles by id not found")
    void givenUserIdWhenUpdateRolesByIdThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceRolesOperations.updateRoles(123123L, null).getServiceMessage());
    }
    @Test
    @DisplayName("test get update roles by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserIdWhenUpdateRolesByIdThenServiceMessageOK() {
        createRolesListDTO();
        assertEquals(ServiceMessage.OK, siptisUserServiceRolesOperations.updateRoles(120L, rolesListDTO).getServiceMessage());
    }
}

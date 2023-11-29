package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.RegisterAdminDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceAdminOperationsTest {
    @Autowired
    private SiptisUserServiceAdminOperations siptisUserServiceAdminOperations;
    private RegisterAdminDTO registerAdminDTO;

    private void startAdminDTO() {
        registerAdminDTO = new RegisterAdminDTO();
        registerAdminDTO.setEmail("estudiante1@gmail.com");
        registerAdminDTO.setPassword("12121212");
    }

    @Test
    @Rollback
    @DisplayName("test register admin with already existing email")
    @Sql(scripts = {"/custom_imports/create_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenAdminDTO_WhenRegisterAdmin_ThenServiceMessageEMAIL_ALREADY_EXIST() {
        startAdminDTO();
        ServiceAnswer answer = siptisUserServiceAdminOperations.registerAdmin(registerAdminDTO);
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test get admin user list")
    void given_SiptisUsers_WhenGetAdminUserList_ThenServiceMessageOk() {
        ServiceAnswer answer = siptisUserServiceAdminOperations.getAdminUserList("", Pageable.ofSize(10));
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test admin edit user")
    void givenWrongUserId_WhenEditUserInformation_ThenServiceMessageID_DOES_NOT_EXIST() {
        ServiceAnswer answer = siptisUserServiceAdminOperations.adminEditUserInformation(123456L, null);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test get possible tribunals list")
    void givenSiptisUsers_WhenGetPossibleTribunals_ThenServiceMessageOk() {
        ServiceAnswer answer = siptisUserServiceAdminOperations.getPossibleTribunals();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }


}

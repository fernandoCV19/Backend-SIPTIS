package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.AdminEditUserInformationDTO;
import backend.siptis.model.pjo.dto.user_data.RegisterAdminDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class SiptisUserServiceAdminOperationsTest {
    @Autowired
    private SiptisUserServiceAdminOperations siptisUserServiceAdminOperations;
    private RegisterAdminDTO registerAdminDTO;
    private AdminEditUserInformationDTO adminEditUserInformationDTO;

    private void startAdminDTO() {
        registerAdminDTO = new RegisterAdminDTO();
        registerAdminDTO.setEmail("dilan.est@mail.com");
        registerAdminDTO.setPassword("12121212");
    }
    private void startAdminEditUserInformationDTO(){
        adminEditUserInformationDTO = new AdminEditUserInformationDTO();
        adminEditUserInformationDTO.setNames("StudentName");
        adminEditUserInformationDTO.setLastnames("StudentLastName");
        adminEditUserInformationDTO.setEmail("newe@mail.com");
        adminEditUserInformationDTO.setCelNumber("44456322");
        adminEditUserInformationDTO.setCi("56756546");
        adminEditUserInformationDTO.setCodSIS("39123");
        adminEditUserInformationDTO.setBirthDate(new Date());
    }

    @Test
    @DisplayName("test register admin with already existing email")
    void givenAdminDTOWhenRegisterAdminThenServiceMessageEMAIL_ALREADY_EXIST() {
        startAdminDTO();
        ServiceAnswer answer = siptisUserServiceAdminOperations.registerAdmin(registerAdminDTO);
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, answer.getServiceMessage());
    }
    @Test
    @DisplayName("test register admin success")
    void givenAdminDTOWhenRegisterAdminThenServiceMessageEMAIL_OK() {
        startAdminDTO();
        registerAdminDTO.setEmail("newuswer@mail.com");
        ServiceAnswer answer = siptisUserServiceAdminOperations.registerAdmin(registerAdminDTO);
        assertEquals(ServiceMessage.SUCCESSFUL_REGISTER, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test get admin user list")
    void given_SiptisUsersWhenGetAdminUserListThenServiceMessageOk() {
        ServiceAnswer answer = siptisUserServiceAdminOperations.getAdminUserList("", Pageable.ofSize(10));
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test admin edit user id does not exist")
    void givenWrongUserIdWhenEditUserInformationThenServiceMessageID_DOES_NOT_EXIST() {
        ServiceAnswer answer = siptisUserServiceAdminOperations.adminEditUserInformation(123456L, null);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }
    @Test
    @DisplayName("test admin edit user success")
    void givenWrongUserIdWhenEditUserInformationThenServiceMessageOK() {
        startAdminEditUserInformationDTO();
        ServiceAnswer answer = siptisUserServiceAdminOperations.adminEditUserInformation(100L, adminEditUserInformationDTO);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("test get possible tribunals list")
    void givenSiptisUsersWhenGetPossibleTribunalsThenServiceMessageOk() {
        ServiceAnswer answer = siptisUserServiceAdminOperations.getPossibleTribunals();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

}

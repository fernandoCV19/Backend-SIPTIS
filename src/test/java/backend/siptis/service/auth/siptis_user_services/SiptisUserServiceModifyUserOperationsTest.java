package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.RegisterUserDTO;
import backend.siptis.model.pjo.dto.user_data.UserEditInformationDTO;
import backend.siptis.model.pjo.dto.user_data.UserSelectedAreasDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SiptisUserServiceModifyUserOperationsTest {
    @Autowired
    private SiptisUserServiceModifyUserOperations siptisUserServiceModifyUserOperations;
    private RegisterUserDTO registerUserDTO;
    private UserSelectedAreasDTO userSelectedAreasDTO;
    private UserEditInformationDTO userEditInformationDTO;

    private void createRegisterUserDTO() {
        registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setEmail("dilan.est@mail.com");
        registerUserDTO.setNames("userName");
        registerUserDTO.setLastnames("userLastName");
        registerUserDTO.setCelNumber("3428324");
        registerUserDTO.setBirthDate(new Date());
        registerUserDTO.setCi("4565656");
    }
    private void createUserSelectedAreasDTO(){
        userSelectedAreasDTO = new UserSelectedAreasDTO();
        userSelectedAreasDTO.setIds(List.of(100L));
    }
    private void createUserEditInformationDTO(){
        userEditInformationDTO = new UserEditInformationDTO();
        userEditInformationDTO.setEmail("newemail@mail.com");
        userEditInformationDTO.setCelNumber("3454534544");
        userEditInformationDTO.setBirthDate(new Date());
    }
    @Test
    @DisplayName("test update user areas id does not exist")
    void givenWrongUserIdWhenUpdateAreasThenServiceMessageID_DOES_NOT_EXIST() {
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, siptisUserServiceModifyUserOperations.updateAreas(123123L, null).getServiceMessage());
    }
    @Test
    @DisplayName("test update user areas")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenWrongUserIdWhenUpdateAreasThenServiceMessageOK() {
        createUserSelectedAreasDTO();
        assertEquals(ServiceMessage.OK, siptisUserServiceModifyUserOperations.updateAreas(100L, userSelectedAreasDTO).getServiceMessage());
    }

    @Test
    @DisplayName("test user edit personal information")
    void givenWrongUserIdWhenEditPersonalInformationThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceModifyUserOperations.userEditPersonalInformation(123123L, null).getServiceMessage());
    }
    @Test
    @DisplayName("test user edit personal information success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenWrongUserIdWhenEditPersonalInformationThenServiceMessageOK() {
        createUserEditInformationDTO();
        assertEquals(ServiceMessage.OK, siptisUserServiceModifyUserOperations.userEditPersonalInformation(100L, userEditInformationDTO).getServiceMessage());
    }

    @Test
    @DisplayName("test register user only credentials")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadEmailWhenRegisterUserThenServiceMessageEMAIL_ALREADY_EXIST() {
        createRegisterUserDTO();
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, siptisUserServiceModifyUserOperations.registerUser("dilan.est@mail.com", "").getServiceMessage());
    }
    @Test
    @DisplayName("test register user only credentials")
    void givenBadEmailWhenRegisterUserThenServiceMessageOK() {
        createRegisterUserDTO();
        assertEquals(ServiceMessage.OK, siptisUserServiceModifyUserOperations.registerUser("admin@mail.com", "").getServiceMessage());
    }

    @Test
    @DisplayName("test register user failed")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenRegisterUserDTOWhenRegisterUserThenServiceMessageEMAIL_ALREADY_EXIST() {
        createRegisterUserDTO();
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, siptisUserServiceModifyUserOperations.registerUser(registerUserDTO).getServiceMessage());
    }
    @Test
    @DisplayName("test register user")
    void givenRegisterUserDTOWhenRegisterUserThenServiceMessageSUCCESSFUL_REGISTER() {
        createRegisterUserDTO();
        assertEquals(ServiceMessage.SUCCESSFUL_REGISTER, siptisUserServiceModifyUserOperations.registerUser(registerUserDTO).getServiceMessage());
    }

}

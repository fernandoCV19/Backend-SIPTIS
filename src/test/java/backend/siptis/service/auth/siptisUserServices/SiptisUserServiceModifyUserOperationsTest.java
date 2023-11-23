package backend.siptis.service.auth.siptisUserServices;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterUserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceModifyUserOperationsTest {
    @Autowired
    private SiptisUserServiceModifyUserOperations siptisUserServiceModifyUserOperations;
    private RegisterUserDTO registerUserDTO;

    private void createRegisterUserDTO(){
        registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setEmail("estudiante1@gmail.com");

    }

    @Test
    @DisplayName("test update user areas")
    void givenWrongUserIdWhenUpdateAreasThenServiceMessageID_DOES_NOT_EXIST(){
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, siptisUserServiceModifyUserOperations.updateAreas(123123l, null).getServiceMessage());
    }

    @Test
    @DisplayName("test user edit personal information")
    void givenWrongUserIdWhenEditPersonalInformationThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceModifyUserOperations.userEditPersonalInformation(123123L, null).getServiceMessage());
    }


    @Test
    @DisplayName("test register user ony credentials")
    @Sql(scripts = {"/custom_imports/create_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadEmailWhenRegisterUserThenServiceMessageEMAIL_ALREADY_EXIST(){
        createRegisterUserDTO();
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, siptisUserServiceModifyUserOperations.registerUser("estudiante1@gmail.com", "").getServiceMessage());
    }

    @Test
    @DisplayName("test register user")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void givenRegisterUserDTOWhenRegisterUserThenServiceMessageEMAIL_ALREADY_EXIST(){
        createRegisterUserDTO();
        registerUserDTO.setEmail("");
        assertEquals(ServiceMessage.ERROR, siptisUserServiceModifyUserOperations.registerUser(registerUserDTO).getServiceMessage());
    }
}

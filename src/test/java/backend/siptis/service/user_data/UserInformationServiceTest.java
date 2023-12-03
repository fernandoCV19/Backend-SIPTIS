package backend.siptis.service.user_data;


import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.user_data.RegisterUserDTO;
import backend.siptis.model.pjo.dto.user_data.UserEditInformationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserInformationServiceTest {
    @Autowired
    private UserInformationService userInformationService;
    private RegisterUserDTO registerUserDTO;
    private RegisterStudentDTO registerStudentDTO;
    private UserEditInformationDTO userEditInformationDTO;

    private void createRegisterUserDTO() {
        registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setNames("user name");
        registerUserDTO.setLastnames("user last name");
        registerUserDTO.setEmail("user@gmail.com");
        registerUserDTO.setCi("565787565");
        registerUserDTO.setCelNumber("2343243243");
        registerUserDTO.setBirthDate(new Date());
    }

    private void createRegisterStudentDTO() {
        registerStudentDTO = new RegisterStudentDTO();
        registerStudentDTO.setNames("user name");
        registerStudentDTO.setLastnames("user last name");
        registerStudentDTO.setEmail("user@gmail.com");
        registerStudentDTO.setCi("565787565");
        registerStudentDTO.setCelNumber("2343243243");
        registerStudentDTO.setBirthDate(new Date());
        registerStudentDTO.setCodSIS("204900122");
        registerStudentDTO.setCareer("INFORMATICA");
    }

    private void createUserEditInformationDTO() {
        userEditInformationDTO = new UserEditInformationDTO();
        userEditInformationDTO.setEmail("user@gmail.com");
        userEditInformationDTO.setBirthDate(new Date());
        userEditInformationDTO.setCelNumber("234345345");
    }


    @Test
    @DisplayName("Test for find by id")
    void givenNoUserInformation_WhenFindById_ThenServiceMessageOK() {
        ServiceAnswer answer = userInformationService.findById(1L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for register user with null information ")
    void givenNull_WhenRegisterUserInformation_ThenServiceMessageERROR() {
        ServiceAnswer answer = userInformationService.registerUserInformation(null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for success register user ")
    void givenRegisterUserDTO_WhenRegisterUserInformation_ThenServiceMessageERROR() {
        createRegisterUserDTO();
        ServiceAnswer answer = userInformationService.registerUserInformation(registerUserDTO);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for register student ")
    void givenNull_WhenRegisterStudentInformation_ThenServiceMessageERROR() {
        ServiceAnswer answer = userInformationService.registerUserInformation(null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for success register student")
    void givenNull_WhenRegisterStudentInformation_ThenServiceMessageOK() {
        createRegisterStudentDTO();
        ServiceAnswer answer = userInformationService.registerUserInformation(registerStudentDTO);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for user edit personal information ")
    void givenNull_WhenUserEditInformation_ThenServiceMessageERROR() {
        ServiceAnswer answer = userInformationService.userEditInformation(null, null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for admin edit user information ")
    void givenNull_WhenAdminEditUserInformation_ThenServiceMessageERROR() {
        ServiceAnswer answer = userInformationService.adminEditUserInformation(null, null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for admin edit student information ")
    void givenNull_WhenAdminEditStudentInformation_ThenServiceMessageERROR() {
        ServiceAnswer answer = userInformationService.adminEditStudentInformation(null, null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

}

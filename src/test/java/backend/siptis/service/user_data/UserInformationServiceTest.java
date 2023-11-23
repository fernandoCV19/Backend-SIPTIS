package backend.siptis.service.user_data;


import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserInformationServiceTest {
    @Autowired
    private UserInformationService userInformationService;

    @Test
    @DisplayName("Test for find by id")
    void givenNoUserInformation_WhenFindById_ThenServiceMessageOK() {
        ServiceAnswer answer = userInformationService.findById(1L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for register user ")
    void givenNull_WhenRegisterUserInformation_ThenServiceMessageERROR() {
        ServiceAnswer answer = userInformationService.registerUserInformation(null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for register student ")
    void givenNull_WhenRegisterStudentInformation_ThenServiceMessageERROR() {
        ServiceAnswer answer = userInformationService.registerUserInformation(null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
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

package backend.siptis.service.userData;


import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserInformationServiceTest {

    private UserInformationService userInformationService;
    @Autowired
    UserInformationServiceTest(UserInformationService userInformationService){
        this.userInformationService = userInformationService;
    }

    @Test
    @DisplayName("Test for find by id")
    public void givenNoUserInformationWhenFindByIdThenServiceMessageOK(){
        ServiceAnswer answer = userInformationService.findById(1l);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for register user ")
    public void givenNullWhenRegisterUserInformationThenServiceMessageERROR(){
        ServiceAnswer answer = userInformationService.registerUserInformation(null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for register student ")
    public void givenNullWhenRegisterStudentInformationThenServiceMessageERROR(){
        ServiceAnswer answer = userInformationService.registerUserInformation(null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for user edit personal information ")
    public void givenNullWhenUserEditInformationThenServiceMessageERROR(){
        ServiceAnswer answer = userInformationService.userEditInformation(null, null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for admin edit user information ")
    public void givenNullWhenAdminEditUserInformationThenServiceMessageERROR(){
        ServiceAnswer answer = userInformationService.adminEditUserInformation(null, null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for admin edit student information ")
    public void givenNullWhenAdminEditStudentInformationThenServiceMessageERROR(){
        ServiceAnswer answer = userInformationService.adminEditStudentInformation(null, null);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

}

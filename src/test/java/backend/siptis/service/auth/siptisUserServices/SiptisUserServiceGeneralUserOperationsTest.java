package backend.siptis.service.auth.siptisUserServices;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SiptisUserServiceGeneralUserOperationsTest {
    @Autowired
    private SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;

    @Test
    @DisplayName("test get project by non existing user id")
    public void givenUserIDWhenGetProjectByIdThenNullServiceMessage(){
        assertNull(siptisUserServiceGeneralUserOperations.getProjectById(123L));
    }
    @Test
    @DisplayName("test get all users")
    public void givenSiptisUsersWhenGetAllUsersThenServiceMessageOK(){
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceGeneralUserOperations.getAllUsers().getServiceMessage());
    }
    @Test
    @DisplayName("test get user personal information")
    public void givenBadUserIdWhenGetUserPersonalInformationThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND,siptisUserServiceGeneralUserOperations.getUserPersonalInformation(123123L).getServiceMessage());
    }
    @Test
    @DisplayName("test get user areas by id")
    public void givenUserIdWhenGetUserAreasByIdThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND,siptisUserServiceGeneralUserOperations.getUserAreasById(123123L).getServiceMessage());
    }
    @Test
    @DisplayName("test get user list")
    public void givenUserIdWhenGetUserListThenServiceMessageOK(){
        assertEquals(ServiceMessage.OK,siptisUserServiceGeneralUserOperations.getUserList("", "ADMIN",Pageable.ofSize(12)).getServiceMessage());
    }
    @Test
    @DisplayName("test get normal user list")
    public void givenUserIdWhenGetNormalUserListThenServiceMessageOK(){
        assertEquals(ServiceMessage.OK,siptisUserServiceGeneralUserOperations.getNormalUserList("",Pageable.ofSize(12)).getServiceMessage());
    }
    @Test
    @DisplayName("test get personal activities")
    public void givenUserIdWhenGetPersonalActivitiesThenServiceMessage(){
        assertEquals(ServiceMessage.OK,siptisUserServiceGeneralUserOperations.getPersonalActivities(123123L,Pageable.ofSize(12)).getServiceMessage());
    }
}

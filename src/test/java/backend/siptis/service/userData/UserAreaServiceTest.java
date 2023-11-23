package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAreaServiceTest {

    @Autowired
    private UserAreaService userAreaService;
    private CreateAreaDTO createAreaDTO;

    private void createDTO(){
        createAreaDTO = new CreateAreaDTO();
        createAreaDTO.setName("AREA");
    }

    @Test
    @DisplayName("Test for get all user areas list")
    void givenNoAreasWhenGetAllUserAreasThenServiceMessageOK(){
        ServiceAnswer answer = userAreaService.getAllUserAreas();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for create user area")
    void givenCreateAreaDTOWhenCreateUserAreaThenServiceMessageAREA_CREATED(){
        createDTO();
        ServiceAnswer answer = userAreaService.createUserArea(createAreaDTO);
        assertEquals(ServiceMessage.AREA_CREATED, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for delete user area")
    void givenNoArea_WhenDeleteUserArea_ThenServiceMessageAREA_NOT_FOUND(){
        createDTO();
        ServiceAnswer answer = userAreaService.deleteUserArea(1L);
        assertEquals(ServiceMessage.AREA_NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for get user area by id")
    void givenId_WhenGetUserAreaById_ThenNull(){
        assertNull(userAreaService.getUserAreaById(123));
    }

    @Test
    @DisplayName("Test for verify if exist user area by id")
    void givenId_WhenUserAreaExistById_ThenFalse(){
        assertFalse(userAreaService.userAreaExistById(123));
    }


}

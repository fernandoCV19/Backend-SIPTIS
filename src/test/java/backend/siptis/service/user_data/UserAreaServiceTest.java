package backend.siptis.service.user_data;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.general_information.user_area.CreateAreaDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserAreaServiceTest {

    @Autowired
    private UserAreaService userAreaService;
    private CreateAreaDTO createAreaDTO;

    private void createDTO() {
        createAreaDTO = new CreateAreaDTO();
        createAreaDTO.setName("AREA");
    }

    @Test
    @DisplayName("Test for get all user areas list")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenNoAreasWhenGetAllUserAreasThenServiceMessageOK() {
        ServiceAnswer answer = userAreaService.getAllUserAreas();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for create user area")
    void givenCreateAreaDTOWhenCreateUserAreaThenServiceMessageAREA_CREATED() {
        createDTO();
        ServiceAnswer answer = userAreaService.createUserArea(createAreaDTO);
        assertEquals(ServiceMessage.AREA_CREATED, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for create user area with already existing name")
    void givenCreateAreaDTOWhenCreateUserAreaThenServiceMessageAREA_ALREADY_EXIST() {
        createDTO();
        userAreaService.createUserArea(createAreaDTO);
        ServiceAnswer answer = userAreaService.createUserArea(createAreaDTO);
        assertEquals(ServiceMessage.AREA_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for delete user area that doesnt exist")
    void givenNoArea_WhenDeleteUserArea_ThenServiceMessageAREA_NOT_FOUND() {
        ServiceAnswer answer = userAreaService.deleteUserArea(1L);
        assertEquals(ServiceMessage.AREA_NOT_FOUND, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for success delete user area")
    void givenNoArea_WhenDeleteUserArea_ThenServiceMessageAREA_DELETED() {
        createDTO();
        userAreaService.createUserArea(createAreaDTO);
        ServiceAnswer answer = userAreaService.deleteUserArea(1L);
        assertEquals(ServiceMessage.AREA_DELETED, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for get non existing user area by id")
    void givenId_WhenGetUserAreaById_ThenNull() {
        assertNull(userAreaService.getUserAreaById(123));
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Test for get user area by id")
    void givenId_WhenGetUserAreaById_ThenNotNull() {
        createDTO();
        userAreaService.createUserArea(createAreaDTO);
        assertNotNull(userAreaService.getUserAreaById(1));
    }

    @Test
    @DisplayName("Test for verify if exist non existing user area by id")
    void givenId_WhenUserAreaExistById_ThenFalse() {
        assertFalse(userAreaService.userAreaExistById(123));
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Test for verify if exist user area by id")
    void givenId_WhenUserAreaExistById_ThenTrue() {
        createDTO();
        userAreaService.createUserArea(createAreaDTO);
        assertTrue(userAreaService.userAreaExistById(1));
    }


}

package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserAreaServiceTest {

    private static final CreateAreaDTO createAreaDTO = new CreateAreaDTO();
    private final UserAreaService userAreaService;

    @Autowired
    public UserAreaServiceTest(UserAreaService userAreaService) {
        this.userAreaService = userAreaService;
    }

    private ServiceAnswer registerArea() {
        createAreaDTO.setName("New Area Example");
        return userAreaService.createUserArea(createAreaDTO);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getAllAreasListSuccessTest() {
        ServiceAnswer answer = userAreaService.getAllUserAreas();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createAreaSuccessTest() {
        ServiceAnswer answer = registerArea();
        assertEquals(ServiceMessage.AREA_CREATED, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createAreaFailAlreadyExistingAreaNameTest() {
        registerArea();
        ServiceAnswer answer = registerArea();
        assertEquals(ServiceMessage.AREA_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createAreaFailInvalidAreaNameTest() {
        createAreaDTO.setName("N");
        ServiceAnswer answer = userAreaService.createUserArea(createAreaDTO);
        assertEquals(ServiceMessage.INVALID_AREA_NAME, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteAreaSuccessTest() {
        registerArea();
        ServiceAnswer answer = userAreaService.deleteUserArea(1L);
        assertEquals(ServiceMessage.AREA_DELETED, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteAreaFailNotFoundAreaTest() {
        ServiceAnswer answer = userAreaService.deleteUserArea(1L);
        assertEquals(ServiceMessage.AREA_NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getAreaByIdSuccessTest() {
        registerArea();
        UserArea userArea = userAreaService.getUserAreaById(1);
        assertNotNull(userArea);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getAreaByIdFailNotFoundTest() {
        UserArea userArea = userAreaService.getUserAreaById(1);
        assertNull(userArea);
    }

}

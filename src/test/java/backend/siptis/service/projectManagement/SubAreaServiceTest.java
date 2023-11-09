package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class SubAreaServiceTest {
    private static final CreateAreaDTO createAreaDTO = new CreateAreaDTO();
    private final SubAreaService subAreaService;

    @Autowired
    public SubAreaServiceTest(SubAreaService subAreaService) {
        this.subAreaService = subAreaService;
    }

    private ServiceAnswer registerSubArea() {
        createAreaDTO.setName("New Sub Area Example");
        return subAreaService.createSubArea(createAreaDTO);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getAllSubAreasListSuccessTest() {
        ServiceAnswer answer = subAreaService.getAllSubAreas();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createSubAreaSuccessTest() {
        ServiceAnswer answer = registerSubArea();
        assertEquals(ServiceMessage.SUB_AREA_CREATED, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createSubAreaFailInvalidSubAreaNameTest() {
        createAreaDTO.setName("N");
        ServiceAnswer answer = subAreaService.createSubArea(createAreaDTO);
        assertEquals(ServiceMessage.INVALID_SUB_AREA_NAME, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createSubAreaFailAlreadyExistingSubAreaTest() {
        registerSubArea();
        ServiceAnswer answer = registerSubArea();
        assertEquals(ServiceMessage.SUB_AREA_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteSubAreaSuccessTest() {
        registerSubArea();
        ServiceAnswer answer = subAreaService.deleteSubArea(1L);
        assertEquals(ServiceMessage.SUB_AREA_DELETED, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteSubAreaFailNotFoundAreaTest() {
        ServiceAnswer answer = subAreaService.deleteSubArea(1L);
        assertEquals(ServiceMessage.SUB_AREA_NOT_FOUND, answer.getServiceMessage());
    }
}

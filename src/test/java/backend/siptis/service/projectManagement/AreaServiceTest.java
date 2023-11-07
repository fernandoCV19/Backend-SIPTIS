package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Area;
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
public class AreaServiceTest {

    private final AreaService areaService;
    private static CreateAreaDTO createAreaDTO = new CreateAreaDTO();

    @Autowired
    public AreaServiceTest(AreaService areaService){
        this.areaService = areaService;
    }

    private ServiceAnswer registerArea(){
        createAreaDTO.setName("New Area Example");
        return areaService.createArea(createAreaDTO);
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getAllAreasListSuccessTest(){
        ServiceAnswer answer = areaService.getAllAreas();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createAreaSuccessTest(){
        ServiceAnswer answer = registerArea();
        assertEquals(ServiceMessage.AREA_CREATED, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createAreaFailAreaNameAlreadyExistTest(){
        registerArea();
        ServiceAnswer answer = registerArea();
        assertEquals(ServiceMessage.AREA_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void createAreaFailInvalidAreaNameTest(){
        createAreaDTO.setName("N");
        ServiceAnswer answer = areaService.createArea(createAreaDTO);
        assertEquals(ServiceMessage.INVALID_AREA_NAME, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteAreaSuccessTest(){
        registerArea();
        ServiceAnswer answer = areaService.deleteArea(1l);
        assertEquals(ServiceMessage.AREA_DELETED, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteAreaFailNotFoundAreaTest(){
        ServiceAnswer answer = areaService.deleteArea(1l);
        assertEquals(ServiceMessage.AREA_NOT_FOUND, answer.getServiceMessage());
    }



}

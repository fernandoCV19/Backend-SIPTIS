package backend.siptis.service.project_management;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.general_information.user_area.CreateAreaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AreaServiceImplTest {

    private final AreaService areaService;
    private final CreateAreaDTO CREATE_AREA_DTO = new CreateAreaDTO();

    @Autowired
    public AreaServiceImplTest(AreaService areaService) {
        this.areaService = areaService;
    }

    @BeforeEach
    void setUp() {
        CREATE_AREA_DTO.setName("AreaTest");
    }

    @Test
    void whenGetAllAreasThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, areaService.getAllAreas().getServiceMessage());
    }

    @Test
    @Rollback
    void givenValidDTOWhenCreateAreaThenServiceMessageOk() {
        assertEquals(ServiceMessage.AREA_CREATED, areaService.createArea(CREATE_AREA_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenInvalidDTOWhenCreateAreaThenServiceMessageInvalidName() {
        CREATE_AREA_DTO.setName(null);
        assertEquals(ServiceMessage.INVALID_AREA_NAME, areaService.createArea(CREATE_AREA_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenInvalidIdWhenDeleteAreaThenServiceMessageAreaNotFound() {
        assertEquals(ServiceMessage.AREA_NOT_FOUND, areaService.deleteArea(999L).getServiceMessage());
    }
}
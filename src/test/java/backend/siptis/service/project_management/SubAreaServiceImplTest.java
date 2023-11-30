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
class SubAreaServiceImplTest {

    private final SubAreaService subAreaService;
    private final CreateAreaDTO CREATE_AREA_DTO = new CreateAreaDTO();


    @Autowired
    public SubAreaServiceImplTest(SubAreaService subAreaService) {
        this.subAreaService = subAreaService;
    }

    @BeforeEach
    void setUp() {
        CREATE_AREA_DTO.setName("SubAreaTest");
    }

    @Test
    void whenGetAllSubAreasThenServiceOk() {
        assertEquals(ServiceMessage.OK, subAreaService.getAllSubAreas().getServiceMessage());
    }

    @Test
    @Rollback
    void givenValidDTOWhenCreateSubAreaThenServiceMessageCreated() {
        assertEquals(ServiceMessage.SUB_AREA_CREATED, subAreaService.createSubArea(CREATE_AREA_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenInvalidDTOWhenCreateSubAreaThenServiceMessageInvalidName() {
        CREATE_AREA_DTO.setName(null);
        assertEquals(ServiceMessage.INVALID_SUB_AREA_NAME, subAreaService.createSubArea(CREATE_AREA_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenInvalidIdWhendeleteSubAreaThenServiceMessageSubAreaNotFound() {
        assertEquals(ServiceMessage.SUB_AREA_NOT_FOUND, subAreaService.deleteSubArea(1L).getServiceMessage());
    }
}
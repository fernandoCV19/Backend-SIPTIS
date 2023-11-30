package backend.siptis.service.project_management;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ModalityServiceImplTest {


    private final ModalityService modalityService;

    @Autowired
    public ModalityServiceImplTest(ModalityService modalityService) {
        this.modalityService = modalityService;
    }

    @Test
    void whenGetAllModalitiesThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, modalityService.getAllModalities().getServiceMessage());
    }
}
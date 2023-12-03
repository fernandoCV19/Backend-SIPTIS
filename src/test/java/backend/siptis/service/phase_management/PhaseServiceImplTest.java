package backend.siptis.service.phase_management;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PhaseServiceImplTest {

    private final PhaseService phaseService;

    @Autowired
    public PhaseServiceImplTest(PhaseService phaseService) {
        this.phaseService = phaseService;
    }


    @Test
    void whenFindAllPhasesThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, phaseService.findAllPhases().getServiceMessage());
    }

    @Test
    void givenValidPhaseIdWhenFindPhaseByIdThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, phaseService.findPhaseByUserId(100L).getServiceMessage());
    }

    @Test
    void givenInvalidPhaseIdWhenFindPhaseByIdThenServiceMessageNotFound() {
        assertEquals(ServiceMessage.NOT_FOUND, phaseService.findPhaseByUserId(999L).getServiceMessage());
    }

    @Test
    void givenValidUserIdWhenGetPhasesByUserIdThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, phaseService.getPhasesByUserId(100L).getServiceMessage());
    }

    @Test
    void givenInvalidUserIdWhenGetPhasesByUserIdThenServiceMessageNotFound() {
        assertEquals(ServiceMessage.NOT_FOUND, phaseService.getPhasesByUserId(999L).getServiceMessage());
    }

    @Test
    void givenValidModalityIdWhenFindPhaseByModalityIdThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, phaseService.findPhaseByModalityId(100L).getServiceMessage());
    }
}
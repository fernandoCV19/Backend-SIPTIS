package backend.siptis.service.project_management.project;

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
@DirtiesContext
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectServiceOtherOperationsTest {

    private final ProjectServiceOtherOperations projectServiceOtherOperations;

    @Autowired
    public ProjectServiceOtherOperationsTest(ProjectServiceOtherOperations projectServiceOtherOperations) {
        this.projectServiceOtherOperations = projectServiceOtherOperations;
    }

    @Test
    void givenValidProjetIdWhenGetPresentationsThenServiceMessageNotFound() {
        ServiceMessage sm = projectServiceOtherOperations.getPresentations(102L).getServiceMessage();
        assertEquals(ServiceMessage.NO_PRESENTATIONS, sm);
    }

    @Test
    void givenInvalidProjectIdWhenGetPresentationsThenServiceMessageNotFound() {
        ServiceMessage sm = projectServiceOtherOperations.getPresentations(1L).getServiceMessage();
        assertEquals(ServiceMessage.NOT_FOUND, sm);
    }

    @Test
    void givenValidProjectIdWhenGetInvolvedPeopleThenServiceMessageOk() {
        ServiceMessage sm = projectServiceOtherOperations.getInvolvedPeople(102L).getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void givenInvalidProjectIdWhenGetInvolvedPeopleThenServiceMessageProjectIdDoesNotExist() {
        ServiceMessage sm = projectServiceOtherOperations.getInvolvedPeople(1L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, sm);
    }

    @Test
    void givenValidProjectIdWhenGetSchedulesInfoToAssignADefenseThenServiceOk() {
        ServiceMessage sm = projectServiceOtherOperations.getSchedulesInfoToAssignADefense(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void givenInvalidProjectIdWhenGetSchedulesInfoToAssignADefenseThenServiceMessageProjectIdDoesNotExist() {
        ServiceMessage sm = projectServiceOtherOperations.getSchedulesInfoToAssignADefense(1L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, sm);
    }
}
package backend.siptis.service.editors_and_reviewers.project_tribunal_services;

import backend.siptis.commons.ServiceAnswer;
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
class ProjectTribunalServiceGetProjectsTest {

    private final ProjectTribunalServiceGetProjects projectTribunalServiceGetProjects;

    @Autowired
    public ProjectTribunalServiceGetProjectsTest(ProjectTribunalServiceGetProjects projectTribunalServiceGetProjects) {
        this.projectTribunalServiceGetProjects = projectTribunalServiceGetProjects;
    }

    @Test
    void givenValidTribunalIdWhenGetAllProjectsNotAcceptedReviewedByTribunalIdThenServiceMessageOK() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedReviewedByTribunalId(155L);
        assertEquals(ServiceMessage.OK, sa.getServiceMessage());
    }

    @Test
    void givenInvalidTribunalIdWhenGetAllProjectsNotAcceptedReviewedByTribunalIdThenServiceMessageIDDoesNotExist() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedReviewedByTribunalId(1L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, sa.getServiceMessage());
    }

    @Test
    void givenValidTribunalIdWhenGetAllProjectsNotAcceptedNotReviewedByTribunalIdThenServiceMessageOK() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedNotReviewedByTribunalId(154L);
        assertEquals(ServiceMessage.OK, sa.getServiceMessage());
    }

    @Test
    void givenInvalidTribunalIdWhenGetAllProjectsNotAcceptedNotReviewedByTribunalIdThenServiceMessageIDDoesNotExist() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedNotReviewedByTribunalId(1L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, sa.getServiceMessage());
    }

    @Test
    void givenValidTribunalIdWhenGetAllProjectsNotAcceptedNotReviewedByTribunalIdThenServiceMessageOk() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedNotReviewedByTribunalId(154L);
        assertEquals(ServiceMessage.OK, sa.getServiceMessage());
    }

    @Test
    void givenInvalidTribunalIdWhenGetAllProjectsNotAcceptedNotReviewedByTribunalIdThenThenServiceMessageIdDoesNotExist() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedNotReviewedByTribunalId(1L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, sa.getServiceMessage());
    }


    @Test
    void givenValidTribunalIdWhenGetAllProjectsAcceptedWithoutDefensePointsByTribunalId() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(153L);
        assertEquals(ServiceMessage.OK, sa.getServiceMessage());
    }


    @Test
    void givenValidTribunalIdWhenGetAllProjectsAcceptedWithoutDefensePointsByTribunalIdThenServiceMessageOk() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(153L);
        assertEquals(ServiceMessage.OK, sa.getServiceMessage());
    }

    @Test
    void givenValidTribunalIdWHenGetAllProjectsAcceptedWithoutDefensePointsByTribunalIdThenServiceMessageIdDoesNotExist() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(1L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, sa.getServiceMessage());
    }

    @Test
    void givenValidTribunalIdWhenGetAllProjectsDefendedByTribunalIdThenServiceMessageWithoutProjects() {
        ServiceAnswer sa = projectTribunalServiceGetProjects.getAllProjectsDefendedByTribunalId(152L);
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, sa.getServiceMessage());
    }
}
package backend.siptis.service.editors_and_reviewers;

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
class ProjectSupervisorServiceImplTest {

    private final ProjectSupervisorService projectSupervisorService;

    @Autowired
    public ProjectSupervisorServiceImplTest(ProjectSupervisorService projectSupervisorService) {
        this.projectSupervisorService = projectSupervisorService;
    }

    @Test
    void givenValidIdWhengetAllProjectsNotAcceptedReviewedBySupervisorIdThenServiceMessageOk() {
        ServiceMessage message = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(141L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalididWhengetAllProjectsNotAcceptedReviewedBySupervisorIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenValidIdWhengetAllProjectsNotAcceptedNotReviewedBySupervisorIdThenServiceMessageOk() {
        ServiceMessage message = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(145L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalididWhengetAllProjectsNotAcceptedNotReviewedBySupervisorIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenValidIdWhengetAllProjectsAcceptedBySupervisorIdThenServiceMessageOk() {
        ServiceMessage message = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(140L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalididWhengetAllProjectsAcceptedBySupervisorIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenValidSupervisorAndProjectIdWhenAcceptProjectThenServiceMessageOk() {
        ServiceMessage message = projectSupervisorService.acceptProject(141L, 103L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalidSupervisorIdWhenAcceptProjectTheeServiceMessageUserIdDoesNotExist() {
        ServiceMessage message = projectSupervisorService.acceptProject(1L, 103L).getServiceMessage();
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenInvalidProjectIdWhenAcceptProjectTheeServiceMessageProjectIdDoesNotExist() {
        ServiceMessage message = projectSupervisorService.acceptProject(141L, 1L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenValidSupervisorAndProjectIdWhenAcceptProjectThenServiceMessageIdReviewerDoesNotMatchWithProject() {
        ServiceMessage message = projectSupervisorService.acceptProject(140L, 103L).getServiceMessage();
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, message);
    }

    @Test
    void givenAnAlreadyAcceptedProjectWhenAcceptProjectThenServiceMessageProjectHasAlreadyBeenAccepted() {
        ServiceMessage message = projectSupervisorService.acceptProject(140L, 102L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED, message);
    }
}
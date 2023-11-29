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
class ProjectTutorServiceImplTest {

    private final ProjectTutorService projectTutorService;

    @Autowired
    public ProjectTutorServiceImplTest(ProjectTutorService projectTutorService) {
        this.projectTutorService = projectTutorService;
    }

    @Test
    void givenValidTutorIdWhenGetAllProjectsNotAcceptedReviewedByTutorIdThenServiceMessageOk() {
        ServiceMessage message = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(131L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalidTutorIdWhenGetAllProjectsNotAcceptedReviewedByTutorIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenValidTutorIdWhenGetAllProjectsNotAcceptedNotReviewedByTutorIdThenServiceMessageOk() {
        ServiceMessage message = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(134L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalidTutorIdWhenGetAllProjectsNotAcceptedNotReviewedByTutorIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenValidTutorIdWhenGetAllProjectsAcceptedByTutorIdThenServiceMessageOk() {
        ServiceMessage message = projectTutorService.getAllProjectsAcceptedByTutorId(131L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalidTutorIdWhenGetAllProjectsAcceptedByTutorIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectTutorService.getAllProjectsAcceptedByTutorId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenInvalidTeacherIdWhenAcceptProjectThenServiceMessageIdReviewerDoesNotMatchWithProject() {
        ServiceMessage message = projectTutorService.acceptProject(1L, 1L).getServiceMessage();
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenInvalidProjectIdWhenAcceptProjectThenServiceMessageProjectIdDoesNotExist() {
        ServiceMessage message = projectTutorService.acceptProject(131L, 1L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenNotMatchTeacherIdWhenAcceptProjectThenServiceMessageIdReviewerDoesNotMatchWithProject() {
        ServiceMessage message = projectTutorService.acceptProject(132L, 101L).getServiceMessage();
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, message);
    }

    @Test
    void givenAcceptedProjectIdWhenAcceptProjectThenServiceAnswerProjectHasAlreadyBeenAccepted() {
        ServiceMessage message = projectTutorService.acceptProject(131L, 101L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED, message);
    }
}
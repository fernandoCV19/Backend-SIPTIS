package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectTeacherServiceImplTest {

    private final ProjectTeacherService projectTeacherService;

    @Autowired
    public ProjectTeacherServiceImplTest (ProjectTeacherService projectTeacherService) {
        this.projectTeacherService = projectTeacherService;
    }


    @Test
    void givenValidTeacherIdWhenGetAllProjectsNotAcceptedReviewedByTeacherIdThenServiceMessageOk() {
        ServiceMessage message = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(120L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalidTeacherIdWhenGetAllProjectsNotAcceptedReviewedByTeacherIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }
    @Test
    void givenValidTeacherIdWhenGetAllProjectsNotAcceptedNotReviewedByTeacherIdThenServiceMessageOk() {
        ServiceMessage message = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(120L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalidTeacherIdWhenGetAllProjectsNotAcceptedNotReviewedByTeacherIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenValidTeacherIdWhenGetAllProjectsAcceptedByTeacherIdThenServiceMessageOk() {
        ServiceMessage message = projectTeacherService.getAllProjectsAcceptedByTeacherId(120L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalidTeacherIdWhenGetAllProjectsAcceptedByTeacherIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage message = projectTeacherService.getAllProjectsAcceptedByTeacherId(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenValidTeacherIdAndProjectIdWhenAcceptProjectThenServiceMessageOk() {
        ServiceMessage message = projectTeacherService.acceptProject(120L, 100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void givenInvalidTeacherIdWhenAcceptProjectThenServiceMessageIdReviewerDoesNotMatchWithProject() {
        ServiceMessage message = projectTeacherService.acceptProject(1L, 1L).getServiceMessage();
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenInvalidProjectIdWhenAcceptProjectThenServiceMessageProjectIdDoesNotExist() {
        ServiceMessage message = projectTeacherService.acceptProject(120L, 1L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, message);
    }

    @Test
    void givenNotMatchTeacherIdWhenAcceptProjectThenServiceMessageIdReviewerDoesNotMatchWithProject() {
        ServiceMessage message = projectTeacherService.acceptProject(121L, 100L).getServiceMessage();
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, message);
    }

    @Test
    void givenAcceptedProjectIdWhenAcceptProjectThenServiceAnswerProjectHasAlreadyBeenAccepted() {
        ServiceMessage message = projectTeacherService.acceptProject(120L, 101L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED, message);
    }
}
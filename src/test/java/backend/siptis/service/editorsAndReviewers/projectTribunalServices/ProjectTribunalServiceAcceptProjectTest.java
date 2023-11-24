package backend.siptis.service.editorsAndReviewers.projectTribunalServices;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editors_and_reviewers.project_tribunal_services.ProjectTribunalServiceAcceptProject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ProjectTribunalServiceAcceptProjectTest {

    public ProjectTribunalServiceAcceptProject projectTribunalServiceAcceptProject;
    @Autowired
    public ProjectTribunalServiceAcceptProjectTest(ProjectTribunalServiceAcceptProject projectTribunalServiceAcceptProject) {
        this.projectTribunalServiceAcceptProject = projectTribunalServiceAcceptProject;
    }

    @Test
    @Rollback
    void givenValidTribunalAndProjectIdWhenAcceptProjectThenServiceAnswerOk() {
        ServiceAnswer sa = projectTribunalServiceAcceptProject.acceptProject(150L,103L);
        assertEquals(ServiceMessage.OK, sa.getServiceMessage());
    }

    @Test
    @Rollback
    void givenValidTribunalAndProjectIdWhenAcceptProjectThenProjectChangePhase(){
        ServiceAnswer sa = projectTribunalServiceAcceptProject.acceptProject(154L,105L);
        assertEquals("THE PROJECT HAS CHANGED TO THE PHASE OF DEFENSE", sa.getData());
    }

    @Test
    @Rollback
    void givenValidTribunalAndProjectIdWhenAcceptProjectThenProjectDoesNotChangePhase() {
        ServiceAnswer sa = projectTribunalServiceAcceptProject.acceptProject(150L,103L);
        assertEquals("THE PROJECT HAS NOT CHANGED TO THE PHASE OF DEFENSE", sa.getData());
    }

    @Test
    @Rollback
    void givenInvalidTribunalIdWhenAcceptProjectThenServiceAnswerUserIdDoesNotExist() {
        ServiceAnswer sa = projectTribunalServiceAcceptProject.acceptProject(1L,100L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, sa.getServiceMessage());
    }

    @Test
    @Rollback
    void givenInvalidProjectIdWhenAcceptProjectThenServiceAnswerProjectIdDoesNotExist() {
        ServiceAnswer sa = projectTribunalServiceAcceptProject.acceptProject(150L,10L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, sa.getServiceMessage());
    }

    @Test
    @Rollback
    void givenNotMatchIdsWhenAcceptProjectThenServiceAnswerIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer sa = projectTribunalServiceAcceptProject.acceptProject(150L,104L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, sa.getServiceMessage());
    }

    @Test
    @Rollback
    void givenAlreadyAcceptedProjectWhenAcceptProjectThenServiceAnswerProjectHasAlreadyBeenAccepted() {
        ServiceAnswer sa = projectTribunalServiceAcceptProject.acceptProject(151L,103L);
        assertEquals(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED, sa.getServiceMessage());
    }
}
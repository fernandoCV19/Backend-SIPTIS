package backend.siptis.service.project_management.project;

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
class ProjectServiceGetProjectInfoTest {

    private final ProjectServiceGetProjectInfo projectServiceGetProjectInfo;

    @Autowired
    public ProjectServiceGetProjectInfoTest(ProjectServiceGetProjectInfo projectServiceGetProjectInfo) {
        this.projectServiceGetProjectInfo = projectServiceGetProjectInfo;
    }


    @Test
    void givenValidIdProjectandIdReviwerWhenGetProjectInfoToReviewThenServiceMessageNoPresentation() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfoToReview(102L, 140L).getServiceMessage();
        assertEquals(ServiceMessage.THERE_IS_NO_PRESENTATION_YET, sm);
    }

    @Test
    void givenInvalidProjectIdWhenGetProjectInfoToReviewThenServiceMessageERROR() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfoToReview(100L, 101L).getServiceMessage();
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, sm);
    }

    @Test
    void givenInvalidReviwerIdWhenGetProjectInfoToReviewThenServiceMessageUserDoesNotExist() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfoToReview(101L, 1L).getServiceMessage();
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, sm);
    }

    @Test
    void givenNoMatchProjectAndReviwerIdWHenGetProjectInfoToReviewThenServiceMessageNotMatch() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfoToReview(1L, 1L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, sm);
    }

    @Test
    void givenValidIdProjectWhenGetProjectInfoToAssignTribunalsThenServiceMessageOk() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfoToAssignTribunals(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void givenInvalidProjectIdWhenGetProjectInfoToAssignTribunalsThenServiceMessageProjectDoesNotExist() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfoToAssignTribunals(1L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, sm);
    }

    @Test
    void givenValidIdProjectWhenGetProjectInfoThenServiceMessageOk() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfoToAssignTribunals(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void givenInvalidProjectIdWhenGetProjectInfoThenServiceMessageIdDoesNotExist() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfoToAssignTribunals(1L).getServiceMessage();
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, sm);
    }

    @Test
    void givenValidProjectIdWhenGetAllProjectInfoThenServiceMessageOk() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfo(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void givenInvalidProjectIdWhenGetAllProjectInfoThenServiceMessageIdDoesNotExist() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectInfo(1L).getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, sm);
    }

    @Test
    void givenValidProjectIdWhenGetProjectByIdThenServiceMessageOk() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectById(101L).getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void givenInvalidProjectIdWhenGetProjectByIdThenServiceMessageIdDoesNotExist() {
        ServiceMessage sm = projectServiceGetProjectInfo.getProjectById(1L).getServiceMessage();
        assertEquals(ServiceMessage.NOT_FOUND, sm);
    }
}
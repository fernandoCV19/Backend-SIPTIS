package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.vo.projectManagement.ProjectCompleteInfo;
import backend.siptis.model.pjo.vo.projectManagement.ProjectInfoToAssignTribunals;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToReviewSectionVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectServiceTest {

    private final ProjectService projectService;

    @Autowired
    public ProjectServiceTest(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Test
    void getProjectInfoToReviewWithProjectIdThatDoesNotExistReturnProjectIdDoesNoExist() {
        ServiceAnswer query = projectService.getProjectInfoToReview(0L, 30L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void getProjectInfoToReviewWithProjectIdThatDoesNotExistReturnNullData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(0L, 30L);
        assertNull(query.getData());
    }

    @Test
    void getProjectInfoToReviewWithUserIdThatDoesNotExistReturnUserIdDoesNotExist() {
        ServiceAnswer query = projectService.getProjectInfoToReview(30L, 0L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void getProjectInfoToReviewWithUserIdThatDoesNotExistReturnNullData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(30L, 0L);
        assertNull(query.getData());
    }

    @Test
    void getProjectInfoToReviewWithIdReviewerThatDoesNotMatchWithProjectReturnIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer query = projectService.getProjectInfoToReview(30L, 10L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, query.getServiceMessage());
    }
    @Test
    void getProjectInfoToReviewWithIdReviewerThatDoesNotMatchWithProjectReturnNullData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(30L, 10L);
        assertNull(query.getData());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatDoesNotHavePresentationsReturnThereIsNoPresentation() {
        ServiceAnswer query = projectService.getProjectInfoToReview(30L, 30L);
        assertEquals(ServiceMessage.THERE_IS_NO_PRESENTATION_YET, query.getServiceMessage());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatDoesNotHavePresentationsReturnAnObjectWithData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(30L, 30L);
        assertNotNull(query.getData());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatDoesNotHavePresentationsReturnAnObjectWithCorrectData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(30L, 30L);
        ProjectToReviewSectionVO projectToReviewSectionVO = (ProjectToReviewSectionVO)query.getData();
        assertFalse(projectToReviewSectionVO.getStudentChanges());
        assertFalse(projectToReviewSectionVO.getReviewed());
        assertEquals(-1, projectToReviewSectionVO.getNumberOfDays());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatNotHaveBeenReviewedReturnOk() {
        ServiceAnswer query = projectService.getProjectInfoToReview(31L, 30L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatNotHaveBeenReviewedReturnObjectWithData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(31L, 30L);
        assertNotNull(query);
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatNotHaveBeenReviewedReturnObjectWithCorrectData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(31L, 30L);
        ProjectToReviewSectionVO projectToReviewSectionVO = (ProjectToReviewSectionVO)query.getData();
        assertTrue(projectToReviewSectionVO.getStudentChanges());
        assertFalse(projectToReviewSectionVO.getReviewed());
        assertNotEquals(-1, projectToReviewSectionVO.getNumberOfDays());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatHaveBeenReviewedByTheReviewerReturnOk() {
        ServiceAnswer query = projectService.getProjectInfoToReview(32L, 30L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatHaveBeenReviewedByTheReviewerReturnObjectWithData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(32L, 30L);
        assertNotNull(query.getData());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatHaveBeenReviewedByTheReviewerReturnObjectWithCorrectData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(32L, 30L);
        ProjectToReviewSectionVO projectToReviewSectionVO = (ProjectToReviewSectionVO)query.getData();
        assertFalse(projectToReviewSectionVO.getStudentChanges());
        assertTrue(projectToReviewSectionVO.getReviewed());
        assertNotEquals(-1, projectToReviewSectionVO.getNumberOfDays());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatHaveBeenReviewedByAnotherReviewerReturnOk() {
        ServiceAnswer query = projectService.getProjectInfoToReview(34L, 30L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatHaveBeenReviewedByAnotherReviewerReturnObjectWithData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(34L, 30L);
        assertNotNull(query.getData());
    }

    @Test
    void getProjectInfoToReviewWithAProjectThatHaveBeenReviewedByAnotherReviewerReturnObjectWithCorrectData() {
        ServiceAnswer query = projectService.getProjectInfoToReview(34L, 30L);
        ProjectToReviewSectionVO projectToReviewSectionVO = (ProjectToReviewSectionVO)query.getData();
        assertFalse(projectToReviewSectionVO.getStudentChanges());
        assertFalse(projectToReviewSectionVO.getReviewed());
        assertNotEquals(-1, projectToReviewSectionVO.getNumberOfDays());
    }

    @Test
    void getAllProjectInfoWithIncorrectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectService.getAllProjectInfo(0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void getAllProjectInfoWithIncorrectIdReturnNullData() {
        ServiceAnswer query = projectService.getAllProjectInfo(0L);
        assertNull(query.getData());
    }

    @Test
    void getAllProjectInfoWithIncorrectIdReturnOk() {
        ServiceAnswer query = projectService.getAllProjectInfo(1L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getAllProjectInfoWithIncorrectIdReturnObjectWithData() {
        ServiceAnswer query = projectService.getAllProjectInfo(1L);
        assertNotNull(query.getData());
    }

    @Test
    void getAllProjectInfoWithIncorrectIdReturnTheProjectThatOwnsTheId() {
        ServiceAnswer query = projectService.getAllProjectInfo(1L);
        ProjectCompleteInfo project = (ProjectCompleteInfo) query.getData();
        assertEquals(1L, project.getId());
    }

    @Test
    void getProjectInfoToAssignTribunalsWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectService.getProjectInfoToAssignTribunals(0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }
    @Test
    void getProjectInfoToAssignTribunalsWithIncorrectProjectIdReturnDataNull() {
        ServiceAnswer query = projectService.getProjectInfoToAssignTribunals(0L);
        assertNull(query.getData());
    }
    @Test
    void getProjectInfoToAssignTribunalsWithCorrectProjectIdReturnOk() {
        ServiceAnswer query = projectService.getProjectInfoToAssignTribunals(1L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }
    @Test
    void getProjectInfoToAssignTribunalsWithCorrectProjectIdReturnDataWithValues() {
        ServiceAnswer query = projectService.getProjectInfoToAssignTribunals(1L);
        assertNotNull(query.getData());
    }
    @Test
    void getProjectInfoToAssignTribunalsWithCorrectProjectIdReturnAnObjectWithTheCorrectIdOfTheProject() {
        ServiceAnswer query = projectService.getProjectInfoToAssignTribunals(1L);
        ProjectInfoToAssignTribunals res = (ProjectInfoToAssignTribunals)query.getData();
        assertEquals(1L, res.getId());
    }
}
package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.projectManagement.AssignTribunalsDTO;
import backend.siptis.model.pjo.vo.projectManagement.InfoToCreateADefenseVO;
import backend.siptis.model.pjo.vo.projectManagement.ProjectCompleteInfoVO;
import backend.siptis.model.pjo.vo.projectManagement.ProjectInfoToAssignTribunalsVO;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToReviewSectionVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProjectServiceTest {

    private final ProjectService projectService;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectServiceTest(ProjectService projectService, JdbcTemplate jdbcTemplate) {
        this.projectService = projectService;
        this.jdbcTemplate = jdbcTemplate;
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
        ProjectToReviewSectionVO projectToReviewSectionVO = (ProjectToReviewSectionVO) query.getData();
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
        ProjectToReviewSectionVO projectToReviewSectionVO = (ProjectToReviewSectionVO) query.getData();
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
        ProjectToReviewSectionVO projectToReviewSectionVO = (ProjectToReviewSectionVO) query.getData();
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
        ProjectToReviewSectionVO projectToReviewSectionVO = (ProjectToReviewSectionVO) query.getData();
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
        ProjectCompleteInfoVO project = (ProjectCompleteInfoVO) query.getData();
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
        ProjectInfoToAssignTribunalsVO res = (ProjectInfoToAssignTribunalsVO) query.getData();
        assertEquals("ProyectoGrado1", res.getName());
    }

    @Test
    void assignTribunalsWithIncorrectIdTribunalReturnUserIdDoesNotExist() {
        AssignTribunalsDTO assignTribunalsDTO = new AssignTribunalsDTO(new Long[]{0L, 5L, 9L}, 1L);
        ServiceAnswer query = projectService.assignTribunals(assignTribunalsDTO);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void assignTribunalsWithIncorrectIdTribunalReturnTheIncorrectId() {
        AssignTribunalsDTO assignTribunalsDTO = new AssignTribunalsDTO(new Long[]{0L, 5L, 9L}, 1L);
        ServiceAnswer query = projectService.assignTribunals(assignTribunalsDTO);
        Long data = (Long) query.getData();
        assertEquals(0L, data);
    }

    @Test
    void assignTribunalsWithUserIdThatIsNotATribunalReturnUserIsNotATribunal() {
        AssignTribunalsDTO assignTribunalsDTO = new AssignTribunalsDTO(new Long[]{2L, 5L, 9L}, 1L);
        ServiceAnswer query = projectService.assignTribunals(assignTribunalsDTO);
        assertEquals(ServiceMessage.USER_IS_NOT_A_TRIBUNAL, query.getServiceMessage());
    }

    @Test
    void assignTribunalsWithUserIdThatIsNotATribunalReturnTheIncorrectId() {
        AssignTribunalsDTO assignTribunalsDTO = new AssignTribunalsDTO(new Long[]{2L, 5L, 9L}, 1L);
        ServiceAnswer query = projectService.assignTribunals(assignTribunalsDTO);
        Long data = (Long) query.getData();
        assertEquals(2L, data);
    }

    @Test
    void assignTribunalsWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        AssignTribunalsDTO assignTribunalsDTO = new AssignTribunalsDTO(new Long[]{1L, 5L, 9L}, 0L);
        ServiceAnswer query = projectService.assignTribunals(assignTribunalsDTO);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void assignTribunalsWithIncorrectProjectIdReturnNullData() {
        AssignTribunalsDTO assignTribunalsDTO = new AssignTribunalsDTO(new Long[]{1L, 5L, 9L}, 0L);
        ServiceAnswer query = projectService.assignTribunals(assignTribunalsDTO);
        assertNull(query.getData());
    }

    @Test
    @Sql(scripts = {"/cleanDB.sql", "/assignTribunals.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void assignTribunalsWithCorrectDataReturnOk() {
        AssignTribunalsDTO assignTribunalsDTO = new AssignTribunalsDTO(new Long[]{1L, 5L, 9L}, 1L);
        ServiceAnswer query = projectService.assignTribunals(assignTribunalsDTO);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
        jdbcTemplate.execute("DROP ALL OBJECTS");
    }

    @Test
    @Sql(scripts = {"/cleanDB.sql", "/assignTribunals.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void assignTribunalsWithCorrectDataReturnDataWithAMessage() {
        AssignTribunalsDTO assignTribunalsDTO = new AssignTribunalsDTO(new Long[]{1L, 5L, 9L}, 1L);
        ServiceAnswer query = projectService.assignTribunals(assignTribunalsDTO);
        assertEquals("Tribunals has been assigned to the project", (String) query.getData());
        jdbcTemplate.execute("DROP ALL OBJECTS");
    }

    @Test
    void getSchedulesInfoToAssignADefenseWithAnIncorrectIdReturnIdProjectDoesNotExist() {
        ServiceAnswer query = projectService.getSchedulesInfoToAssignADefense(0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void getSchedulesInfoToAssignADefenseWithAnIncorrectIdReturnNullData() {
        ServiceAnswer query = projectService.getSchedulesInfoToAssignADefense(0L);
        assertNull(query.getData());
    }

    @Test
    void getSchedulesInfoToAssignADefenseWithACorrectIdReturnOk() {
        ServiceAnswer query = projectService.getSchedulesInfoToAssignADefense(100L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getSchedulesInfoToAssignADefenseWithACorrectIdReturnNotNullData() {
        ServiceAnswer query = projectService.getSchedulesInfoToAssignADefense(100L);
        assertNotNull(query.getData());
    }

    @Test
    void getSchedulesInfoToAssignADefenseWithACorrectIdReturnTheCorrectNumberOfTribunals() {
        ServiceAnswer query = projectService.getSchedulesInfoToAssignADefense(100L);
        InfoToCreateADefenseVO infoToCreateADefenseVO = (InfoToCreateADefenseVO) query.getData();
        assertEquals(3, infoToCreateADefenseVO.getTribunals().size());
    }

    @Test
    void getSchedulesInfoToAssignADefenseWithACorrectIdReturnTheCorrectNumberOfStudents() {
        ServiceAnswer query = projectService.getSchedulesInfoToAssignADefense(100L);
        InfoToCreateADefenseVO infoToCreateADefenseVO = (InfoToCreateADefenseVO) query.getData();
        assertEquals(2, infoToCreateADefenseVO.getStudents().size());
    }

    @Test
    void getSchedulesInfoToAssignADefenseWithACorrectIdReturnTheCorrectDistributionOfDaysOfAUser() {
        ServiceAnswer query = projectService.getSchedulesInfoToAssignADefense(100L);
        InfoToCreateADefenseVO infoToCreateADefenseVO = (InfoToCreateADefenseVO) query.getData();
        HashMap<String, List<String[]>> res = infoToCreateADefenseVO.getTribunals().get(0).getSchedules();
        assertTrue(res.containsKey("Lunes") && res.containsKey("Martes") && res.containsKey("Miercoles") && res.containsKey("Jueves") && res.containsKey("Viernes") && res.size() == 5);
    }
}
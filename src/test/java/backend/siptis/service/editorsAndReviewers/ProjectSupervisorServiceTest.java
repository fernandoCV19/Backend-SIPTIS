package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectSupervisorRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@Transactional
class ProjectSupervisorServiceTest {

    private final ProjectSupervisorService projectSupervisorService;
    private final ProjectRepository projectRepository;
    private final ProjectSupervisorRepository projectSupervisorRepository;

    @Autowired
    ProjectSupervisorServiceTest(ProjectSupervisorServiceImpl projectSupervisorService, ProjectRepository projectRepository, ProjectSupervisorRepository projectSupervisorRepository) {
        this.projectSupervisorService = projectSupervisorService;
        this.projectRepository = projectRepository;
        this.projectSupervisorRepository = projectSupervisorRepository;
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(1L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(1L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(1L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(data.get(0).getReviewed() && !data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(2L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(1L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(1L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(1L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && !data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(2L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(1L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(1L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(1L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(2L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void acceptProjectWithIncorrectUserIdReturnUserIdDoesNotExist() {
        ServiceAnswer query = projectSupervisorService.acceptProject(0L, 54L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIncorrectUserIdReturnNullData() {
        ServiceAnswer query = projectSupervisorService.acceptProject(0L, 54L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIncorrectProjectIdReturnNullData() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 0L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 1L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnNullData() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 1L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatHasAlreadyBeenAcceptedReturnProjectHasAlreadyBeenAccepted() {
        projectSupervisorService.acceptProject(52L, 54L);
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 54L);
        assertEquals(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatHasAlreadyBeenAcceptedReturnNullData() {
        projectSupervisorService.acceptProject(52L, 54L);
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 54L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnOk() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 54L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnNotNullData() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 54L);
        assertNotNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnDataTheProjectHasNotChangeThePhase() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 54L);
        assertEquals("THE PROJECT HAS NOT CHANGED TO THE PHASE OF TRIBUNALS", query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeThePaseOfTheProject() {
        Optional<Project> project = projectRepository.findById(54L);
        String message1 = project.get().getPhase();
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 54L);
        Optional<Project> project2 = projectRepository.findById(54L);
        String message2 = project.get().getPhase();
        assertEquals(message1, message2);
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnOk() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 55L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnNotNullData() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 55L);
        assertNotNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnDataTheProjectHasChangeThePhase() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 55L);
        assertEquals("THE PROJECT HAS CHANGED TO THE PHASE OF TRIBUNALS", query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeThePaseOfTheProject() {
        Optional<Project> project = projectRepository.findById(55L);
        String message1 = project.get().getPhase();
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 55L);
        Optional<Project> project2 = projectRepository.findById(55L);
        String message2 = project.get().getPhase();
        assertTrue(!message1.equals(message2) && message2.equals("TRIBUNALS_PHASE"));
    }

    @Test
    void acceptProjectWithAProjectThatAcceptedParameterCanChangeWillChangeThatParameter() {
        ServiceAnswer query = projectSupervisorService.acceptProject(52L, 55L);
        ProjectSupervisor res = projectSupervisorRepository.findBySupervisorIdAndProjectId(52L, 55L);
        assertTrue(res.getAccepted());
    }
}
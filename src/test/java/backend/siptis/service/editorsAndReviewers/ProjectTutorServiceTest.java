package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTutorRepository;
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
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectTutorServiceTest {

    private final ProjectTutorService projectTutorService;
    private final ProjectRepository projectRepository;
    private final ProjectTutorRepository projectTutorRepository;

    @Autowired
    ProjectTutorServiceTest(ProjectTutorService projectTutorService, ProjectRepository projectRepository, ProjectTutorRepository projectTutorRepository) {
        this.projectTutorService = projectTutorService;
        this.projectRepository = projectRepository;
        this.projectTutorRepository = projectTutorRepository;
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(2L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(2L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(2L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(data.get(0).getReviewed() && !data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(1L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(2L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(2L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(2L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && !data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(1L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(2L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(2L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(2L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(1L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(0L);
        Object data = ans.getData();
        assertNull(data);
    }


    @Test
    void acceptProjectWithIncorrectUserIdReturnUserIdDoesNotExist() {
        ServiceAnswer query = projectTutorService.acceptProject(0L, 52L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIncorrectUserIdReturnNullData() {
        ServiceAnswer query = projectTutorService.acceptProject(0L, 52L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIncorrectProjectIdReturnNullData() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 0L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 1L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnNullData() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 1L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatHasAlreadyBeenAcceptedReturnProjectHasAlreadyBeenAccepted() {
        projectTutorService.acceptProject(51L, 52L);
        ServiceAnswer query = projectTutorService.acceptProject(51L, 52L);
        assertEquals(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatHasAlreadyBeenAcceptedReturnNullData() {
        projectTutorService.acceptProject(51L, 52L);
        ServiceAnswer query = projectTutorService.acceptProject(51L, 52L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnOk() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 52L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnNotNullData() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 52L);
        assertNotNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnDataTheProjectHasNotChangeThePhase() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 52L);
        assertEquals("THE PROJECT HAS NOT CHANGED TO THE PHASE OF TRIBUNALS", query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeThePaseOfTheProject() {
        Optional<Project> project = projectRepository.findById(52L);
        String message1 = project.get().getPhase();
        ServiceAnswer query = projectTutorService.acceptProject(51L, 52L);
        Optional<Project> project2 = projectRepository.findById(52L);
        String message2 = project.get().getPhase();
        assertEquals(message1, message2);
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnOk() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 53L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnNotNullData() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 53L);
        assertNotNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnDataTheProjectHasChangeThePhase() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 53L);
        assertEquals("THE PROJECT HAS CHANGED TO THE PHASE OF TRIBUNALS", query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeThePaseOfTheProject() {
        Optional<Project> project = projectRepository.findById(53L);
        String message1 = project.get().getPhase();
        ServiceAnswer query = projectTutorService.acceptProject(51L, 53L);
        Optional<Project> project2 = projectRepository.findById(53L);
        String message2 = project.get().getPhase();
        assertTrue(!message1.equals(message2) && message2.equals("TRIBUNALS_PHASE"));
    }

    @Test
    void acceptProjectWithAProjectThatAcceptedParameterCanChangeWillChangeThatParameter() {
        ServiceAnswer query = projectTutorService.acceptProject(51L, 53L);
        ProjectTutor res = projectTutorRepository.findByTutorIdAndProjectId(51L, 53L);
        assertTrue(res.getAccepted());
    }

    @Test
    void removeAcceptProjectWithIncorrectUserIdReturnUserIdDoesNotExist() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(0L, 55L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithIncorrectUserIdReturnNullData() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(0L, 55L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithIncorrectProjectIdReturnNullData() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 0L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 2L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, query.getServiceMessage());

    }

    @Test
    void removeAcceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnNullData() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 2L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsNoAcceptedReturnProjectIsAlreadyNotAccepted() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 54L);
        assertEquals(ServiceMessage.PROJECT_IS_ALREADY_NOT_ACCEPTED, query.getServiceMessage());

    }

    @Test
    void removeAcceptProjectWithAProjectThatIsNoAcceptedReturnNullData() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 54L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInTribunalsPhaseReturnProjectIsInTribunalsPhase() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 50L);
        assertEquals(ServiceMessage.PROJECT_IS_ON_ANOTHER_PHASE, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInTribunalsPhaseReturnNullData() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 50L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInTribunalsPhaseWillNotChangeThePhase() {
        Project projectBefore = projectRepository.findById(50L).get();
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 50L);
        Project projectAfter = projectRepository.findById(50L).get();
        assertEquals(projectBefore.getPhase(), projectAfter.getPhase());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanBeChangeReturnOk() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 55L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanBeChangeReturnDataWithAValue() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 55L);
        assertNotNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanChangeWillChangeThatParameter() {
        ServiceAnswer query = projectTutorService.removeAcceptProject(51L, 55L);
        ProjectTutor res = projectTutorRepository.findByTutorIdAndProjectId(51L, 55L);
        assertFalse(res.getAccepted());
    }
}
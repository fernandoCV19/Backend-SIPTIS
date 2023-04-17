package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToTribunalHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTribunalRepository;
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
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectTribunalServiceTest {

    private final ProjectTribunalService projectTribunalService;
    private final ProjectRepository projectRepository;
    private final ProjectTribunalRepository projectTribunalRepository;

    @Autowired
    ProjectTribunalServiceTest(ProjectTribunalService projectTribunalService, ProjectRepository projectRepository, ProjectTribunalRepository projectTribunalRepository) {
        this.projectTribunalService = projectTribunalService;
        this.projectRepository = projectRepository;
        this.projectTribunalRepository = projectTribunalRepository;
    }

    @Test
    void getAllProjectsNotReviewedByTribunalIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsNotReviewedByTribunalId(4L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsNotReviewedByTribunalIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsNotReviewedByTribunalId(4L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsNotReviewedByTribunalIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsNotReviewedByTribunalId(4L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToTribunalHomePageVO);
    }

    @Test
    void getAllProjectsNotReviewedByTribunalIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsNotReviewedByTribunalId(4L);
        List<ProjectToTribunalHomePageVO> data = (List<ProjectToTribunalHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && !data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsNotReviewedByTribunalIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsNotReviewedByTribunalId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsNotReviewedByTribunalIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsNotReviewedByTribunalId(1L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsNotReviewedByTribunalIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsNotReviewedByTribunalId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsNotReviewedByTribunalIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsNotReviewedByTribunalId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsReviewedNotAcceptedByTribunalIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(4L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsReviewedNotAcceptedByTribunalIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(4L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsReviewedNotAcceptedByTribunalIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(4L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToTribunalHomePageVO);
    }

    @Test
    void getAllProjectsReviewedNotAcceptedByTribunalIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(4L);
        List<ProjectToTribunalHomePageVO> data = (List<ProjectToTribunalHomePageVO>) ans.getData();
        assertTrue(data.get(0).getReviewed() && !data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsReviewedNotAcceptedByTribunalIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsReviewedNotAcceptedByTribunalIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(1L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsReviewedNotAcceptedByTribunalIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsReviewedNotAcceptedByTribunalIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsReviewedNotAcceptedByTribunalId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsAcceptedWithoutDefensePointsByTribunalIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(4L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedWithoutDefensePointsByTribunalIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(4L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsAcceptedWithoutDefensePointsByTribunalIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(4L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToTribunalHomePageVO);
    }

    @Test
    void getAllProjectsAcceptedWithoutDefensePointsByTribunalIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(4L);
        List<ProjectToTribunalHomePageVO> data = (List<ProjectToTribunalHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && data.get(0).getAccepted() && data.get(0).getDefensePoints() == null);
    }

    @Test
    void getAllProjectsAcceptedWithoutDefensePointsByTribunalIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedWithoutDefensePointsByTribunalIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(1L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsAcceptedWithoutDefensePointsByTribunalIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedWithoutDefensePointsByTribunalIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsDefendedByTribunalIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsDefendedByTribunalId(4L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsDefendedByTribunalIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsDefendedByTribunalId(4L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsDefendedByTribunalIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsDefendedByTribunalId(4L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToTribunalHomePageVO);
    }

    @Test
    void getAllProjectsDefendedByTribunalIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsDefendedByTribunalId(4L);
        List<ProjectToTribunalHomePageVO> data = (List<ProjectToTribunalHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && data.get(0).getAccepted() && data.get(0).getDefensePoints() == 100);
    }

    @Test
    void getAllProjectsDefendedByTribunalIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsDefendedByTribunalId(1L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsDefendedByTribunalIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsDefendedByTribunalId(1L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsDefendedByTribunalIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsDefendedByTribunalId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsDefendedByTribunalIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTribunalService.getAllProjectsDefendedByTribunalId(0L);
        Object data = ans.getData();
        assertNull(data);
    }





    @Test
    void acceptProjectWithIncorrectUserIdReturnUserIdDoesNotExist() {
        ServiceAnswer query = projectTribunalService.acceptProject(0L, 50L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIncorrectUserIdReturnNullData() {
        ServiceAnswer query = projectTribunalService.acceptProject(0L, 50L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIncorrectProjectIdReturnNullData() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 0L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 1L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnNullData() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 1L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatHasAlreadyBeenAcceptedReturnProjectHasAlreadyBeenAccepted() {
        projectTribunalService.acceptProject(50L, 50L);
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 50L);
        assertEquals(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatHasAlreadyBeenAcceptedReturnNullData() {
        projectTribunalService.acceptProject(50L, 50L);
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 50L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnOk() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 50L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnNotNullData() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 50L);
        assertNotNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnDataTheProjectHasNotChangeThePhase() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 50L);
        assertEquals("THE PROJECT HAS NOT CHANGED TO THE PHASE OF DEFENSE", query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeThePaseOfTheProject() {
        Optional<Project> project = projectRepository.findById(50L);
        String message1 = project.get().getPhase();
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 50L);
        Optional<Project> project2 = projectRepository.findById(50L);
        String message2 = project.get().getPhase();
        assertEquals(message1, message2);
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnOk() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 51L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnNotNullData() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 51L);
        assertNotNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnDataTheProjectHasChangeThePhase() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 51L);
        assertEquals("THE PROJECT HAS CHANGED TO THE PHASE OF DEFENSE", query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeThePaseOfTheProject() {
        Optional<Project> project = projectRepository.findById(51L);
        String message1 = project.get().getPhase();
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 51L);
        Optional<Project> project2 = projectRepository.findById(51L);
        String message2 = project.get().getPhase();
        assertTrue(!message1.equals(message2) && message2.equals("DEFENSE_PHASE"));
    }

    @Test
    void acceptProjectWithAProjectThatAcceptedParameterCanChangeWillChangeThatParameter() {
        ServiceAnswer query = projectTribunalService.acceptProject(50L, 51L);
        ProjectTribunal res = projectTribunalRepository.findByTribunalIdAndProjectId(50L, 51L);
        assertTrue(res.getAccepted());
    }

    @Test
    void removeAcceptProjectWithIncorrectUserIdReturnUserIdDoesNotExist() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(0L, 55L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithIncorrectUserIdReturnNullData() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(0L, 55L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithIncorrectProjectIdReturnNullData() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 0L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 2L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, query.getServiceMessage());

    }

    @Test
    void removeAcceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnNullData() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 2L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsNoAcceptedReturnProjectIsAlreadyNotAccepted() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 50L);
        assertEquals(ServiceMessage.PROJECT_IS_ALREADY_NOT_ACCEPTED, query.getServiceMessage());

    }

    @Test
    void removeAcceptProjectWithAProjectThatIsNoAcceptedReturnNullData() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 50L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInReviewersPhaseReturnProjectIsOnAnotherPhase() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 53L);
        assertEquals(ServiceMessage.PROJECT_IS_ON_ANOTHER_PHASE, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInTribunalsPhaseReturnNullData() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 53L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInTribunalsPhaseWillNotChangeThePhase() {
        Project projectBefore = projectRepository.findById(53L).get();
        ServiceAnswer query = projectTribunalService.removeAcceptProject(50L, 53L);
        Project projectAfter = projectRepository.findById(53L).get();
        assertEquals(projectBefore.getPhase(), projectAfter.getPhase());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanBeChangeReturnOk() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(51L, 51L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanBeChangeReturnDataWithAValue() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(51L, 51L);
        assertNotNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanChangeWillChangeThatParameter() {
        ServiceAnswer query = projectTribunalService.removeAcceptProject(51L, 51L);
        ProjectTribunal res = projectTribunalRepository.findByTribunalIdAndProjectId(51L, 51L);
        assertFalse(res.getAccepted());
    }
}

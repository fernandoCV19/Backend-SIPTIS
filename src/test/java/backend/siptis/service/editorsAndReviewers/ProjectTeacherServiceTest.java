package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToHomePageVO;
import backend.siptis.model.repository.editorsAndReviewers.ProjectTeacherRepository;
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
class ProjectTeacherServiceTest {

    private final ProjectTeacherService projectTeacherService;
    private final ProjectRepository projectRepository;
    private final ProjectTeacherRepository projectTeacherRepository;

    @Autowired
    ProjectTeacherServiceTest(ProjectTeacherServiceImpl projectTeacherService, ProjectRepository projectRepository, ProjectTeacherRepository projectTeacherRepository) {
        this.projectTeacherService = projectTeacherService;
        this.projectRepository = projectRepository;
        this.projectTeacherRepository = projectTeacherRepository;
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(3L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(3L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(3L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(3L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(data.get(0).getReviewed() && !data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(2L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(3L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(3L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(3L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(3L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && !data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(2L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(0L);
        Object data = ans.getData();
        assertNull(data);
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAnIdThatExistReturnOk() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(3L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAndIdThatExistReturnData() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(3L);
        Object data = ans.getData();
        assertNotNull(data);
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAndIdThatExistReturnACorrectDTOObject() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(3L);
        Object data = ans.getData();
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageVO);
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(3L);
        List<ProjectToHomePageVO> data = (List<ProjectToHomePageVO>) ans.getData();
        assertTrue(!data.get(0).getReviewed() && data.get(0).getAccepted());
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAnIdThatDoesNotHaveProjectsReturnWithout_Projects() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(2L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.WITHOUT_PROJECTS, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAnIdThatDoesNotHaveProjectsReturnAEmptyListOfProjects() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(2L);
        Object data = ans.getData();
        assertTrue(((List<?>) data).isEmpty());
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAnIdThatDoesNotExistReturnIdDoesNotExist() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(0L);
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, serviceMessage);
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAnIdThatDoesNotExistReturnNullInData() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(0L);
        Object data = ans.getData();
        assertNull(data);
    }




    @Test
    void acceptProjectWithIncorrectUserIdReturnUserIdDoesNotExist() {
        ServiceAnswer query = projectTeacherService.acceptProject(0L, 56L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIncorrectUserIdReturnNullData() {
        ServiceAnswer query = projectTeacherService.acceptProject(0L, 56L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIncorrectProjectIdReturnNullData() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 0L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 1L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnNullData() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 1L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatHasAlreadyBeenAcceptedReturnProjectHasAlreadyBeenAccepted() {
        projectTeacherService.acceptProject(53L, 56L);
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 56L);
        assertEquals(ServiceMessage.PROJECT_HAS_ALREADY_BEEN_ACCEPTED, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatHasAlreadyBeenAcceptedReturnNullData() {
        projectTeacherService.acceptProject(53L, 56L);
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 56L);
        assertNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnOk() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 56L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnNotNullData() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 56L);
        assertNotNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeReturnDataTheProjectHasNotChangeThePhase() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 56L);
        assertEquals("THE PROJECT HAS NOT CHANGED TO THE PHASE OF TRIBUNALS", query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillNotChangeThePaseOfTheProject() {
        Optional<Project> project = projectRepository.findById(56L);
        String message1 = project.get().getPhase();
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 56L);
        Optional<Project> project2 = projectRepository.findById(56L);
        String message2 = project.get().getPhase();
        assertEquals(message1, message2);
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnOk() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 57L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnNotNullData() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 57L);
        assertNotNull(query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeReturnDataTheProjectHasChangeThePhase() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 57L);
        assertEquals("THE PROJECT HAS CHANGED TO THE PHASE OF TRIBUNALS", query.getData());
    }

    @Test
    void acceptProjectWithAProjectThatPhaseWillChangeThePaseOfTheProject() {
        Optional<Project> project = projectRepository.findById(57L);
        String message1 = project.get().getPhase();
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 57L);
        Optional<Project> project2 = projectRepository.findById(57L);
        String message2 = project.get().getPhase();
        assertTrue(!message1.equals(message2) && message2.equals("TRIBUNALS_PHASE"));
    }

    @Test
    void acceptProjectWithAProjectThatAcceptedParameterCanChangeWillChangeThatParameter() {
        ServiceAnswer query = projectTeacherService.acceptProject(53L, 57L);
        ProjectTeacher res = projectTeacherRepository.findByTeacherIdAndProjectId(53L, 57L);
        assertTrue(res.getAccepted());
    }

    @Test
    void removeAcceptProjectWithIncorrectUserIdReturnUserIdDoesNotExist() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(0L, 52L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithIncorrectUserIdReturnNullData() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(0L, 52L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithIncorrectProjectIdReturnProjectIdDoesNotExist() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithIncorrectProjectIdReturnNullData() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 0L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnIdReviewerDoesNotMatchWithProject() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 2L);
        assertEquals(ServiceMessage.ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, query.getServiceMessage());

    }

    @Test
    void removeAcceptProjectWithIdReviewerThatDoesNotMatchTheProjectReturnNullData() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 2L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsNoAcceptedReturnProjectIsAlreadyNotAccepted() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 54L);
        assertEquals(ServiceMessage.PROJECT_IS_ALREADY_NOT_ACCEPTED, query.getServiceMessage());

    }

    @Test
    void removeAcceptProjectWithAProjectThatIsNoAcceptedReturnNullData() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 54L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInTribunalsPhaseReturnProjectIsInTribunalsPhase() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 50L);
        assertEquals(ServiceMessage.PROJECT_IS_ON_ANOTHER_PHASE, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInTribunalsPhaseReturnNullData() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 50L);
        assertNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatIsInTribunalsPhaseWillNotChangeThePhase() {
        Project projectBefore = projectRepository.findById(50L).get();
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 50L);
        Project projectAfter = projectRepository.findById(50L).get();
        assertEquals(projectBefore.getPhase(), projectAfter.getPhase());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanBeChangeReturnOk() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 53L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanBeChangeReturnDataWithAValue() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 53L);
        assertNotNull(query.getData());
    }

    @Test
    void removeAcceptProjectWithAProjectThatAcceptedParameterCanChangeWillChangeThatParameter() {
        ServiceAnswer query = projectTeacherService.removeAcceptProject(53L, 53L);
        ProjectTeacher res = projectTeacherRepository.findByTeacherIdAndProjectId(53L, 53L);
        assertFalse(res.getAccepted());
    }
}
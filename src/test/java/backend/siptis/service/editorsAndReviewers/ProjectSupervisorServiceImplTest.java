package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.projectManagement.ProjectToHomePageDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectSupervisorServiceImplTest {

    private final ProjectSupervisorService projectSupervisorService;

    @Autowired
    ProjectSupervisorServiceImplTest(ProjectSupervisorServiceImpl projectSupervisorService) {
        this.projectSupervisorService = projectSupervisorService;
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedBySupervisorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(1L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedBySupervisorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(1L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsAcceptedBySupervisorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(1L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
}
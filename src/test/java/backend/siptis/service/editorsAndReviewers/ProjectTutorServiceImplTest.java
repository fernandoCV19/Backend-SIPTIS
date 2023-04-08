package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.projectManagement.ProjectToHomePageDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectTutorServiceImplTest {

    private final ProjectTutorService projectTutorService;

    @Autowired
    ProjectTutorServiceImplTest(ProjectTutorService projectTutorService) {
        this.projectTutorService = projectTutorService;
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTutorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(2L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTutorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(2L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsAcceptedByTutorIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTutorService.getAllProjectsAcceptedByTutorId(2L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
}
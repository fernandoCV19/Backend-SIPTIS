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
class ProjectTeacherServiceImplTest {

    private final ProjectTeacherService projectTeacherService;

    @Autowired
    ProjectTeacherServiceImplTest(ProjectTeacherServiceImpl projectTeacherService) {
        this.projectTeacherService = projectTeacherService;
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsNotAcceptedReviewedByTeacherIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(3L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsNotAcceptedNotReviewedByTeacherIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(3L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
        assertTrue(data instanceof List && ((List<?>) data).get(0) instanceof ProjectToHomePageDTO);
    }

    @Test
    void getAllProjectsAcceptedByTeacherIdWithAndIdThatExistReturnADTOObjectThatIsNotAcceptedAndReviewed() {
        ServiceAnswer ans = projectTeacherService.getAllProjectsAcceptedByTeacherId(3L);
        List<ProjectToHomePageDTO> data = (List<ProjectToHomePageDTO>) ans.getData();
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
}
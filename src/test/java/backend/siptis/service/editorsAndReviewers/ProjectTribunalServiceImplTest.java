package backend.siptis.service.editorsAndReviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.vo.projectManagement.ProjectToTribunalHomePageVO;
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
class ProjectTribunalServiceImplTest {

    private final ProjectTribunalService projectTribunalService;

    @Autowired
    ProjectTribunalServiceImplTest(ProjectTribunalService projectTribunalService) {
        this.projectTribunalService = projectTribunalService;
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
}

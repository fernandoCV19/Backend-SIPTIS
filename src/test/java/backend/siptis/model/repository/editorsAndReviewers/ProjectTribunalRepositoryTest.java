package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.model.entity.projectManagement.Project;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectTribunalRepositoryTest {

    private final ProjectTribunalRepository projectTribunalRepository;

    @Autowired
    ProjectTribunalRepositoryTest(ProjectTribunalRepository projectTribunalRepository) {
        this.projectTribunalRepository = projectTribunalRepository;
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseWithAFalseIdReturnNotNull() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnAListWithElements() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(4L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(4L);
        ProjectTribunal projectTribunal = ans.get(0);
        assertTrue(projectTribunal.getProject() != null && projectTribunal.getTribunal() != null && projectTribunal.getProject().getName() != null && projectTribunal.getTribunal().getEmail() != null);
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(4L);
        SiptisUser tribunal = ans.get(0).getTribunal();
        assertEquals("usuario4@mail.com", tribunal.getEmail());
        assertEquals(4, tribunal.getId());
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(4L);
        Project project = ans.get(0).getProject();
        assertEquals(10, project.getId());
        assertEquals("ProyectoGrado10", project.getName());
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnOnlyOneElement() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(4L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueWithAFalseIdReturnNotNull() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnAListWithElements() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(4L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(4L);
        ProjectTribunal projectTribunal = ans.get(0);
        assertTrue(projectTribunal.getProject() != null && projectTribunal.getTribunal() != null && projectTribunal.getProject().getName() != null && projectTribunal.getTribunal().getEmail() != null);
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(4L);
        SiptisUser tribunal = ans.get(0).getTribunal();
        assertEquals("usuario4@mail.com", tribunal.getEmail());
        assertEquals(4, tribunal.getId());
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(4L);
        Project project = ans.get(0).getProject();
        assertEquals(11, project.getId());
        assertEquals("ProyectoGrado11", project.getName());
    }

    @Test
    void findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnOnlyOneElement() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(4L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullWithAFalseIdReturnNotNull() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullWithATrueIdReturnAListWithElements() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(4L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(4L);
        ProjectTribunal projectTribunal = ans.get(0);
        assertTrue(projectTribunal.getProject() != null && projectTribunal.getTribunal() != null && projectTribunal.getProject().getName() != null && projectTribunal.getTribunal().getEmail() != null);
    }

    @Test
    void findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(4L);
        SiptisUser tribunal = ans.get(0).getTribunal();
        assertEquals("usuario4@mail.com", tribunal.getEmail());
        assertEquals(4, tribunal.getId());
    }

    @Test
    void findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(4L);
        Project project = ans.get(0).getProject();
        assertEquals(12, project.getId());
        assertEquals("ProyectoGrado12", project.getName());
    }

    @Test
    void findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullWithATrueIdReturnOnlyOneElement() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(4L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTribunalIdAndDefensePointsIsNotNullWithAFalseIdReturnNotNull() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTribunalIdAndDefensePointsIsNotNullWithATrueIdReturnAListWithElements() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(4L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTribunalIdAndDefensePointsIsNotNullWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(4L);
        ProjectTribunal projectTribunal = ans.get(0);
        assertTrue(projectTribunal.getProject() != null && projectTribunal.getTribunal() != null && projectTribunal.getProject().getName() != null && projectTribunal.getTribunal().getEmail() != null);
    }

    @Test
    void findByTribunalIdAndDefensePointsIsNotNullWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(4L);
        SiptisUser tribunal = ans.get(0).getTribunal();
        assertEquals("usuario4@mail.com", tribunal.getEmail());
        assertEquals(4, tribunal.getId());
    }

    @Test
    void findByTribunalIdAndDefensePointsIsNotNullWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(4L);
        Project project = ans.get(0).getProject();
        assertEquals(13, project.getId());
        assertEquals("ProyectoGrado13", project.getName());
    }

    @Test
    void findByTribunalIdAndDefensePointsIsNotNullWithATrueIdReturnOnlyOneElement() {
        List<ProjectTribunal> ans = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(4L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTribunalIdAndProjectIdWithACorrectUserIdAndProjectIdReturnAnObject() {
        ProjectTribunal query = projectTribunalRepository.findByTribunalIdAndProjectId(4L, 10L);
        assertNotNull(query);
    }

    @Test
    void findByTribunalIdAndProjectIdWithAnIncorrectUserIdAndProjectIdReturnANull() {
        ProjectTribunal query = projectTribunalRepository.findByTribunalIdAndProjectId(0L, 0L);
        assertNull(query);
    }

    @Test
    void findByTribunalIdAndProjectIdWithACorrectUserIdAndProjectIdReturnAnObjectThatContainsTheProjectAndTheReviewer() {
        ProjectTribunal query = projectTribunalRepository.findByTribunalIdAndProjectId(4L, 10L);
        assertTrue(query.getTribunal().getId() == 4L && query.getProject().getId() == 10L);
    }
}
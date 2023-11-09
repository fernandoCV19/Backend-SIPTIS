package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectTutorRepositoryTest {

    private final ProjectTutorRepository projectTutorRepository;

    @Autowired
    ProjectTutorRepositoryTest(ProjectTutorRepository projectTutorRepository) {
        this.projectTutorRepository = projectTutorRepository;
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsTrueWithAFalseIdReturnNotNull() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnAListWithElements() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(2L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(2L);
        ProjectTutor projectTutor = ans.get(0);
        assertTrue(projectTutor.getProject() != null && projectTutor.getTutor() != null && projectTutor.getProject().getName() != null && projectTutor.getTutor().getEmail() != null);
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(2L);
        SiptisUser tutor = ans.get(0).getTutor();
        assertEquals("usuario2@mail.com", tutor.getEmail());
        assertEquals(2, tutor.getId());
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(2L);
        Project project = ans.get(0).getProject();
        assertEquals(5, project.getId());
        assertEquals("ProyectoGrado5", project.getName());
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnOnlyOneElement() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(2L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsFalseWithAFalseIdReturnNotNull() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnAListWithElements() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(2L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(2L);
        ProjectTutor projectTutor = ans.get(0);
        assertTrue(projectTutor.getProject() != null && projectTutor.getTutor() != null && projectTutor.getProject().getName() != null && projectTutor.getTutor().getEmail() != null);
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(2L);
        SiptisUser tutor = ans.get(0).getTutor();
        assertEquals("usuario2@mail.com", tutor.getEmail());
        assertEquals(2, tutor.getId());
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(2L);
        Project project = ans.get(0).getProject();
        assertEquals(4, project.getId());
        assertEquals("ProyectoGrado4", project.getName());
    }

    @Test
    void findByTutorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnOnlyOneElement() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(2L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTutorIdAndAcceptedIsTrueWithAFalseIdReturnNotNull() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTutorIdAndAcceptedIsTrueWithATrueIdReturnAListWithElements() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(2L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTutorIdAndAcceptedIsTrueWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(2L);
        ProjectTutor projectTutor = ans.get(0);
        assertTrue(projectTutor.getProject() != null && projectTutor.getTutor() != null && projectTutor.getProject().getName() != null && projectTutor.getTutor().getEmail() != null);
    }

    @Test
    void findByTutorIdAndAcceptedIsTrueWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(2L);
        SiptisUser tutor = ans.get(0).getTutor();
        assertEquals("usuario2@mail.com", tutor.getEmail());
        assertEquals(2, tutor.getId());
    }

    @Test
    void findByTutorIdAndAcceptedIsTrueWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(2L);
        Project project = ans.get(0).getProject();
        assertEquals(6, project.getId());
        assertEquals("ProyectoGrado6", project.getName());
    }

    @Test
    void findByTutorIdAndAcceptedIsTrueWithATrueIdReturnOnlyOneElement() {
        List<ProjectTutor> ans = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(2L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTutorIdAndProjectIdWithACorrectUserIdAndProjectIdReturnAnObject() {
        ProjectTutor query = projectTutorRepository.findByTutorIdAndProjectId(2L, 4L);
        assertNotNull(query);
    }

    @Test
    void findByTutorIdAndProjectIdWithAnIncorrectUserIdAndProjectIdReturnANull() {
        ProjectTutor query = projectTutorRepository.findByTutorIdAndProjectId(0L, 0L);
        assertNull(query);
    }

    @Test
    void findByTutorIdAndProjectIdWithACorrectUserIdAndProjectIdReturnAnObjectThatContainsTheProjectAndTheReviewer() {
        ProjectTutor query = projectTutorRepository.findByTutorIdAndProjectId(2L, 4L);
        assertTrue(query.getTutor().getId() == 2L && query.getProject().getId() == 4L);
    }
}
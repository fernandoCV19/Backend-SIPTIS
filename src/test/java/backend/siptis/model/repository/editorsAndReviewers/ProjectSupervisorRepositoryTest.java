package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.projectManagement.Project;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectSupervisorRepositoryTest {

    private final ProjectSupervisorRepository projectSupervisorRepository;

    @Autowired
    ProjectSupervisorRepositoryTest(ProjectSupervisorRepository projectSupervisorRepository) {
        this.projectSupervisorRepository = projectSupervisorRepository;
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrueWithAFalseIdReturnNotNull() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(0L);
        assertNotNull(ans);
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnAListWithElements() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(1L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(1L);
        ProjectSupervisor projectSupervisor = ans.get(0);
        assertTrue(projectSupervisor.getProject() != null && projectSupervisor.getSupervisor() != null && projectSupervisor.getProject().getName() != null && projectSupervisor.getSupervisor().getEmail() != null);
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(1L);
        SiptisUser supervisor = ans.get(0).getSupervisor();
        assertEquals("usuario1@mail.com", supervisor.getEmail());
        assertEquals(1, supervisor.getId());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(1L);
        Project project = ans.get(0).getProject();
        assertEquals(2, project.getId());
        assertEquals("ProyectoGrado2", project.getName());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnOnlyOneElement() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(1L);
        assertEquals(1, ans.size());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalseWithAFalseIdReturnNotNull() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(0L);
        assertNotNull(ans);
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnAListWithElements() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(1L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(1L);
        ProjectSupervisor projectSupervisor = ans.get(0);
        assertTrue(projectSupervisor.getProject() != null && projectSupervisor.getSupervisor() != null && projectSupervisor.getProject().getName() != null && projectSupervisor.getSupervisor().getEmail() != null);
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(1L);
        SiptisUser supervisor = ans.get(0).getSupervisor();
        assertEquals("usuario1@mail.com", supervisor.getEmail());
        assertEquals(1, supervisor.getId());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(1L);
        Project project = ans.get(0).getProject();
        assertEquals(1, project.getId());
        assertEquals("ProyectoGrado1", project.getName());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnOnlyOneElement() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(1L);
        assertEquals(1, ans.size());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsTrueWithAFalseIdReturnNotNull() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(0L);
        assertNotNull(ans);
    }

    @Test
    void findBySupervisorIdAndAcceptedIsTrueWithATrueIdReturnAListWithElements() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(1L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsTrueWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(1L);
        ProjectSupervisor projectSupervisor = ans.get(0);
        assertTrue(projectSupervisor.getProject() != null && projectSupervisor.getSupervisor() != null && projectSupervisor.getProject().getName() != null && projectSupervisor.getSupervisor().getEmail() != null);
    }

    @Test
    void findBySupervisorIdAndAcceptedIsTrueWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(1L);
        SiptisUser supervisor = ans.get(0).getSupervisor();
        assertEquals("usuario1@mail.com", supervisor.getEmail());
        assertEquals(1, supervisor.getId());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsTrueWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(1L);
        Project project = ans.get(0).getProject();
        assertEquals(3, project.getId());
        assertEquals("ProyectoGrado3", project.getName());
    }

    @Test
    void findBySupervisorIdAndAcceptedIsTrueWithATrueIdReturnOnlyOneElement() {
        List<ProjectSupervisor> ans = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(1L);
        assertEquals(1, ans.size());
    }
}
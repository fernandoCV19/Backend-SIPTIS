package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
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
class ProjectTeacherRepositoryTest {

    private final ProjectTeacherRepository projectTeacherRepository;

    @Autowired
    ProjectTeacherRepositoryTest(ProjectTeacherRepository projectTeacherRepository) {
        this.projectTeacherRepository = projectTeacherRepository;
    }
    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrueWithAFalseIdReturnNotNull() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnAListWithElements() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(3L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(3L);
        ProjectTeacher projectTeacher = ans.get(0);
        assertTrue(projectTeacher.getProject() != null && projectTeacher.getTeacher() != null && projectTeacher.getProject().getName() != null && projectTeacher.getTeacher().getEmail() != null);
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(3L);
        SiptisUser teacher = ans.get(0).getTeacher();
        assertEquals("usuario3@mail.com", teacher.getEmail());
        assertEquals(3, teacher.getId());
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(3L);
        Project project = ans.get(0).getProject();
        assertEquals(8, project.getId());
        assertEquals("ProyectoGrado8", project.getName());
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrueWithATrueIdReturnOnlyOneElement() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(3L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalseWithAFalseIdReturnNotNull() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnAListWithElements() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(3L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(3L);
        ProjectTeacher projectTeacher = ans.get(0);
        assertTrue(projectTeacher.getProject() != null && projectTeacher.getTeacher() != null && projectTeacher.getProject().getName() != null && projectTeacher.getTeacher().getEmail() != null);
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(3L);
        SiptisUser teacher = ans.get(0).getTeacher();
        assertEquals("usuario3@mail.com", teacher.getEmail());
        assertEquals(3, teacher.getId());
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(3L);
        Project project = ans.get(0).getProject();
        assertEquals(7, project.getId());
        assertEquals("ProyectoGrado7", project.getName());
    }

    @Test
    void findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalseWithATrueIdReturnOnlyOneElement() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(3L);
        assertEquals(1, ans.size());
    }

    @Test
    void findByTeacherIdAndAcceptedIsTrueWithAFalseIdReturnNotNull() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(0L);
        assertNotNull(ans);
    }

    @Test
    void findByTeacherIdAndAcceptedIsTrueWithATrueIdReturnAListWithElements() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(3L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByTeacherIdAndAcceptedIsTrueWithATrueIdDoACorrectJoinOfTheData() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(3L);
        ProjectTeacher projectTeacher = ans.get(0);
        assertTrue(projectTeacher.getProject() != null && projectTeacher.getTeacher() != null && projectTeacher.getProject().getName() != null && projectTeacher.getTeacher().getEmail() != null);
    }

    @Test
    void findByTeacherIdAndAcceptedIsTrueWithATrueIdReturnTheSupervisorDataThatHasThatId() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(3L);
        SiptisUser teacher = ans.get(0).getTeacher();
        assertEquals("usuario3@mail.com", teacher.getEmail());
        assertEquals(3, teacher.getId());
    }

    @Test
    void findByTeacherIdAndAcceptedIsTrueWithATrueIdReturnAProjectWithTheCorrectJoin() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(3L);
        Project project = ans.get(0).getProject();
        assertEquals(9, project.getId());
        assertEquals("ProyectoGrado9", project.getName());
    }

    @Test
    void findByTeacherIdAndAcceptedIsTrueWithATrueIdReturnOnlyOneElement() {
        List<ProjectTeacher> ans = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(3L);
        assertEquals(1, ans.size());
    }
}
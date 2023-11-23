package backend.siptis.model.repository.project_management;

import backend.siptis.model.entity.project_management.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;
    private final Pageable pageable = PageRequest.of(1, 10);

    @Test
    @DisplayName("Test for project")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenGetIdsListFromReviewers_thenLongList() {
        List<Long> list = projectRepository.getIdsListFromReviewers(1L);
        assertNotNull(list);
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Test for project empty list ")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjectsAndWrongProjectId_whenGetIdsListFromReviewers_thenEmpty() {
        List<Long> list = projectRepository.getIdsListFromReviewers(12345L);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Test for find list of projects with defense")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllWithDefense(pageable);
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by name")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllByNameWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllByNameWithDefense(pageable, "Name1");
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }

    @Test
    @DisplayName("Test for find empty list of projects with defense by wrong name")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjectsAndWrongName_whenFindAllByNameWithDefense_thenEmpty() {
        Page<Project> projects = projectRepository.findAllByNameWithDefense(pageable, "Wrong Name");
        assertTrue(projects.isEmpty());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by modality")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllByModalityWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllByModalityWithDefense(pageable, "TESIS");
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by area")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllByAreaWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllByAreaWithDefense(pageable, "AREA1");
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by area")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllBySubAreaWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllBySubAreaWithDefense(pageable, "SUB_AREA1");
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by area")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllBySubAreaWithDefense_thenEmpty() {
        assertTrue(projectRepository.findAllBySubAreaWithDefense(pageable, "SUB_AREA56").isEmpty());
    }

    @Test
    @DisplayName("Test for find list of projects by standart filter")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenStandardFilter_thenEmptyList() {
        assertTrue(projectRepository.standardFilter(pageable, "Perfil4", "2-2023").isEmpty());
    }

    @Test
    @DisplayName("Test for find list of projects by advanced filter")
    void givenProjects_whenAdvancedFilter_thenEmptyList() {
        Page<Project> projects = projectRepository.advancedFilter("", "", "", "", "", "", "", pageable);
        assertTrue(projects.isEmpty());
    }

    @Test
    @DisplayName("Test for exist by name")
    void givenProjects_whenExistByName_thenFalse() {
        assertFalse(projectRepository.existsByName("wrong name"));
    }

    @Test
    @DisplayName("Test for search project")
    void givenProjects_whenSearchProject_thenEmptyList() {
        projectRepository.searchProject("", pageable);
    }

    @Test
    @DisplayName("Test for get number of projects by modality and career")
    void givenProjects_whenGetNumberOfProjectsByModalityAndCareer_thenEmptyList() {
        assertFalse(projectRepository.getNumberOfProjectsByModalityAndCareer(123456L).isEmpty());
    }

    @Test
    @DisplayName("Test for get number of projects by areas and career")
    void givenProjects_whenGetNumberOfProjectsByAreasAndCareer_thenEmptyList() {
        assertTrue(projectRepository.getNumberOfProjectsByAreasAndCareer(123456L).isEmpty());
    }

    @Test
    @DisplayName("Test for get number of projects by areas and career")
    void givenProjects_whenGetNumberOfProjectsBySubAreasAndCareer_thenEmptyList() {
        assertTrue(projectRepository.getNumberOfProjectsBySubAreasAndCareer(123456L).isEmpty());
    }

    @Test
    @DisplayName("Test for get number of projects by career")
    void givenProjects_whenGetProjectsByCareer_thenEmptyList() {
        assertFalse(projectRepository.getProjectsByCareer(123456L).isEmpty());
    }

    @Test
    @DisplayName("Test for get number of projects by period and career")
    void givenProjects_whenGetNumberProjectsByPeriodAndCareer_thenEmptyList() {
        assertTrue(projectRepository.getNumberProjectsByPeriodAndCareer(123456L).isEmpty());
    }
}

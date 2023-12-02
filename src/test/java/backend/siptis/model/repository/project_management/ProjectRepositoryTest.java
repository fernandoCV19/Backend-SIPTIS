package backend.siptis.model.repository.project_management;

import backend.siptis.model.entity.project_management.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProjectRepositoryTest {
    private final Pageable pageable = PageRequest.of(1, 10);
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @DisplayName("Test for search project")
    void givenProjects_whenSearchProject_thenEmptyList() {
        assertTrue(projectRepository.searchProject("bad project name", pageable).isEmpty());
    }
    @Test
    @DisplayName("Test for search project success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenSearchProject_thenList() {
        assertFalse(projectRepository.searchProject("", pageable).isEmpty());
    }

    @Test
    @DisplayName("Test for get id list from reviewers")
    void givenProjects_whenGetIdsListFromReviewers_thenEmptyList() {
        List<Long> list = projectRepository.getIdsListFromReviewers(1L);
        assertTrue(list.isEmpty());
    }
    @Test
    @DisplayName("Test for get id list from reviewers success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenGetIdsListFromReviewers_thenLongList() {
        List<Long> list = projectRepository.getIdsListFromReviewers(101L);
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("Test for find list of projects with defense")
    void givenProjects_whenFindAllWithDefense_thenEmptyProjectList() {
        Page<Project> projects = projectRepository.findAllWithDefense(pageable);
        assertTrue(projects.isEmpty());
    }
    @Test
    @DisplayName("Test for find list of projects with defense success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllWithDefense(pageable);
        assertNotNull(projects);
    }

    @Test
    @DisplayName("Test for find list of projects with defense by name")
    void givenProjects_whenFindAllByNameWithDefense_thenEmptyProjectList() {
        Page<Project> projects = projectRepository.findAllByNameWithDefense(pageable, "Proyecto de Desarrollo de Aplicación Web");
        assertTrue(projects.isEmpty());
    }
    @Test
    @DisplayName("Test for find list of projects with defense by name")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllByNameWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllByNameWithDefense(pageable, "Proyecto de Desarrollo de Aplicación Web");
        assertNotNull(projects);
    }

    @Test
    @DisplayName("Test for find list of projects with defense by modality")
    void givenProjects_whenFindAllByModalityWithDefense_thenEmptyProjectList() {
        Page<Project> projects = projectRepository.findAllByModalityWithDefense(pageable, "TESIS");
        assertTrue(projects.isEmpty());
    }
    @Test
    @DisplayName("Test for find list of projects with defense by modality success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllByModalityWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllByModalityWithDefense(pageable, "TESIS");
        assertNotNull(projects);
    }

    @Test
    @DisplayName("Test for find list of projects with defense by area")
    void givenProjects_whenFindAllByAreaWithDefense_thenemptyProjectList() {
        Page<Project> projects = projectRepository.findAllByAreaWithDefense(pageable, "AREA1");
        assertTrue(projects.isEmpty());
    }
    @Test
    @DisplayName("Test for find list of projects with defense by area success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllByAreaWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllByAreaWithDefense(pageable, "AREA1");
        assertNotNull(projects);
    }

    @Test
    @DisplayName("Test for find list of projects with defense by area")
    void givenProjects_whenFindAllBySubAreaWithDefense_thenEmptyProjectList() {
        Page<Project> projects = projectRepository.findAllBySubAreaWithDefense(pageable, "SUB_AREA1");
        assertTrue(projects.isEmpty());
    }
    @Test
    @DisplayName("Test for find list of projects with defense by area success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenFindAllBySubAreaWithDefense_thenProjectList() {
        Page<Project> projects = projectRepository.findAllBySubAreaWithDefense(pageable, "SUB_AREA1");
        assertNotNull(projects);
    }

    @Test
    @DisplayName("Test for find list of projects by standart filter")
    void givenProjects_whenStandardFilter_thenEmptyList() {
        assertTrue(projectRepository.standardFilter(pageable, "Perfil4", "2-2023").isEmpty());
    }
    @Test
    @DisplayName("Test for find list of projects by standart filter success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenStandardFilter_thenList() {
        assertNotNull(projectRepository.standardFilter(pageable, "a", "2-2023"));
    }

    @Test
    @DisplayName("Test for find list of projects by advanced filter")
    void givenProjects_whenAdvancedFilter_thenEmptyList() {
        Page<Project> projects = projectRepository.advancedFilter("", "", "", "", "", "", "", pageable);
        assertTrue(projects.isEmpty());
    }
    @Test
    @DisplayName("Test for find list of projects by advanced filter success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenAdvancedFilter_thenList() {
        Page<Project> projects = projectRepository.advancedFilter("", "", "", "", "", "", "", pageable);
        assertNotNull(projects);
    }

    @Test
    @DisplayName("Test for exist by name")
    void givenProjects_whenExistByName_thenFalse() {
        assertFalse(projectRepository.existsByName("wrong name"));
    }
    @Test
    @DisplayName("Test for exist by name success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenExistByName_thenTrue() {
        assertTrue(projectRepository.existsByName("Estudio de Blockchain en Aplicaciones Financieras"));
    }

    @Test
    @DisplayName("Test for get number of projects by modality and career")
    void givenProjects_whenGetNumberOfProjectsByModalityAndCareer_thenEmptyList() {
        assertTrue(projectRepository.getNumberOfProjectsByModalityAndCareer(123456L).isEmpty());
    }
    @Test
    @DisplayName("Test for get number of projects by modality and career success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenGetNumberOfProjectsByModalityAndCareer_thenList() {
        assertNotNull(projectRepository.getNumberOfProjectsByModalityAndCareer(100l));
    }

    @Test
    @DisplayName("Test for get number of projects by areas and career")
    void givenProjects_whenGetNumberOfProjectsByAreasAndCareer_thenEmptyList() {
        assertTrue(projectRepository.getNumberOfProjectsByAreasAndCareer(123456L).isEmpty());
    }
    @Test
    @DisplayName("Test for get number of projects by areas and career success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenGetNumberOfProjectsByAreasAndCareer_thenList() {
        assertNotNull(projectRepository.getNumberOfProjectsByAreasAndCareer(100l));
    }

    @Test
    @DisplayName("Test for get number of projects by areas and career")
    void givenProjects_whenGetNumberOfProjectsBySubAreasAndCareer_thenEmptyList() {
        assertTrue(projectRepository.getNumberOfProjectsBySubAreasAndCareer(123456L).isEmpty());
    }
    @Test
    @DisplayName("Test for get number of projects by areas and career success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenGetNumberOfProjectsBySubAreasAndCareer_thenList() {
        assertNotNull(projectRepository.getNumberOfProjectsBySubAreasAndCareer(100L));
    }

    @Test
    @DisplayName("Test for get number of projects by career")
    void givenProjects_whenGetProjectsByCareer_thenEmptyList() {
        assertFalse(projectRepository.getProjectsByCareer(12345L).isEmpty());
    }
    @Test
    @DisplayName("Test for get number of projects by career success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenGetProjectsByCareer_thenList() {
        assertNotNull(projectRepository.getProjectsByCareer(100L));
    }

    @Test
    @DisplayName("Test for get number of projects by period and career")
    void givenProjects_whenGetNumberProjectsByPeriodAndCareer_thenEmptyList() {
        assertTrue(projectRepository.getNumberProjectsByPeriodAndCareer(123456L).isEmpty());
    }
    @Test
    @DisplayName("Test for get number of projects by period and career")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjects_whenGetNumberProjectsByPeriodAndCareer_thenList() {
        assertNotNull(projectRepository.getNumberProjectsByPeriodAndCareer(123456L));
    }
}

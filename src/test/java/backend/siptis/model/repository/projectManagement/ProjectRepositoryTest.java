package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;
    private Pageable pageable = PageRequest.of(1, 10);;

    @Test
    @DisplayName("Test for project")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjects_whenGetIdsListFromReviewers_thenLongList(){
        List<Long> list = projectRepository.getIdsListFromReviewers(1L);
        assertNotNull(list);
        assertEquals(3, list.size());
    }
    @Test
    @DisplayName("Test for project empty list ")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjectsAndWrongProjectId_whenGetIdsListFromReviewers_thenEmpty(){
        List<Long> list = projectRepository.getIdsListFromReviewers(12345L);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Test for find list of projects with defense")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjects_whenFindAllWithDefense_thenProjectList(){
        Page<Project> projects = projectRepository.findAllWithDefense(pageable);
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by name")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjects_whenFindAllByNameWithDefense_thenProjectList(){
        Page<Project> projects = projectRepository.findAllByNameWithDefense(pageable, "Name1");
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }
    @Test
    @DisplayName("Test for find empty list of projects with defense by wrong name")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjectsAndWrongName_whenFindAllByNameWithDefense_thenEmpty(){
        Page<Project> projects = projectRepository.findAllByNameWithDefense(pageable, "Wrong Name");
        assertTrue(projects.isEmpty());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by modality")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjects_whenFindAllByModalityWithDefense_thenProjectList(){
        Page<Project> projects = projectRepository.findAllByModalityWithDefense(pageable, "TESIS");
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }
    @Test
    @DisplayName("Test for find list of projects with defense by area")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjects_whenFindAllByAreaWithDefense_thenProjectList(){
        Page<Project> projects = projectRepository.findAllByAreaWithDefense(pageable, "AREA1");
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by area")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjects_whenFindAllBySubAreaWithDefense_thenProjectList(){
        Page<Project> projects = projectRepository.findAllBySubAreaWithDefense(pageable, "SUB_AREA1");
        assertNotNull(projects);
        assertEquals(1L, projects.getTotalElements());
    }
    @Test
    @DisplayName("Test for find list of projects with defense by area")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjects_whenFindAllBySubAreaWithDefense_thenEmpty(){
        Page<Project> projects = projectRepository.findAllBySubAreaWithDefense(pageable, "SUB_AREA56");
        assertTrue(projects.isEmpty());
    }

    @Test
    @DisplayName("Test for find list of projects with defense by area")
    @Sql(scripts = {"/custom_imports/projectTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenProjects_whenStandardFilter_then(){
        Page<Project> projects = projectRepository.standardFilter(pageable, "Perfil4","2-2023");
        assertTrue(projects.isEmpty());
    }

}

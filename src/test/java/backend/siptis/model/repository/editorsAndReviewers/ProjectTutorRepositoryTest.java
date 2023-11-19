package backend.siptis.model.repository.editorsAndReviewers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class ProjectTutorRepositoryTest {
    @Autowired
    private ProjectTutorRepository projectTutorRepository;

    @Test
    @DisplayName("Test for get project tutor by tutor id and project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenTutorIdAndProjectId_whenFindByTutorIdAndProjectId_thenProjectTutorObject(){
        assertNotNull(projectTutorRepository.findByTutorIdAndProjectId(3L, 1L));
    }

    @Test
    @DisplayName("Test for get project tutor by tutor id and wrong project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenTutorIdAndProjectWrongId_whenFindByTutorIdAndProjectId_thenNull(){
        assertNull(projectTutorRepository.findByTutorIdAndProjectId(1L, 1234567L));
    }

    @Test
    @DisplayName("Test for get project tutor by wrong tutor id and project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenTutorWrongIdAndProjectId_whenFindByTutorIdAndProjectId_thenNull(){
        assertNull(projectTutorRepository.findByTutorIdAndProjectId(1324345543L, 1L));
    }

    @Test
    @DisplayName("Test for get project tutor list by tutor id and accepted")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenTutorIdAndAccepted_whenFindByTutorIdAndAcceptedIsTrue_thenTutorProjectList(){
        assertNotNull(projectTutorRepository.findByTutorIdAndAcceptedIsTrue(4L));
    }
    @Test
    @DisplayName("Test for get empty project tutor list by tutor id and accepted")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenTutorIdAndAccepted_whenFindByTutorIdAndAcceptedIsTrue_thenEmptyList(){
        assertTrue(projectTutorRepository.findByTutorIdAndAcceptedIsTrue(3L).isEmpty());
    }
}

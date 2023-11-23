package backend.siptis.model.repository.editors_and_reviewers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectSupervisorRepositoryTest {
    @Autowired
    private ProjectSupervisorRepository projectSupervisorRepository;

    @Test
    @DisplayName("Test for get project supervisor by supervisor id and project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndProjectId_whenFindBySupervisorIdAndProjectId_thenProjectSupervisorObject() {
        assertNotNull(projectSupervisorRepository.findBySupervisorIdAndProjectId(6L, 2L));
    }

    @Test
    @DisplayName("Test for get project supervisor by supervisor id and wrong project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndProjectWrongId_whenFindBySupervisorIdAndProjectId_thenNull() {
        assertNull(projectSupervisorRepository.findBySupervisorIdAndProjectId(1L, 1234567L));
    }

    @Test
    @DisplayName("Test for get project supervisor by wrong supervisor id and project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorWrongIdAndProjectId_whenFindBySupervisorIdAndProjectId_thenNull() {
        assertNull(projectSupervisorRepository.findBySupervisorIdAndProjectId(1324345543L, 1L));
    }

    @Test
    @DisplayName("Test for get project supervisor list by supervisor id and accepted")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsTrue_thenSupervisorProjectList() {
        assertNotNull(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(4L));
    }

    @Test
    @DisplayName("Test for get empty project supervisor list by supervisor id and accepted")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsTrue_thenEmptyList() {
        assertTrue(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(3L).isEmpty());
    }

    @Test
    @DisplayName("Test for get empty project supervisor list by supervisor id and accepted")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse_thenEmptyList() {
        assertTrue(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(3L).isEmpty());
    }

    @Test
    @DisplayName("Test for get empty project supervisor list by supervisor id and accepted")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue_thenEmptyList() {
        assertTrue(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(3L).isEmpty());
    }
}

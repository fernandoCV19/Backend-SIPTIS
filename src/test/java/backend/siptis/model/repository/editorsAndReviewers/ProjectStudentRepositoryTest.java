package backend.siptis.model.repository.editorsAndReviewers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class ProjectStudentRepositoryTest {
    @Autowired
    private ProjectStudentRepository projectStudentRepository;

    @Test
    @DisplayName("Test for get project student by student id and project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenStudentIdAndProjectId_whenFindByStudentIdAndProjectId_thenProjectStudentObject() {
        assertNotNull(projectStudentRepository.findByStudentIdAndProjectId(1L, 1L));
    }

    @Test
    @DisplayName("Test for get project student by student id and wrong project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenStudentIdAndProjectWrongId_whenFindByStudentIdAndProjectId_thenNull() {
        assertNull(projectStudentRepository.findByStudentIdAndProjectId(1L, 1234567L));
    }

    @Test
    @DisplayName("Test for get project student by wrong student id and project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenStudentWrongIdAndProjectId_whenFindByStudentIdAndProjectId_thenNull() {
        assertNull(projectStudentRepository.findByStudentIdAndProjectId(1324345543L, 1L));
    }
}

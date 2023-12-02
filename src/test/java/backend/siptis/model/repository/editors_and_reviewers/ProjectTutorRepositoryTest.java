package backend.siptis.model.repository.editors_and_reviewers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProjectTutorRepositoryTest {
    @Autowired
    private ProjectTutorRepository projectTutorRepository;

    @Test
    @DisplayName("Test for get project tutor by tutor id and project id")
    void givenTutorIdAndProjectId_whenFindByTutorIdAndProjectId_thenNull() {
        assertNull(projectTutorRepository.findByTutorIdAndProjectId(3L, 1L));
    }
    @Test
    @DisplayName("Test for get project tutor by tutor id and project id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenTutorIdAndProjectId_whenFindByTutorIdAndProjectId_thenProjectTutorObject() {
        assertNotNull(projectTutorRepository.findByTutorIdAndProjectId(131l, 101L));
    }
    @Test
    @DisplayName("Test for get project tutor by wrong tutor id and project id")
    void givenTutorWrongIdAndProjectId_whenFindByTutorIdAndProjectId_thenNull() {
        assertNull(projectTutorRepository.findByTutorIdAndProjectId(1324345543L, 101L));
    }

    @Test
    @DisplayName("Test for get project tutor list by tutor id and accepted")
    void givenTutorIdAndAccepted_whenFindByTutorIdAndAcceptedIsTrue_thenEmpty() {
        assertTrue(projectTutorRepository.findByTutorIdAndAcceptedIsTrue(4L).isEmpty());
    }
    @Test
    @DisplayName("Test for get project tutor list by tutor id and accepted success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenTutorIdAndAccepted_whenFindByTutorIdAndAcceptedIsTrue_thenTutorProjectList() {
        assertFalse(projectTutorRepository.findByTutorIdAndAcceptedIsTrue(131L).isEmpty());
    }

    @Test
    @DisplayName("Test for find by tutor id and accepted is false and reviewed is false ")
    void givenTutorIdAndAccepted_whenFindByTutorIdAndAcceptedIsFalseAndReviewedIsFalse_thenEmptyList() {
        assertTrue(projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(3L).isEmpty());
    }
    @Test
    @DisplayName("Test for find by tutor id and accepted is false and reviewed is false ")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenTutorIdAndAccepted_whenFindByTutorIdAndAcceptedIsFalseAndReviewedIsFalse_thenList() {
        assertFalse(projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(134L).isEmpty());
    }

    @Test
    @DisplayName("Test for find by tutor id and accepted is false and reviewed is true ")
    void givenTutorIdAndAccepted_whenFindByTutorIdAndAcceptedIsFalseAndReviewedIsTrue_thenEmptyList() {
        assertTrue(projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(3L).isEmpty());
    }
    @Test
    @DisplayName("Test for find by tutor id and accepted is false and reviewed is true ")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenTutorIdAndAccepted_whenFindByTutorIdAndAcceptedIsFalseAndReviewedIsTrue_thenList() {
        assertFalse(projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(132L).isEmpty());
    }
}

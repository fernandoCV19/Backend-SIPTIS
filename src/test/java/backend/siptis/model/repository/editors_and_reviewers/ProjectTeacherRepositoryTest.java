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
class ProjectTeacherRepositoryTest {
    @Autowired
    private ProjectTeacherRepository projectTeacherRepository;

    @Test
    @DisplayName("test for get list of project teacher find by teacher id not accepted and reviewed ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue_thenEmpty() {
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(123L).isEmpty());
    }

    @Test
    @DisplayName("test for get list of project teacher find by teacher id not accepted and reviewed success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadId_whenFindByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue_thenList() {
        assertFalse(projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(121L).isEmpty());
    }

    @Test
    @DisplayName("test for get list of project teacher find by teacher id not accepted and not reviewed ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse_thenEmpty() {
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(123L).isEmpty());
    }

    @Test
    @DisplayName("test for get list of project teacher find by teacher id not accepted and not reviewed success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadId_whenFindByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse_thenList() {
        assertFalse(projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(120L).isEmpty());
    }

    @Test
    @DisplayName("test for get list of project teacher find by teacher id  accepted  ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsTrue_thenEmpty() {
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(123L).isEmpty());
    }

    @Test
    @DisplayName("test for get list of project teacher find by teacher id  accepted success ")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadId_whenFindByTeacherIdAndAcceptedIsTrue_thenList() {
        assertFalse(projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(120L).isEmpty());
    }

    @Test
    @DisplayName("test for find by teacher id and project id")
    void givenBadIds_whenFindByTeacherIdAndProjectId_thenNull() {
        assertNull(projectTeacherRepository.findByTeacherIdAndProjectId(123L, 123L));
    }

    @Test
    @DisplayName("test for find by teacher id and project id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadIds_whenFindByTeacherIdAndProjectId_thenNotNull() {
        assertNotNull(projectTeacherRepository.findByTeacherIdAndProjectId(120L, 100L));
    }
}

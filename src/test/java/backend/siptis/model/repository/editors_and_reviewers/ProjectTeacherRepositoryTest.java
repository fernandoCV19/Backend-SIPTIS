package backend.siptis.model.repository.editors_and_reviewers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ProjectTeacherRepositoryTest {
    @Autowired
    private ProjectTeacherRepository projectTeacherRepository;

    @Test
    @DisplayName("test for get list of project teacher find by teacher id not accepted and reviewed ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue_thenEmpty() {
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(123L).isEmpty());
    }

    @Test
    @DisplayName("test for get list of project teacher find by teacher id not accepted and not reviewed ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse_thenEmpty() {
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(123L).isEmpty());
    }

    @Test
    @DisplayName("test for get list of project teacher find by teacher id  accepted  ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsTrue_thenEmpty() {
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(123L).isEmpty());
    }

    @Test
    @DisplayName("test for get list of project teacher find by teacher id  accepted  ")
    void givenBadIds_whenFindByTeacherIdAndProjectId_thenNull() {
        assertNull(projectTeacherRepository.findByTeacherIdAndProjectId(123L, 123L));
    }
}

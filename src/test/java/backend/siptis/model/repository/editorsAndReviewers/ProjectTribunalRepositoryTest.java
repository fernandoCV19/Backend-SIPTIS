package backend.siptis.model.repository.editorsAndReviewers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ProjectTribunalRepositoryTest {
    @Autowired
    private ProjectTribunalRepository projectTribunalRepository;

    @Test
    @DisplayName("test find by tribunal id and accepted is false and reviewed is false")
    void givenIdWhenFindByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(123456L).isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and accepted is false and reviewed is true")
    void givenIdWhenFindByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(123456L).isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and accepted is false and reviewed is true")
    void givenIdWhenFindByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(123456L).isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and defense points is not null")
    void givenIdWhenFindByTribunalIdAndDefensePointsIsNotNullThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(123456L).isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and project id")
    void givenIdWhenFindByProject_IdAndTribunal_IdThenIsNull() {
        assertNull(projectTribunalRepository.findByProject_IdAndTribunal_Id(123456L, 123456L));
    }

    @Test
    @DisplayName("test find by tribunal id and project phase and defense points is null")
    void givenIdWhenFindByTribunal_IdAndProject_PhaseAndDefensePointsNullThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunal_IdAndProject_PhaseAndDefensePointsNull(123456L, "").isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and project phase and defense points is  not null")
    void givenIdWhenFindByTribunal_IdAndProject_PhaseAndDefensePointsNotNullThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunal_IdAndProject_PhaseAndDefensePointsNotNull(123456L, "").isEmpty());
    }

    @Test
    @DisplayName("test find by project id")
    void givenIdWhenFindByProjectIdThenIsNull() {
        assertNull(projectTribunalRepository.findByProjectId(123456L));
    }
}

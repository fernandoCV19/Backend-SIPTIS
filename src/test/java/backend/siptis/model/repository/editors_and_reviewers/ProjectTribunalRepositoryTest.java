package backend.siptis.model.repository.editors_and_reviewers;

import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProjectTribunalRepositoryTest {
    @Autowired
    private ProjectTribunalRepository projectTribunalRepository;

    private void updateProjectTribunal(){
        ProjectTribunal projectTribunal = projectTribunalRepository.findById(127L).get();
        projectTribunal.setDefensePoints(60.0);
        projectTribunalRepository.save(projectTribunal);
    }
    @Test
    @DisplayName("test find by tribunal id and accepted is false and reviewed is false empty")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(123456L).isEmpty());
    }
    @Test
    @DisplayName("test find by tribunal id and accepted is false and reviewed is false success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByTribunalIdAndAcceptedIsFalseAndReviewedIsFalseThenList() {
        assertFalse(projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(150L).isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and accepted is false and reviewed is true")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(123456L).isEmpty());
    }
    @Test
    @DisplayName("test find by tribunal id and accepted is false and reviewed is true success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByTribunalIdAndAcceptedIsFalseAndReviewedIsTrueThenList() {
        assertFalse(projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(155L).isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and accepted is true and defense points is null")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(123456L).isEmpty());
    }
    @Test
    @DisplayName("test find by tribunal id and accepted is true and defense points is null success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNullThenList() {
        assertFalse(projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(151L).isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and defense points is not null")
    void givenIdWhenFindByTribunalIdAndDefensePointsIsNotNullThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(123456L).isEmpty());
    }
    @Test
    @DisplayName("test find by tribunal id and defense points is not null success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByTribunalIdAndDefensePointsIsNotNullThenList() {
        updateProjectTribunal();
        assertFalse(projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(156L).isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and project id")
    void givenIdWhenFindByProject_IdAndTribunal_IdThenIsNull() {
        assertNull(projectTribunalRepository.findByProject_IdAndTribunal_Id(123456L, 123456L));
    }
    @Test
    @DisplayName("test find by tribunal id and project id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByProject_IdAndTribunal_IdThenList() {
        assertNotNull(projectTribunalRepository.findByProject_IdAndTribunal_Id(103L, 150L));
    }

    @Test
    @DisplayName("test find by tribunal id and project phase and defense points is null")
    void givenIdWhenFindByTribunal_IdAndProject_PhaseAndDefensePointsNullThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunal_IdAndProject_PhaseAndDefensePointsNull(123456L, "").isEmpty());
    }
    @Test
    @DisplayName("test find by tribunal id and project phase and defense points is null success")
    void givenIdWhenFindByTribunal_IdAndProject_PhaseAndDefensePointsNullThenList() {
        assertTrue(projectTribunalRepository.findByTribunal_IdAndProject_PhaseAndDefensePointsNull(174L, "PRE_DEFENSE_PHASE").isEmpty());
    }

    @Test
    @DisplayName("test find by tribunal id and project phase and defense points is  not null")
    void givenIdWhenFindByTribunal_IdAndProject_PhaseAndDefensePointsNotNullThenIsEmpty() {
        assertTrue(projectTribunalRepository.findByTribunal_IdAndProject_PhaseAndDefensePointsNotNull(123456L, "").isEmpty());
    }
    @Test
    @DisplayName("test find by tribunal id and project phase and defense points is  not null success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByTribunal_IdAndProject_PhaseAndDefensePointsNotNullThenList() {
        updateProjectTribunal();
        assertFalse(projectTribunalRepository.findByTribunal_IdAndProject_PhaseAndDefensePointsNotNull(156L, "DEFENSE_PHASE").isEmpty());
    }

    @Test
    @DisplayName("test find by project id")
    void givenIdWhenFindByProjectIdThenIsNull() {
        assertNull(projectTribunalRepository.findByProjectId(123456L));
    }
    @Test
    @DisplayName("test find by project id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByProjectIdThenIsNotNull() {
        assertNotNull(projectTribunalRepository.findByProjectId(102L));
    }
}

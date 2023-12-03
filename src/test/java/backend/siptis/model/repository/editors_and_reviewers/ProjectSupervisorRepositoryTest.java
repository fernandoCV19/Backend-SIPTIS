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
class ProjectSupervisorRepositoryTest {
    @Autowired
    private ProjectSupervisorRepository projectSupervisorRepository;

    @Test
    @DisplayName("Test for get project supervisor by supervisor id and project id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndProjectId_whenFindBySupervisorIdAndProjectId_thenProjectSupervisorObject() {
        assertNotNull(projectSupervisorRepository.findBySupervisorIdAndProjectId(140L, 102L));
    }

    @Test
    @DisplayName("Test for get project supervisor by supervisor id and wrong project id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndProjectWrongId_whenFindBySupervisorIdAndProjectId_thenNull() {
        assertNull(projectSupervisorRepository.findBySupervisorIdAndProjectId(140L, 1234567L));
    }

    @Test
    @DisplayName("Test for get project supervisor by wrong supervisor id and project id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorWrongIdAndProjectId_whenFindBySupervisorIdAndProjectId_thenNull() {
        assertNull(projectSupervisorRepository.findBySupervisorIdAndProjectId(1324345543L, 102L));
    }

    @Test
    @DisplayName("Test for get project supervisor list by supervisor id and accepted")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsTrue_thenSupervisorProjectList() {
        assertNotNull(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(140L));
    }

    @Test
    @DisplayName("Test for get empty project supervisor list by supervisor id and accepted")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsTrue_thenEmptyList() {
        assertTrue(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(141L).isEmpty());
    }

    @Test
    @DisplayName("Test for get empty project supervisor list by supervisor id no accepted and no reviewed success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse_thenTrue() {
        assertFalse(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(145L).isEmpty());
    }

    @Test
    @DisplayName("Test for get empty project supervisor list by supervisor id no accepted and no reviewed empty")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse_thenEmptyList() {
        assertTrue(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(144L).isEmpty());
    }


    @Test
    @DisplayName("Test for get empty project supervisor list by supervisor id and accepted success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue_thenList() {
        assertFalse(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(141L).isEmpty());
    }

    @Test
    @DisplayName("Test for get empty project supervisor list by supervisor id and accepted empty")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSupervisorIdAndAccepted_whenFindBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue_thenEmptyList() {
        assertTrue(projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(142L).isEmpty());
    }
}

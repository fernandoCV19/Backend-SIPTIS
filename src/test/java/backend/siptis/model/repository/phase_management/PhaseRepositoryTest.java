package backend.siptis.model.repository.phase_management;

import backend.siptis.model.entity.phase_management.Phase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PhaseRepositoryTest {
    @Autowired
    private PhaseRepository phaseRepository;

    @Test
    @DisplayName("Test for empty list of phases by id")
    public void givenPhasesAndWrongId_thenFindByModality_IdOrderByNumberPhaseAsc_thenEmptyPhasesList() {
        List<Phase> list = phaseRepository.findByModality_IdOrderByNumberPhaseAsc(13L);
        assertTrue(list.isEmpty());
    }
    @Test
    @DisplayName("Test for empty list of phases by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenPhasesAndWrongId_thenFindByModality_IdOrderByNumberPhaseAsc_thenPhasesList() {
        List<Phase> list = phaseRepository.findByModality_IdOrderByNumberPhaseAsc(101L);
        assertFalse(list.isEmpty());
    }
}

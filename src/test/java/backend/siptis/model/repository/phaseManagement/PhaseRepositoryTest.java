package backend.siptis.model.repository.phaseManagement;

import backend.siptis.model.entity.phaseManagement.Phase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PhaseRepositoryTest {
    @Autowired
    private PhaseRepository phaseRepository;

    @Test
    @DisplayName("Test for list of phases by id ")
    @Sql(scripts = {"/custom_imports/phases.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenPhases_thenFindByModality_IdOrderByNumberPhaseAsc_thenPhasesList(){
        List<Phase> list = phaseRepository.findByModality_IdOrderByNumberPhaseAsc(1L);
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Test for empty list of phases by id")
    public void givenPhasesAndWrongId_thenFindByModality_IdOrderByNumberPhaseAsc_thenPhasesList(){
        List<Phase> list = phaseRepository.findByModality_IdOrderByNumberPhaseAsc(13L);
        assertTrue(list.isEmpty());
    }
}

package backend.siptis.model.repository.phase_management;

import backend.siptis.model.entity.phase_management.Phase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PhaseRepositoryTest {
    @Autowired
    private PhaseRepository phaseRepository;

    @Test
    @DisplayName("Test for empty list of phases by id")
    public void givenPhasesAndWrongId_thenFindByModality_IdOrderByNumberPhaseAsc_thenPhasesList() {
        List<Phase> list = phaseRepository.findByModality_IdOrderByNumberPhaseAsc(13L);
        assertTrue(list.isEmpty());
    }
}

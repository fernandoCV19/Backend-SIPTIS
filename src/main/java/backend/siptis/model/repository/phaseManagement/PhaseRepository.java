package backend.siptis.model.repository.phaseManagement;

import backend.siptis.model.entity.phaseManagement.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhaseRepository extends JpaRepository<Phase, Long> {

    List<Phase> findByModality_IdOrderByNumberPhaseAsc(@Param("modalityId") Long idModality);

}

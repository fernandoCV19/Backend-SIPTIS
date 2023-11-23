package backend.siptis.model.repository.phase_management;

import backend.siptis.model.entity.phase_management.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhaseRepository extends JpaRepository<Phase, Long> {

    List<Phase> findByModality_IdOrderByNumberPhaseAsc(@Param("modalityId") Long idModality);

}

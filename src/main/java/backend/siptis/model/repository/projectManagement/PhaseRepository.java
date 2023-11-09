package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Phase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhaseRepository extends JpaRepository<Phase, Long> {

    @Query(value = "SELECT * FROM Phase p WHERE p.modality_id = :modalityId ORDER BY p.number_phase", nativeQuery = true)
    List<Phase> findAllByModalityId(@Param("modalityId") Long idModality);
}

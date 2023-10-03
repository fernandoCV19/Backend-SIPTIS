package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
    List<Phase> findAllByModalityId(Long idModality);
}

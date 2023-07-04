package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseRepository extends JpaRepository<Phase, Long> {
}

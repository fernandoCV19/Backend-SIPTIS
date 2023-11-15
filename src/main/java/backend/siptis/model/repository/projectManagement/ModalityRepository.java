package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Modality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalityRepository extends JpaRepository<Modality, Long> {

    boolean existsById(Long id);

    Modality findModalityById(Long id);
}

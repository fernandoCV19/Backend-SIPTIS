package backend.siptis.model.repository.project_management;

import backend.siptis.model.entity.project_management.Modality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModalityRepository extends JpaRepository<Modality, Long> {

    boolean existsById(Long id);

    Modality findModalityById(Long id);
}

package backend.siptis.model.repository.projectManagement;

import java.util.Optional;

import backend.siptis.model.entity.projectManagement.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    Optional<Presentation> findById(long id);
    Optional<Presentation> findTopByProjectIdAndReviewed(long idProyecto , boolean revisado);
    Optional<Presentation> findByProjectIdAndReviewed(long idProyecto , boolean revisado);

    @Override
    void deleteById(Long aLong);

    Presentation findTopByProjectIdOrderByDateDesc(Long idProject);

}

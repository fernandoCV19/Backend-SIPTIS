package backend.siptis.model.repository.projectManagement;

import java.util.Optional;

import backend.siptis.model.entity.projectManagement.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    Presentation findById(int id);
    Optional<Presentation> findByProjectIdAndReviewed(long idProyecto , boolean revisado);

    @Override
    void deleteById(Long aLong);

    Presentation findTopByProjectIdOrderByDateDesc(Long id);

}

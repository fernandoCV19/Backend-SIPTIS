package backend.siptis.model.repository.presentations;

import backend.siptis.model.entity.presentations.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    Optional<Presentation> findById(long id);

    Optional<Presentation> findTopByProjectIdAndReviewed(long projectId, boolean reviewed);

    Optional<Presentation> findByProjectIdAndReviewed(long projectId, boolean reviewed);

    @Override
    void deleteById(Long aLong);

    Presentation findTopByProjectIdOrderByDateDesc(Long idProject);

}

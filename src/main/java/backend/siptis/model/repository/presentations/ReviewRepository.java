package backend.siptis.model.repository.presentations;

import backend.siptis.model.entity.presentations.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(Long idProject, Long idUser);
}

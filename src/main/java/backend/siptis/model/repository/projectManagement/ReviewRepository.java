package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(Long idProject, Long idUser);
}

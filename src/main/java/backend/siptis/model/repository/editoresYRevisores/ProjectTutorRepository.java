package backend.siptis.model.repository.editoresYRevisores;

import backend.siptis.model.entity.editoresYRevisores.ProjectTutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTutorRepository extends JpaRepository<ProjectTutor, Long> {

    List<ProjectTutor> findByTutorAndAcceptedIsFalseAndReviewedIsTrue(Long id);

    List<ProjectTutor> findByTutorAndAcceptedIsFalseAndReviewedIsFalse(Long id);

    List<ProjectTutor> findByTutorAndAcceptedIsTrue(Long id);
}

package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTutorRepository extends JpaRepository<ProjectTutor, Long> {

    List<ProjectTutor> findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(Long id);

    List<ProjectTutor> findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(Long id);

    List<ProjectTutor> findByTutorIdAndAcceptedIsTrue(Long id);

    ProjectTutor findByTutorIdAndProjectId(Long tutorId, Long projectId);
}

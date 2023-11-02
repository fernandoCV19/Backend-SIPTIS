package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStudentRepository extends JpaRepository<ProjectStudent, Long> {

    ProjectStudent findByStudentIdAndProjectId(Long studentId, Long projectId);
}

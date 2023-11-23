package backend.siptis.model.repository.editors_and_reviewers;

import backend.siptis.model.entity.editors_and_reviewers.ProjectStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStudentRepository extends JpaRepository<ProjectStudent, Long> {

    ProjectStudent findByStudentIdAndProjectId(Long studentId, Long projectId);
}

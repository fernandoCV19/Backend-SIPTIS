package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTeacherRepository extends JpaRepository<ProjectTeacher, Long> {

    List<ProjectTeacher> findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(Long id);

    List<ProjectTeacher> findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(Long id);

    List<ProjectTeacher> findByTeacherIdAndAcceptedIsTrue(Long id);

    ProjectTeacher findByTeacherIdAndProjectId(Long teacherID, Long projectId);
}

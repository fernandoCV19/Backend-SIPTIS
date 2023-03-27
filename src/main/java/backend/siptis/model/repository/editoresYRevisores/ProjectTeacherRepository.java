package backend.siptis.model.repository.editoresYRevisores;

import backend.siptis.model.entity.editoresYRevisores.ProjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTeacherRepository extends JpaRepository<ProjectTeacher, Long> {

    List<ProjectTeacher> findByTeacherAndAcceptedIsFalseAndReviewedIsTrue(Long id);

    List<ProjectTeacher> findByTeacherAndAcceptedIsFalseAndReviewedIsFalse(Long id);

    List<ProjectTeacher> findByTeacherAndAcceptedIsTrue(Long id);
}

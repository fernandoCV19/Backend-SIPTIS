package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTeacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProjectSupervisorRepository extends JpaRepository<ProjectSupervisor, Long> {

    List<ProjectSupervisor> findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(Long id);

    List<ProjectSupervisor> findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(Long id);

    List<ProjectSupervisor> findBySupervisorIdAndAcceptedIsTrue(Long id);

    ProjectSupervisor findBySupervisorIdAndProjectId(Long supervisorId, Long projectId);
}

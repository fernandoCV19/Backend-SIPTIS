package backend.siptis.model.repository.editors_and_reviewers;

import backend.siptis.model.entity.editors_and_reviewers.ProjectSupervisor;
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

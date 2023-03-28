package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectSupervisorRepository extends JpaRepository<ProjectSupervisor, Long> {

    List<ProjectSupervisor> findBySupervisorAndAcceptedIsFalseAndReviewedIsTrue(Long id);

    List<ProjectSupervisor> findBySupervisorAndAcceptedIsFalseAndReviewedIsFalse(Long id);

    List<ProjectSupervisor> findBySupervisorAndAcceptedIsTrue(Long id);
}

package backend.siptis.model.repository.editorsAndReviewers;

import backend.siptis.model.entity.editorsAndReviewers.ProjectSupervisor;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTribunal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTribunalRepository extends JpaRepository<ProjectTribunal, Long> {

    List<ProjectTribunal> findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(Long id);

    List<ProjectTribunal> findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(Long id);

    List<ProjectTribunal> findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(Long id);

    List<ProjectTribunal> findByTribunalIdAndDefensePointsIsNotNull(Long id);

    ProjectTribunal findByTribunalIdAndProjectId(Long tribunalId, Long projectId);
}

package backend.siptis.model.repository.editoresYRevisores;

import backend.siptis.model.entity.editoresYRevisores.ProjectTribunal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTribunalRepository extends JpaRepository<ProjectTribunal, Long> {

    List<ProjectTribunal> findByTribunalAndAcceptedIsFalseAndReviewedIsFalse(Long id);

    List<ProjectTribunal> findByTribunalAndAcceptedIsFalseAndReviewedIsTrue(Long id);

    List<ProjectTribunal> findByTribunalAndAcceptedIsTrueAndDefensePointsIsNull(Long id);

    List<ProjectTribunal> findByTribunalAndDefensePointsIsNotNull(Long id);
}

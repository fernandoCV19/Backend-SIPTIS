package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Defense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefenseRepository extends JpaRepository<Defense, Long> {
    List<Defense> findByplaceToDefenseId(Long idPlace);
}

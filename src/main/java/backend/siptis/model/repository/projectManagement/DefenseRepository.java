package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Defense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefenseRepository extends JpaRepository<Defense, Long> {
    List<Defense> findByplaceToDefenseId(Long idPlace);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Defense t WHERE t.id = :id", nativeQuery = true)
    void deleteADefense(@Param("id") Long id);
}

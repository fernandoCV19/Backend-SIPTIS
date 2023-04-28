package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.PlaceToDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceToDefenseRepository extends JpaRepository<PlaceToDefense, Long> {
}
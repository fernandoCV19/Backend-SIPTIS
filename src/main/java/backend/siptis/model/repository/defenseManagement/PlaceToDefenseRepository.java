package backend.siptis.model.repository.defenseManagement;

import backend.siptis.model.entity.defenseManagement.PlaceToDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceToDefenseRepository extends JpaRepository<PlaceToDefense, Long> {

}
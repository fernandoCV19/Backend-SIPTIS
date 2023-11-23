package backend.siptis.model.repository.defense_management;

import backend.siptis.model.entity.defense_management.PlaceToDefense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceToDefenseRepository extends JpaRepository<PlaceToDefense, Long> {

}
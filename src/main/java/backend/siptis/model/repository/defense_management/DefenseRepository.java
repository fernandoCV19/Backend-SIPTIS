package backend.siptis.model.repository.defense_management;

import backend.siptis.model.entity.defense_management.Defense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefenseRepository extends JpaRepository<Defense, Long> {

}

package backend.siptis.model.repository.defenseManagement;

import backend.siptis.model.entity.defenseManagement.Defense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefenseRepository extends JpaRepository<Defense, Long> {

}

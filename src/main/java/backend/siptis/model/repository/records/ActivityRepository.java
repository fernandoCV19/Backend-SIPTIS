package backend.siptis.model.repository.records;

import backend.siptis.model.entity.records.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}

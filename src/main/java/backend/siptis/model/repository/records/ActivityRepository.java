package backend.siptis.model.repository.records;

import backend.siptis.model.entity.records.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByProjectId(Long id);
}

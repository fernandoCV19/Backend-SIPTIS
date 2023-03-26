package backend.siptis.model.repository.records;

import backend.siptis.model.entity.records.GeneralActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralActivityRepository extends JpaRepository<GeneralActivity, Long> {
}

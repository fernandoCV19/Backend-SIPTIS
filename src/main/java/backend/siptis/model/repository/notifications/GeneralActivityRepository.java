package backend.siptis.model.repository.notifications;

import backend.siptis.model.entity.notifications.GeneralActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralActivityRepository extends JpaRepository<GeneralActivity, Long> {
}

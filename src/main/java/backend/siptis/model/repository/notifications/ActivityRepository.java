package backend.siptis.model.repository.notifications;

import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.pjo.vo.ActivityVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByProjectId(Long id);

    Page<ActivityVO> findByProjectId(Long id, Pageable pageable);
}

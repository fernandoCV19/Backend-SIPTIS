package backend.siptis.service.notifications;


import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.pjo.dto.notifications.ActivityDTO;
import backend.siptis.model.pjo.vo.ActivityVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActivityService {
    ServiceAnswer findById(long id);

    ServiceAnswer persistActivity(ActivityDTO ActivityDTO);

    List<ActivityVO> findAllVO();

    Page<ActivityVO> findAllVO(Pageable pageable);

    ServiceAnswer update(ActivityDTO activityDTO, long id);

    ServiceAnswer delete(long id);

    ActivityVO entityToVO(Activity activity);

    List<Activity> findByProjectId(Long id);

    Page<ActivityVO> findByProjectId(Long id, Pageable pageable);
}

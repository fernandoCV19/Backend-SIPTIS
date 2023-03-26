package backend.siptis.service.records;


import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.pjo.dto.records.ActivityDTO;
import backend.siptis.model.pjo.vo.ActivityVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    Optional<Activity> findById(long id);
    ActivityVO persistActivity(ActivityDTO ActivityDTO);
    List<ActivityVO> findAllVO();
    Page<ActivityVO> findAllVO(Pageable pageable);
    void update(ActivityDTO activityDTO, long id);
    void delete(long id);
    ActivityVO entityToVO(Activity activity);
}

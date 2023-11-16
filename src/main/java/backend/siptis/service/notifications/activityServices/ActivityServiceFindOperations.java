package backend.siptis.service.notifications.activityServices;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.pjo.vo.ActivityVO;
import backend.siptis.model.repository.notifications.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActivityServiceFindOperations {

    private final ActivityRepository activityRepository;

    public ServiceAnswer findById(long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);

        if (activityOptional.isPresent()) {
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(activityOptional.get()))
                    .build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .build();
    }

    public List<ActivityVO> findAllVO() {
        List<Activity> activitiesList = activityRepository.findAll();
        ArrayList<ActivityVO> activityArrayList = new ArrayList<>();

        for (Activity activity : activitiesList) {
            activityArrayList.add(entityToVO(activity));
        }
        return activityArrayList;
    }

    public Page<ActivityVO> findAllVO(Pageable pageable) {
        Page<Activity> activityList = activityRepository.findAll(pageable);
        return activityList.map(this::entityToVO);
    }

    public ActivityVO entityToVO(Activity activity) {
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        return activityVO;
    }
}

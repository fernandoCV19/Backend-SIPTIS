package backend.siptis.service.records;

import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.entity.records.GeneralActivity;
import backend.siptis.model.pjo.dto.records.ActivityDTO;
import backend.siptis.model.pjo.vo.ActivityVO;
import backend.siptis.model.repository.records.ActivityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService{

    public final ActivityRepository acitivityRepository;
    @Autowired
    public ActivityServiceImpl(ActivityRepository acitivityRepository) {
        this.acitivityRepository = acitivityRepository;
    }
    @Override
    public Optional<Activity> findById(long id) {
        return acitivityRepository.findById(id);
    }

    @Override
    public ActivityVO persistActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setActivityDescription(activityDTO.getActivityDescription());
        activity.setActivityDate(activityDTO.getActivityDate());
        activity =  acitivityRepository.save(activity);
        return entityToVO(activity);
    }

    @Override
    public List<ActivityVO> findAllVO() {
        List<Activity> activitiesList = acitivityRepository.findAll();
        ArrayList<ActivityVO> activityArrayList = new ArrayList<>();
        for (Activity activity : activitiesList){
            activityArrayList.add(entityToVO(activity));
        }
        return activityArrayList;
    }

    @Override
    public Page<ActivityVO> findAllVO(Pageable pageable) {
        Page<Activity> activityList = acitivityRepository.findAll(pageable);
        return activityList.map(this::entityToVO);
    }

    @Override
    public void update(ActivityDTO activityDTO, long id) {
        Optional <Activity> optionalActivity = acitivityRepository.findById(id);
        if(!optionalActivity.isEmpty()){
            Activity activity = optionalActivity.get();
            activity.setActivityDescription(activityDTO.getActivityDescription());
            activity.setActivityDate(activityDTO.getActivityDate());
            acitivityRepository.save(activity);
        }
    }

    @Override
    public void delete(long id) {
        Optional <Activity> optionalActivity = acitivityRepository.findById(id);
        if(!optionalActivity.isEmpty()){
            acitivityRepository.deleteById(id);
        }
    }

    @Override
    public ActivityVO entityToVO(Activity activity) {
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        return activityVO;
    }
}

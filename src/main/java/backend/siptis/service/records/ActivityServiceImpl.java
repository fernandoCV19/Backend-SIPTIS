package backend.siptis.service.records;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.entity.records.GeneralActivity;
import backend.siptis.model.pjo.dto.records.ActivityDTO;
import backend.siptis.model.pjo.vo.ActivityVO;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
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
    private final ProjectRepository projectRepository;
    @Autowired
    public ActivityServiceImpl(ActivityRepository acitivityRepository, ProjectRepository projectRepository) {
        this.acitivityRepository = acitivityRepository;
        this.projectRepository = projectRepository;
    }
    @Override
    public Optional<Activity> findById(long id) {
        return acitivityRepository.findById(id);
    }

    @Override
    public ServiceAnswer persistActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        Optional<Project> project = projectRepository.findById(activityDTO.getIdProject());

        if (!project.isEmpty()){
            activity.setActivityDescription(activityDTO.getActivityDescription());
            activity.setActivityDate(activityDTO.getActivityDate());
            activity =  acitivityRepository.save(activity);

            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(activity)).build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .data(null).build();
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
    public ServiceAnswer update(ActivityDTO activityDTO, long id) {
        Optional <Activity> optionalActivity = acitivityRepository.findById(id);

        if(!optionalActivity.isEmpty()){
            Activity activity = optionalActivity.get();
            activity.setActivityDescription(activityDTO.getActivityDescription());
            activity.setActivityDate(activityDTO.getActivityDate());
            activity = acitivityRepository.save(activity);

            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(activity)).build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .data(null).build();
    }

    @Override
    public ServiceAnswer delete(long id) {
        Optional <Activity> optionalActivity = acitivityRepository.findById(id);

        if(!optionalActivity.isEmpty()){
            acitivityRepository.deleteById(id);
            optionalActivity = acitivityRepository.findById(id);
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(optionalActivity.get())).build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .data(null).build();
    }

    @Override
    public ActivityVO entityToVO(Activity activity) {
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        return activityVO;
    }
}

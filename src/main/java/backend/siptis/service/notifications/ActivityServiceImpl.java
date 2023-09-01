package backend.siptis.service.notifications;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.pjo.dto.notifications.ActivityDTO;
import backend.siptis.model.pjo.vo.ActivityVO;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.notifications.ActivityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService{

    public final ActivityRepository activityRepository;
    private final ProjectRepository projectRepository;
    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, ProjectRepository projectRepository) {
        this.activityRepository = activityRepository;
        this.projectRepository = projectRepository;
    }
    @Override
    public ServiceAnswer findById(long id) {
        Optional<Activity> activityOptional = activityRepository.findById(id);

        if(!activityOptional.isEmpty()){
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(activityOptional.get()))
                    .build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .build();
    }

    @Override
    public ServiceAnswer persistActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        Optional<Project> project = projectRepository.findById(activityDTO.getIdProject());

        if (!project.isEmpty()){
            activity.setActivityDescription(activityDTO.getActivityDescription());
            activity.setActivityDate(activityDTO.getActivityDate());
            activity.setActivityName(activityDTO.getActivityName());
            activity.setProject(project.get());
            activity =  activityRepository.save(activity);

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
        List<Activity> activitiesList = activityRepository.findAll();
        ArrayList<ActivityVO> activityArrayList = new ArrayList<>();

        for (Activity activity : activitiesList){
            activityArrayList.add(entityToVO(activity));
        }
        return activityArrayList;
    }

    @Override
    public Page<ActivityVO> findAllVO(Pageable pageable) {
        Page<Activity> activityList = activityRepository.findAll(pageable);
        return activityList.map(this::entityToVO);
    }

    @Override
    public ServiceAnswer update(ActivityDTO activityDTO, long id) {
        Optional <Activity> optionalActivity = activityRepository.findById(id);

        if(!optionalActivity.isEmpty()){
            Activity activity = optionalActivity.get();
            activity.setActivityDescription(activityDTO.getActivityDescription());
            activity.setActivityDate(activityDTO.getActivityDate());
            activity = activityRepository.save(activity);

            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(activity ).build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .data(null).build();
    }

    @Override
    public ServiceAnswer delete(long id) {
        Optional <Activity> optionalActivity = activityRepository.findById(id);

        if(!optionalActivity.isEmpty()){
            activityRepository.deleteById(id);
            optionalActivity = activityRepository.findById(id);
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(null).build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .data(null).build();
    }
    @Override
    public List<Activity> findByProjectId(Long id) {
        return activityRepository.findByProjectId(id);
    }

    @Override
    public ActivityVO entityToVO(Activity activity) {
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        System.out.print(activityVO);
        return activityVO;
    }
}

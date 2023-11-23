package backend.siptis.service.notifications.activity_services;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.pjo.dto.notifications.ActivityDTO;
import backend.siptis.model.pjo.vo.ActivityVO;
import backend.siptis.model.repository.notifications.ActivityRepository;
import backend.siptis.model.repository.project_management.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ActivityServiceModifyOperations {

    private final ActivityRepository activityRepository;
    private final ProjectRepository projectRepository;

    public ServiceAnswer persistActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        Optional<Project> project = projectRepository.findById(activityDTO.getIdProject());

        if (project.isPresent()) {
            activity.setActivityDescription(activityDTO.getActivityDescription());
            activity.setActivityDate(activityDTO.getActivityDate());
            activity.setActivityName(activityDTO.getActivityName());
            activity.setProject(project.get());
            activity = activityRepository.save(activity);

            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(entityToVO(activity)).build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .data(null).build();
    }


    public ServiceAnswer update(ActivityDTO activityDTO, long id) {
        Optional<Activity> optionalActivity = activityRepository.findById(id);

        if (!optionalActivity.isEmpty()) {
            Activity activity = optionalActivity.get();
            activity.setActivityDescription(activityDTO.getActivityDescription());
            activity.setActivityDate(activityDTO.getActivityDate());
            activity = activityRepository.save(activity);

            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(activity).build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .data(null).build();
    }

    public ServiceAnswer delete(long id) {
        Optional<Activity> optionalActivity = activityRepository.findById(id);

        if (optionalActivity.isPresent()) {
            activityRepository.deleteById(id);
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.OK)
                    .data(null).build();
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.NOT_FOUND)
                .data(null).build();
    }

    public ActivityVO entityToVO(Activity activity) {
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        return activityVO;
    }
}

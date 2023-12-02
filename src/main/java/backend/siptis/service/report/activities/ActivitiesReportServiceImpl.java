package backend.siptis.service.report.activities;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.entity.notifications.GeneralActivity;
import backend.siptis.model.repository.notifications.ActivityRepository;
import backend.siptis.model.repository.notifications.GeneralActivityRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.activities.generation_tools.ActivitiesByProjectReportTool;
import backend.siptis.service.report.activities.generation_tools.GeneralActivityReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivitiesReportServiceImpl implements ActivitiesReportService{

    private final GeneralActivityRepository generalActivityRepository;
    private final ActivityRepository activityRepository;
    private final CloudManagementService cloud;
    @Override
    public ServiceAnswer getGeneralActivitesReport() {
        List<GeneralActivity> activityList = generalActivityRepository.findAll();
        activityList = activityList.stream()
                .sorted((a1, a2) -> a1.getActivityDate()
                        .compareTo(a2.getActivityDate())).collect(Collectors.toList());
        String fileName = GeneralActivityReportTool.generateReport(activityList);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getActivitiesByProject(){
        List<Activity> activityList = activityRepository.findAll();
        activityList = activityList.stream()
                .sorted((a1, a2) -> a1.getActivityDate()
                        .compareTo(a2.getActivityDate())).collect(Collectors.toList());

        activityList = activityList.stream()
                .sorted((a1, a2) -> a1.getProject().getName()
                        .compareTo(a2.getProject().getName())).collect(Collectors.toList());

        String fileName = ActivitiesByProjectReportTool.generateReport(activityList);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }
}

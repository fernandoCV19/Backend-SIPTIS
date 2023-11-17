package backend.siptis.service.notifications.notificationSenders;

import backend.siptis.model.entity.editorsAndReviewers.ProjectStudent;
import backend.siptis.model.pjo.vo.ActivityVO;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.notifications.EmailFactory;
import backend.siptis.service.notifications.activityServices.ActivityServiceFindOperations;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalActivityNotificationSenderManagerServiceImpl implements PersonalActivityNotificationSenderManagerService {
    private final ActivityServiceFindOperations activityServiceFindOperations;
    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;
    private final SendActivityNotificationServiceImpl sender;
    private final EmailFactory emailFactory;

    @Override
    @Scheduled(cron = "0 0 8 * * *")
    public void sendPersonalActivities() throws MessagingException, IOException {
        int actualMonth = LocalDateTime.now().getMonthValue();
        int actualDay = LocalDateTime.now().getDayOfMonth();

        List<ActivityVO> activityVOS = activityServiceFindOperations.findAllVO();
        for (ActivityVO vo : activityVOS) {
            int activityMonth = vo.getActivityDate().getMonth() + 1;
            int activityDay = vo.getActivityDate().getDay();
            if (activityMonth == actualMonth && (actualDay == activityDay - 1 || actualDay == activityDay)) {
                sender.sendNotification(vo.getActivityName(), getEmails(vo), vo.getActivityDate().toString());
            }
        }
    }

    private String[] getEmails(ActivityVO vo) {
        List<ProjectStudent> students = (List<ProjectStudent>) vo.getProject().getStudents();
        String[] emails = new String[students.size()];
        for (int i = 0; i < students.size(); i++) {
            emails[i] = students.get(i).getStudent().getEmail();
        }
        return emails;
    }
}

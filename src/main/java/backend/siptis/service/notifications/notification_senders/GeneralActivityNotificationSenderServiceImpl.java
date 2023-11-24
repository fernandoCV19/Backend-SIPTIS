package backend.siptis.service.notifications.notification_senders;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.notifications.EmailFactory;
import backend.siptis.service.notifications.general_activity_services.GeneralActivityServiceFindOperations;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class GeneralActivityNotificationSenderServiceImpl implements GeneralActivityNotificationSenderService {
    private final GeneralActivityServiceFindOperations generalActivityServiceFindOperations;
    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;
    private final SendActivityNotificationServiceImpl sender;
    private final EmailFactory emailFactory;

    @Override
    @Scheduled(cron = "0 0 8 * * *")
    public void sendGeneralActivities() throws MessagingException, IOException {
        int actualMonth = LocalDateTime.now().getMonthValue();
        int actualDay = LocalDateTime.now().getDayOfMonth();

        List<GeneralActivityVO> generalActivityVOList = generalActivityServiceFindOperations.findAllVO();
        List<SiptisUser> siptisUserList = (List) siptisUserServiceGeneralUserOperations.getAllUsers().getData();

        for (GeneralActivityVO vo : generalActivityVOList) {
            int activityMonth = vo.getActivityDate().getMonthValue();
            int activityDay = vo.getActivityDate().getDayOfMonth();
            if (activityMonth == actualMonth && (actualDay == activityDay - 1 || actualDay == activityDay)) {
                sender.sendNotification(vo.getActivityName(), getEmails(siptisUserList), vo.getActivityDate().toString());
            }
        }
    }

    private String[] getEmails(List<SiptisUser> userList) {
        String[] emails = new String[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            emails[i] = userList.get(i).getEmail();
        }
        return emails;
    }
}

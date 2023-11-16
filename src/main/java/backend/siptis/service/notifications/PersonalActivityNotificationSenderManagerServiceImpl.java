package backend.siptis.service.notifications;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.notifications.generalActivityServices.GeneralActivityServiceFindOperations;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PersonalActivityNotificationSenderManagerServiceImpl implements PersonalActivityNotificationSenderManagerService {
    private final GeneralActivityServiceFindOperations generalActivityServiceFindOperations;
    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;
    private final SendActivityNotificationServiceImpl sender;
    private final EmailFactory emailFactory;

    @Override
    public void sendPersonalActivities() throws MessagingException, IOException {

    }
}

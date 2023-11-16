package backend.siptis.service.notifications;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface GeneralActivityNotificationSenderService {
    void sendGeneralActivities() throws MessagingException, IOException;
}

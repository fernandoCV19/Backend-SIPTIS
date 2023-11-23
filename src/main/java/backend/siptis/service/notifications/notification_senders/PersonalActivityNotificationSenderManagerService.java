package backend.siptis.service.notifications.notification_senders;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface PersonalActivityNotificationSenderManagerService {
    void sendPersonalActivities() throws MessagingException, IOException;

}

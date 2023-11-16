package backend.siptis.service.notifications;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface PersonalActivityNotificationSenderManagerService {
    void sendPersonalActivities() throws MessagingException, IOException;

}

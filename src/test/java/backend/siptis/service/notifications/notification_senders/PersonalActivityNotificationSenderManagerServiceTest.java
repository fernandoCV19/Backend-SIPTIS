package backend.siptis.service.notifications.notification_senders;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PersonalActivityNotificationSenderManagerServiceTest {

    @Autowired
    private PersonalActivityNotificationSenderManagerService personalActivityNotificationSenderManagerService;

    @Test
    @DisplayName("Test send personal activities")
    void givenSiptisUsers_whenSendPersonalActivities_thenSuccess() throws MessagingException, IOException {
        personalActivityNotificationSenderManagerService.sendPersonalActivities();
    }
}

package backend.siptis.service.notifications.notification_senders;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonalActivityNotificationSenderManagerServiceTest {

    @Autowired
    private PersonalActivityNotificationSenderManagerService personalActivityNotificationSenderManagerService;

    @Test
    @DisplayName("Test send personal activities")
    void givenSiptisUsers_whenSendPersonalActivities_thenSuccess() throws MessagingException, IOException {
        personalActivityNotificationSenderManagerService.sendPersonalActivities();
    }
}

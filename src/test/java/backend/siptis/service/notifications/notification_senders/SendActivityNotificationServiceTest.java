package backend.siptis.service.notifications.notification_senders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SendActivityNotificationServiceTest {
    @Autowired
    private SendActivityNotificationService sendActivityNotificationService;

    @Test
    @DisplayName("Test for send notification")
    void givenTitle_whenSendNotification_thenSuccess() {
        String[] list = new String[2];
        sendActivityNotificationService.sendNotification("", list, "");
    }
}

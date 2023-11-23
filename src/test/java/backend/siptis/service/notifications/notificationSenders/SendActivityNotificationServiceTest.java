package backend.siptis.service.notifications.notificationSenders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class SendActivityNotificationServiceTest {
    @Autowired
    private SendActivityNotificationService sendActivityNotificationService;

    @Test
    @DisplayName("Test for send notification")
    void givenTitle_whenSendNotification_thenSuccess(){
        String[] list = new String[2];
        sendActivityNotificationService.sendNotification("", list, "");
    }
}

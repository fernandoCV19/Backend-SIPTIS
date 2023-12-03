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
public class GeneralActivityNotificationSenderServiceTest {
    @Autowired
    private GeneralActivityNotificationSenderService generalActivityNotificationSenderService;

    @Test
    @DisplayName("test for send general Activities")
    void givenUser_whenSendGeneralActivities() throws MessagingException, IOException {
        generalActivityNotificationSenderService.sendGeneralActivities();
    }
}

package backend.siptis.service.records;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.smtp.SmtpServer;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class EmailServiceImplTest {
    private final EmailServiceImpl emailServiceImpl;

    @Autowired
    public EmailServiceImplTest(EmailServiceImpl emailServiceImpl) {
        this.emailServiceImpl = emailServiceImpl;
    }
    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration
                    .aConfig()
                    .withUser("siptis.umss@gmail.com", "ftryberzfpkddfvj"));

    @Test
    void sendPersonalActivities() {
    }

    @Test
    void sendGeneralActivities() throws MessagingException, IOException, javax.mail.MessagingException {
        SmtpServer s = greenMail.getSmtp();
        emailServiceImpl.sendGeneralActivities();

            MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
            assertEquals(1, receivedMessages.length);

            MimeMessage receivedMessage = receivedMessages[0];
            assertEquals("Hello GreenMail!", GreenMailUtil.getBody(receivedMessage));
            assertEquals(1, receivedMessage.getAllRecipients().length);
            assertEquals("test@greenmail.io", receivedMessage.getAllRecipients()[0].toString());
    }

    @Test
    void readFile() {
    }

    @Test
    void sendEmailFromTemplate() {
    }

    @Test
    void sendSpecificEmail() {
    }
}

package backend.siptis.service.notifications.recoverPassword;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class SendRecoverPasswordEmailServiceTest {
    @Autowired
    private SendRecoverPasswordEmailService sendRecoverPasswordEmailService;

    @Test
    @DisplayName("Test for send recover password email")
    void givenTokenPasswordDTO_WhenSendRecoverPasswordEmail_ThenMessageEMAIL_NOT_EXIST(){
        ServiceAnswer answer = sendRecoverPasswordEmailService.sendRecoverPasswordEmail("wrong email");
        assertEquals(ServiceMessage.EMAIL_NOT_EXIST, answer.getServiceMessage());
    }
}

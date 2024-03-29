package backend.siptis.service.notifications.recover_password;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.TokenPasswordDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RecoverPasswordEmailServiceTest {
    @Autowired
    private RecoverPasswordEmailService recoverPasswordEmailService;
    private TokenPasswordDTO tokenPasswordDTO;

    private void startDTO() {
        tokenPasswordDTO = new TokenPasswordDTO();
        tokenPasswordDTO.setPassword("wrong password");
        tokenPasswordDTO.setTokenPassword("wrong password");
    }

    @Test
    @DisplayName("Test for change password")
    void givenTokenPasswordDTO_WhenChangePassword_ThenMessageINVALID_TOKEN() {
        startDTO();
        ServiceAnswer answer = recoverPasswordEmailService.changePassword(tokenPasswordDTO);
        assertEquals(ServiceMessage.INVALID_TOKEN, answer.getServiceMessage());
    }
}

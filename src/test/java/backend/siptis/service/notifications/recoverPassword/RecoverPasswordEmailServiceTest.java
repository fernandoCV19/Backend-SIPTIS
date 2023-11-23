package backend.siptis.service.notifications.recoverPassword;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.ChangePasswordDTO;
import backend.siptis.model.pjo.dto.userDataDTO.TokenPasswordDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RecoverPasswordEmailServiceTest {
    @Autowired
    private RecoverPasswordEmailService recoverPasswordEmailService;
    private TokenPasswordDTO tokenPasswordDTO;
    
    private void startDTO(){
        tokenPasswordDTO = new TokenPasswordDTO();
        tokenPasswordDTO.setPassword("wrong password");
        tokenPasswordDTO.setTokenPassword("wrong password");
    }
    @Test
    @DisplayName("Test for change password")
    void givenTokenPasswordDTO_WhenChangePassword_ThenMessageINVALID_TOKEN(){
        startDTO();
        ServiceAnswer answer = recoverPasswordEmailService.changePassword(tokenPasswordDTO);
        assertEquals(ServiceMessage.INVALID_TOKEN, answer.getServiceMessage());
    }
}

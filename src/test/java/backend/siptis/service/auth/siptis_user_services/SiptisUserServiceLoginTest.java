package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceLoginTest {
    @Autowired
    private SiptisUserServiceLogIn serviceLogin;
    private LogInDTO logInDTO;

    private void createLoginDTO() {
        logInDTO = new LogInDTO();
        logInDTO.setEmail("user@gmail.com");
        logInDTO.setPassword("12121212");
    }

    @Test
    @DisplayName("test logIn with wrong credentials")
    void givenLogInDTOWhenLoginThenServiceMessageERROR_BAD_CREDENTIALS() {
        createLoginDTO();
        assertEquals(ServiceMessage.ERROR_BAD_CREDENTIALS, serviceLogin.logIn(logInDTO).getServiceMessage());
    }

}

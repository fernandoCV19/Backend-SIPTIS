package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCareerServiceTest {
    @Autowired
    private UserCareerService userCareerService;

    @Test
    @DisplayName("Test for get non existing career by name")
    void givenNonExistingCareerName_WhenGetCareerByName_ThenServiceMessageNotFound(){
        ServiceAnswer answer = userCareerService.getCareerByName("TESIS");
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }
}

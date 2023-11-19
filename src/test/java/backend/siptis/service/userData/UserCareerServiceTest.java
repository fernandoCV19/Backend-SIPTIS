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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserCareerServiceTest {

    private final UserCareerService userCareerService;

    @Autowired
    UserCareerServiceTest(UserCareerService userCareerService){
        this.userCareerService = userCareerService;
    }

    @Test
    @DisplayName("Test for get non existing career by name")
    public void givenNonExistingCareerNameWhenGetCareerByNameThenServiceMessageNotFound(){
        ServiceAnswer answer = userCareerService.getCareerByName("TESIS");
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }
}

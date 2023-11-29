package backend.siptis.service.user_data;


import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserCareerServiceTest {
    private UserCareerService userCareerService;

    @Autowired
    UserCareerServiceTest(UserCareerService userCareerService){
        this.userCareerService = userCareerService;
    }

/*
    @Test
    @DisplayName("Test for get non existing career by name")
    void givenNonExistingCareerName_WhenGetCareerByName_ThenServiceMessageNotFound() {
        ServiceAnswer answer = userCareerService.getCareerByName("TESIS");
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for success get career by name")
    void givenNonExistingCareerName_WhenGetCareerByName_ThenServiceMessageOK() {
        ServiceAnswer answer = userCareerService.getCareerByName("INFORMATICA");
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }
*/
}

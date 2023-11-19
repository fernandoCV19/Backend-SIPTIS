package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ScheduleServiceTest {

    private final ScheduleService scheduleService;

    @Autowired
    ScheduleServiceTest(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @Test
    @DisplayName("Test for get all schedules of a non existing project")
    public void givenWrongProjectIdWhenGetAllSchedulesFromAProjectThenMessageID_DOES_NOT_EXIST(){
        ServiceAnswer answer = scheduleService.getAllSchedulesFromAProject(1l);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }


}

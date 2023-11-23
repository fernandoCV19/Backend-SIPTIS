package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;

    @Test
    @DisplayName("Test for get all schedules of a non existing project")
    void givenWrongProjectId_WhenGetAllSchedulesFromAProject_ThenMessageID_DOES_NOT_EXIST() {
        ServiceAnswer answer = scheduleService.getAllSchedulesFromAProject(1L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }


}

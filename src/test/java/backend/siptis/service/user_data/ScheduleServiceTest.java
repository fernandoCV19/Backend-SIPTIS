package backend.siptis.service.user_data;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.user_data.Schedule;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.user_data.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private SiptisUserRepository siptisUserRepository;

    private void createSchedule() {
        Schedule schedule = new Schedule();
        schedule.setSiptisUser(siptisUserRepository.findById(100L).get());
        schedule.setDay("day");
        schedule.setHourFinish("10");
        schedule.setHourStart("9");
        scheduleRepository.save(schedule);
    }

    @Test
    @DisplayName("Test for get all schedules of a non existing project")
    void givenWrongProjectId_WhenGetAllSchedulesFromAProject_ThenMessageID_DOES_NOT_EXIST() {
        ServiceAnswer answer = scheduleService.getAllSchedulesFromAProject(1L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for get all schedules of a project")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenProjectId_WhenGetAllSchedulesFromAProject_ThenMessageOK() {
        createSchedule();
        ServiceAnswer answer = scheduleService.getAllSchedulesFromAProject(112L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

}

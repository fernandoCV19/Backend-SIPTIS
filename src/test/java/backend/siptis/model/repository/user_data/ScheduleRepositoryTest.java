package backend.siptis.model.repository.user_data;

import backend.siptis.model.entity.user_data.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;
    private Schedule schedule;

    @BeforeEach
    void createPlaceToDefense() {
        schedule = new Schedule();
    }

    @Test
    @DisplayName("Test for find schedule by id")
    void givenScheduleId_whenFindById_thenScheduleObject() {
        scheduleRepository.save(schedule);
        assertFalse(scheduleRepository.findById(schedule.getId()).isEmpty());
    }
}

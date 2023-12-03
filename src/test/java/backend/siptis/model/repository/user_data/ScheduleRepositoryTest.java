package backend.siptis.model.repository.user_data;

import backend.siptis.model.entity.user_data.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;
    private Schedule schedule;

    @BeforeEach
    void createPlaceToDefense() {
        schedule = new Schedule();
    }

    @Test
    @DisplayName("Test for find schedule by id success")
    void givenScheduleId_whenFindById_thenScheduleObject() {
        scheduleRepository.save(schedule);
        assertFalse(scheduleRepository.findById(schedule.getId()).isEmpty());
    }
    @Test
    @DisplayName("Test for find schedule by id")
    void givenScheduleId_whenFindById_thenNull() {
        assertTrue(scheduleRepository.findById(111222L).isEmpty());
    }
}

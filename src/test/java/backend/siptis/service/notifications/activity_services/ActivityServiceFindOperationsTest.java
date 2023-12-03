package backend.siptis.service.notifications.activity_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ActivityServiceFindOperationsTest {
    @Autowired
    private ActivityServiceFindOperations activityServiceFindOperations;
    private Activity activity;

    private void startActivity() {
        activity = new Activity();
        activity.setActivityDate(LocalDate.now());
        activity.setId(1233245L);
        activity.setActivityName("name");
        activity.setActivityDescription("");
    }

    @Test
    @DisplayName("Test find by id")
    void givenId_whenFindById_thenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, activityServiceFindOperations.findById(1233453L).getServiceMessage());
    }

    @Test
    @DisplayName("Test find by id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenId_whenFindById_thenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, activityServiceFindOperations.findById(101L).getServiceMessage());
    }

    @Test
    @DisplayName("Test find all VO")
    void givenId_whenFindByAllVO_thenServiceMessageNOT_FOUND() {
        assertNotNull(activityServiceFindOperations.findAllVO());
    }


    @Test
    @DisplayName("Test Pageable find all VO")
    void givenPageable_whenFindByAllVO_thenNotNull() {
        assertNotNull(activityServiceFindOperations.findAllVO(Pageable.ofSize(12)));
    }

    @Test
    @DisplayName("Test entity to VO")
    void givenActivity_whenEntityToVO_thenServiceMessageNOT_FOUND() {
        startActivity();
        assertNotNull(activityServiceFindOperations.entityToVO(activity));
    }
}

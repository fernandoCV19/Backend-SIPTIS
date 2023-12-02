package backend.siptis.service.notifications.activity_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.pjo.dto.notifications.ActivityDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ActivityServiceModifyOperationsTest {
    @Autowired
    private ActivityServiceModifyOperations activityServiceModifyOperations;
    private ActivityDTO activityDTO;
    private Activity activity;

    private void startActivity() {
        activity = new Activity();
        activity.setActivityDate(LocalDate.now());
        activity.setId(1233245L);
        activity.setActivityName("name");
        activity.setActivityDescription("");
    }

    private void startActivityDTO() {
        activityDTO = new ActivityDTO();
        activityDTO.setIdProject(101);
    }

    @Test
    @DisplayName("Test persist activity not found")
    void givenId_whenPersistActivity_thenServiceMessageNOT_FOUND() {
        startActivityDTO();
        assertEquals(ServiceMessage.NOT_FOUND, activityServiceModifyOperations.persistActivity(activityDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test success persist activity")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenId_whenPersistActivity_thenServiceMessageOK() {
        startActivityDTO();
        assertEquals(ServiceMessage.OK, activityServiceModifyOperations.persistActivity(activityDTO).getServiceMessage());
    }

    @Test
    @DisplayName("Test update activity not found")
    void givenId_whenUpdate_thenServiceMessageNOT_FOUND() {
        startActivityDTO();
        assertEquals(ServiceMessage.NOT_FOUND, activityServiceModifyOperations.update(activityDTO, 1234678L).getServiceMessage());
    }
    @Test
    @DisplayName("Test success persist activity")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenId_whenUpdate_thenServiceMessageOK() {
        startActivityDTO();
        assertEquals(ServiceMessage.OK, activityServiceModifyOperations.update(activityDTO, 101L).getServiceMessage());
    }

    @Test
    @DisplayName("Test delete activity")
    void givenId_whenDelete_thenServiceMessageNOT_FOUND() {
        startActivityDTO();
        assertEquals(ServiceMessage.NOT_FOUND, activityServiceModifyOperations.delete(1234L).getServiceMessage());
    }

    @Test
    @DisplayName("Test entity to VO")
    void givenActivity_whenEntityToVO_thenServiceMessageNOT_FOUND() {
        startActivity();
        assertNotNull(activityServiceModifyOperations.entityToVO(activity));
    }
}

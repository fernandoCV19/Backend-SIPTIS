package backend.siptis.service.notifications.activity_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.pjo.dto.notifications.ActivityDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ActivityServiceModifyOperationsTest {
    @Autowired
    private ActivityServiceModifyOperations activityServiceModifyOperations;
    private ActivityDTO activityDTO;
    private Activity activity;

    private void startActivity() {
        activity = new Activity();
        activity.setActivityDate(new Date());
        activity.setId(1233245L);
        activity.setActivityName("name");
        activity.setActivityDescription("");
    }

    private void startActivityDTO() {
        activityDTO = new ActivityDTO();
    }

    @Test
    @DisplayName("Test persist activity")
    void givenId_whenPersistActivity_thenServiceMessageNOT_FOUND() {
        startActivityDTO();
        assertEquals(ServiceMessage.NOT_FOUND, activityServiceModifyOperations.persistActivity(activityDTO).getServiceMessage());
    }

    @Test
    @DisplayName("Test persist activity")
    void givenId_whenUpdate_thenServiceMessageNOT_FOUND() {
        startActivityDTO();
        assertEquals(ServiceMessage.NOT_FOUND, activityServiceModifyOperations.update(activityDTO, 1234L).getServiceMessage());
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

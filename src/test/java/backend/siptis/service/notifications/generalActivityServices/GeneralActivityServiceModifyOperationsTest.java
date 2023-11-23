package backend.siptis.service.notifications.generalActivityServices;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.GeneralActivity;
import backend.siptis.model.pjo.dto.notifications.GeneralActivityDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GeneralActivityServiceModifyOperationsTest {
    @Autowired
    private GeneralActivityServiceModifyOperations generalActivityServiceModifyOperations;
    private GeneralActivityDTO activityDTO;
    private GeneralActivity activity;

    private void startActivity(){
        activity = new GeneralActivity();
        activity.setActivityDate(new Date());
        activity.setId(1233245l);
        activity.setActivityName("name");
        activity.setActivityDescription("");
    }

    private void startGeneralActivityDTO(){
        activityDTO = new GeneralActivityDTO();
        activityDTO.setActivityDate(new Date());
        activityDTO.setActivityName("name");
        activityDTO.setActivityDescription("");
    }


    @Test
    @DisplayName("Test for persist general activity")
    void givenGeneralActivityDTO_WhenPersistGeneralActivity_ThenServiceMessageOK(){
        startGeneralActivityDTO();
        ServiceAnswer answer = generalActivityServiceModifyOperations.persistGeneralActivity(activityDTO);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for update activity")
    void givenGeneralActivityDTO_WhenUpdate_ThenServiceMessageNOT_FOUND(){
        startGeneralActivityDTO();
        ServiceAnswer answer = generalActivityServiceModifyOperations.update(activityDTO, 12123l);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for update activity")
    void givenGeneralActivityDTO_WhenDelete_ThenServiceMessageNOT_FOUND(){
        ServiceAnswer answer = generalActivityServiceModifyOperations.delete( 12123l);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test entity to VO")
    void givenActivity_whenEntityToVO_thenServiceMessageNOT_FOUND(){
        startActivity();
        assertNotNull(generalActivityServiceModifyOperations.entityToVO(activity));
    }
}

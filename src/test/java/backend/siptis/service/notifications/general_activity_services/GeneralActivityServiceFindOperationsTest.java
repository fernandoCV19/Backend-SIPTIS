package backend.siptis.service.notifications.general_activity_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.GeneralActivity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GeneralActivityServiceFindOperationsTest {
    @Autowired
    private GeneralActivityServiceFindOperations generalActivityServiceFindOperations;
    private GeneralActivity activity;

    private void startActivity() {
        activity = new GeneralActivity();
        activity.setActivityDate(LocalDate.now());
        activity.setId(1233245L);
        activity.setActivityName("name");
        activity.setActivityDescription("");
    }

    @Test
    @DisplayName("test find by id not found")
    void givenId_WhenFindById_ThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, generalActivityServiceFindOperations.findById(1233453L).getServiceMessage());
    }

    @Test
    @DisplayName("test success find by id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenId_WhenFindById_ThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, generalActivityServiceFindOperations.findById(101L).getServiceMessage());
    }

    @Test
    @DisplayName("test find all VO")
    void givenVO_WhenFindAllVO_ThenServiceMessageNOT_FOUND() {
        assertNotNull(generalActivityServiceFindOperations.findAllVO());
    }

    @Test
    @DisplayName("test find all Pageable VO")
    void givenPageable_WhenFindAllVO_ThenServiceMessageNOT_FOUND() {
        assertNotNull(generalActivityServiceFindOperations.findAllVO(Pageable.ofSize(12)));
    }

    @Test
    @DisplayName("test find all Pageable")
    void givenPageable_WhenFindAll_ThenServiceMessageNOT_FOUND() {
        assertNotNull(generalActivityServiceFindOperations.findAll(Pageable.ofSize(12)));
    }

    @Test
    @DisplayName("Test entity to VO")
    void givenActivity_whenEntityToVO_thenServiceMessageNOT_FOUND() {
        startActivity();
        assertNotNull(generalActivityServiceFindOperations.entityToVO(activity));
    }
}

package backend.siptis.model.repository.notifications;

import backend.siptis.model.entity.notifications.Activity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ActivityRepositoryTest {
    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @DisplayName("Test for get activity list from project id")
    void givenActivitiesAndProjectId_whenFindByProjectId_thenActivityList() {
        List<Activity> activities = activityRepository.findByProjectId(101L);
        assertNotNull(activities);
        assertEquals(10, activities.size());
    }

    @Test
    @DisplayName("Test for get empty activity list from project id")
    void givenNoActivitiesAndWrongProjectId_whenFindByProjectId_thenEmptyActivityList() {
        List<Activity> activities = activityRepository.findByProjectId(135L);
        assertNotNull(activities);
        assertEquals(0, activities.size());
    }



}

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
class ActivityRepositoryTest {
    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @DisplayName("Test for get activity list from project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenActivitiesAndProjectId_whenFindByProjectId_thenActivityList(){
        List<Activity> activities = activityRepository.findByProjectId(1L);
        assertNotNull(activities);
        assertEquals(1, activities.size());
    }
    @Test
    @DisplayName("Test for get empty activity list from project id")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenNoActivitiesAndWrongProjectId_whenFindByProjectId_thenEmptyActivityList(){
        List<Activity> activities = activityRepository.findByProjectId(123345L);
        assertNotNull(activities);
        assertEquals(0, activities.size());
    }


    @Test
    @DisplayName("Test for get empty activity Page list from project id")
    void givenPageableAndWrongProjectId_whenFindByProjectId_thenEmptyActivityList(){
        assertTrue(activityRepository.findByProjectId(123345L, Pageable.ofSize(4)).isEmpty());
    }
}

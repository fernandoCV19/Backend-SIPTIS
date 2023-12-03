package backend.siptis.model.repository.notifications;

import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.pjo.vo.ActivityVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ActivityRepositoryTest {
    @Autowired
    private ActivityRepository activityRepository;

    @Test
    @DisplayName("Test for get activity list from project id")
    void givenActivitiesAndProjectId_whenFindByProjectId_thenEmptyActivityList() {
        List<Activity> activities = activityRepository.findByProjectId(101L);
        assertNotNull(activities);
        assertEquals(0, activities.size());
    }
    @Test
    @DisplayName("Test for get activity list from project id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenActivitiesAndProjectId_whenFindByProjectId_thenActivityList() {
        List<Activity> activities = activityRepository.findByProjectId(101L);
        assertNotNull(activities);
        assertEquals(10, activities.size());
    }

}

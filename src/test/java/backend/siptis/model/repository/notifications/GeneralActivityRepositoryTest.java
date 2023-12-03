package backend.siptis.model.repository.notifications;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GeneralActivityRepositoryTest {
    @Autowired
    private GeneralActivityRepository generalActivityRepository;

    @Test
    @DisplayName("Test for find All After A Date")
    void givenActivitiesAndProjectId_whenFindByProjectId_thenEmptyActivityList() {
        assertTrue(generalActivityRepository.findAllAfterADate(LocalDate.now(), Pageable.ofSize(2)).isEmpty());
    }

    @Test
    @DisplayName("Test for find All After A Date success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenActivitiesAndProjectId_whenFindByProjectId_thenActivityList() {
        assertFalse(generalActivityRepository.findAllAfterADate(LocalDate.now(), Pageable.ofSize(2)).isEmpty());
    }

}

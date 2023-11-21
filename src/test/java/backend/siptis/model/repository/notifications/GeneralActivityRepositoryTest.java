package backend.siptis.model.repository.notifications;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class GeneralActivityRepositoryTest {
    @Autowired
    private GeneralActivityRepository generalActivityRepository;

    @Test
    @DisplayName("Test for find All After A Date")
    void givenActivitiesAndProjectId_whenFindByProjectId_thenActivityList(){
        assertNotNull(generalActivityRepository.findAllAfterADate(new Date(), Pageable.ofSize(2)));
    }
}

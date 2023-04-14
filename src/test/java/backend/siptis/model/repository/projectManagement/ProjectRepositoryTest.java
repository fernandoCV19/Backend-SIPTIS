package backend.siptis.model.repository.projectManagement;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectRepositoryTest {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectRepositoryTest(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Test
    void getIdsListFromReviewersWithACorrectIdReturnAListWithElements() {
        List<Long> query = projectRepository.getIdsListFromReviewers(40L);
        assertFalse(query.isEmpty());
    }

    @Test
    void getIdsListFromReviewersWithAIncorrectIdReturnAListWithoutElements() {
        List<Long> query = projectRepository.getIdsListFromReviewers(0L);
        assertTrue(query.isEmpty());
    }

    @Test
    void getIdsListFromReviewersWithACorrectIdReturnAListWithTheIdOfAllReviewers() {
        List<Long> query = projectRepository.getIdsListFromReviewers(40L);
        assertEquals(9, query.size());
    }
}
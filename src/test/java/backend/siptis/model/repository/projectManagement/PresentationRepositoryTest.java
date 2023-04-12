package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Presentation;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class PresentationRepositoryTest {

    private final PresentationRepository presentationRepository;

    @Autowired
    PresentationRepositoryTest(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    @Test
    void findTopByOrderByDateDescAndProjectIdWithACorrectIdReturnAnObject() {
        Presentation query = presentationRepository.findTopByProjectIdOrderByDateDesc(33L);
        assertNotNull(query);
    }

    @Test
    void findTopByOrderByDateDescAndProjectIdWithAIncorrectIdReturnANull() {
        Presentation query = presentationRepository.findTopByProjectIdOrderByDateDesc(0L);
        assertNull(query);
    }

    @Test
    void findTopByOrderByDateDescAndProjectIdWithACorrectIdReturnTheLastPresentation() {
        Presentation query = presentationRepository.findTopByProjectIdOrderByDateDesc(33L);
        assertEquals(query.getProject().getId(), 33);
    }

    @Test
    void findTopByOrderByDateDescAndProjectIdWithACorrectIdReturnAnObjectWithAllData() {
        Presentation query = presentationRepository.findTopByProjectIdOrderByDateDesc(33L);
        assertTrue(query.getBlueBookPath().equals("blueBookPath4") && query.getProjectPath().equals("projectPath4") && query.getDate().toString().equals("2022-03-01 00:00:00.0"));
    }

    @Test
    void findTopByOrderByDateDescAndProjectIdWithACorrectIdReturnAnObjectWithAListOfReviews() {
        Presentation query = presentationRepository.findTopByProjectIdOrderByDateDesc(33L);
        assertFalse(query.getReviews().isEmpty());
    }

    @Test
    void findTopByOrderByDateDescAndProjectIdWithACorrectIdReturnAnObjectWithAProject() {
        Presentation query = presentationRepository.findTopByProjectIdOrderByDateDesc(33L);
        assertEquals(query.getProject().getId(), 33L);
    }
}
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
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class PresentationRepositoryTest {
    @Autowired
    private PresentationRepository presentationRepository;

    @Test
    void findByIdReturnNotNull(){
        Optional<Presentation> ans = presentationRepository.findById(8L);
        assertNotNull(ans);
    }

    @Test
    void findByIdReturnAnElement(){
        Optional<Presentation> ans = presentationRepository.findById(8L);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findByIdReturnObjectActualId(){
        Optional<Presentation> ans = presentationRepository.findById(8L);
        Presentation fAns = ans.get();
        assertEquals(fAns.getId(),8L);
    }

    @Test
    void findTopByProjectIdAndReviewedReturnNotNull(){
        Optional<Presentation> ans = presentationRepository.findTopByProjectIdAndReviewed(1L, true);
        assertNotNull(ans);
    }

    @Test
    void findTopByProjectIdAndReviewedReturnAnElement(){
        Optional<Presentation> ans = presentationRepository.findTopByProjectIdAndReviewed(1L, true);
        assertFalse(ans.isEmpty());
    }

    @Test
    void findTopByProjectIdAndReviewedReturnAnything(){
        Optional<Presentation> ans = presentationRepository.findTopByProjectIdAndReviewed(2L, true);
        assertTrue(ans.isEmpty());
    }

    @Test
    void findTopByProjectIdAndReviewedReturnAnythingReturnObjectActualId(){
        Optional<Presentation> ans = presentationRepository.findTopByProjectIdAndReviewed(1L, true);
        Presentation fAns = ans.get();
        assertEquals(fAns.getId(),8L);
    }

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

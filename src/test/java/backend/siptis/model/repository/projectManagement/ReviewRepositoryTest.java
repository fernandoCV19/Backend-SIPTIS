package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.presentations.Review;
import backend.siptis.model.repository.presentations.ReviewRepository;
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
class ReviewRepositoryTest {

    private final ReviewRepository reviewRepository;

    @Autowired
    ReviewRepositoryTest(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Test
    void findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDescWithACorrectIdReturnAnObject() {
        Review query = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(33L, 30L);
        assertNotNull(query);
    }

    @Test
    void findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDescWithAnIncorrectIdReturnNull() {
        Review query = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(0L, 0L);
        assertNull(query);
    }

    @Test
    void findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDescWithACorrectIdReturnTheLastReviewOfAPresentation() {
        Review query = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(33L, 30L);
        assertEquals(query.getId(), 4L);
    }

    @Test
    void findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDescWithACorrectIdReturnAnObjectWithAllData() {
        Review query = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(33L, 30L);
        assertTrue(query.getDocumentPath().equals("documentPath4") && query.getCommentary().equals("commentary4") && query.getDate().toString().equals("2022-03-10 00:00:00.0"));
    }

    @Test
    void findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDescWithACorrectIdReturnACorrectJoinWithTheUser() {
        Review query = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(33L, 30L);
        assertEquals(query.getSiptisUser().getId(), 30L);
    }

    @Test
    void findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDescWithACorrectIdReturnACorrectJoinWithThePresentation() {
        Review query = reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(33L, 30L);
        assertEquals(query.getPresentation().getId(), 4L);
    }
}
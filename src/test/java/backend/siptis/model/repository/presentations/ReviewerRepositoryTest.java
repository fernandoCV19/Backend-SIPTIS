package backend.siptis.model.repository.presentations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class ReviewerRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("test find top presentation project id and siptis user id order by date desc")
    void givenWhenFindTopByPresentationProjectIdAndSiptisUserIdOrderByDateDescThenNull() {
        assertNull(reviewRepository.findTopByPresentationProjectIdAndSiptisUserIdOrderByDateDesc(12345L, 12345L));
    }

}

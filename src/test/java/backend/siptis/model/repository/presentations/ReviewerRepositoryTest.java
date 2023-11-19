package backend.siptis.model.repository.presentations;

import backend.siptis.model.entity.presentations.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ReviewerRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;
    private Review review;



}

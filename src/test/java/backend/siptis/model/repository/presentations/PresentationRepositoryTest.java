package backend.siptis.model.repository.presentations;

import backend.siptis.model.entity.presentations.Presentation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@DataJpaTest
class PresentationRepositoryTest {
    @Autowired
    private PresentationRepository presentationRepository;

    @Test
    @DisplayName("test find by id")
    void givenIdWhenFindByIdThenEmpty(){
        assertTrue(presentationRepository.findById(123123l).isEmpty());
    }
    @Test
    @DisplayName("test find top by project id and reviewed ")
    void givenIdWhenFindTopByProjectIdAndReviewedThenEmpty(){
        assertTrue(presentationRepository.findTopByProjectIdAndReviewed(123123l, true).isEmpty());
    }
    @Test
    @DisplayName("test find by project id and reviewed ")
    void givenIdWhenFindByProjectIdAndReviewedThenEmpty(){
        assertTrue(presentationRepository.findByProjectIdAndReviewed(123123l, true).isEmpty());
    }
    @Test
    @DisplayName("test find top by project id order by date desc")
    void givenIdWhenFindTopByProjectIdOrderByDateDescThenEmpty(){
        assertNull(presentationRepository.findTopByProjectIdOrderByDateDesc(123123l));
    }
    @Test
    @DisplayName("test delete by id")
    void givenIdWhenDeleteByIdThenEmpty(){
        presentationRepository.save(new Presentation());
        presentationRepository.deleteById(1l);
    }
}

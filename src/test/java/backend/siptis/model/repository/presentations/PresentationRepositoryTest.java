package backend.siptis.model.repository.presentations;

import backend.siptis.model.entity.presentations.Presentation;
import backend.siptis.model.repository.project_management.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PresentationRepositoryTest {
    @Autowired
    private PresentationRepository presentationRepository;
    @Autowired
    private ProjectRepository projectRepository;
    private Presentation presentation;

    private void createPresentation() {
        presentation = new Presentation();
        presentation.setDate(LocalDateTime.now());
        presentation.setBlueBookPath("path");
        presentation.setPhase("PHASE");
        presentation.setProjectPath("path");
        presentation.setReviewed(true);
        presentation.setProject(projectRepository.findById(100L).get());
        presentationRepository.save(presentation);
    }

    @Test
    @DisplayName("test find by id")
    void givenIdWhenFindByIdThenEmpty() {
        assertTrue(presentationRepository.findById(123123L).isEmpty());
    }

    @Test
    @DisplayName("test find by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByIdThenPresentation() {
        createPresentation();
        assertFalse(presentationRepository.findById(presentation.getId()).isEmpty());
    }

    @Test
    @DisplayName("test find top by project id and reviewed ")
    void givenIdWhenFindTopByProjectIdAndReviewedThenEmpty() {
        assertTrue(presentationRepository.findTopByProjectIdAndReviewed(123123L, true).isEmpty());
    }
    /*@Test
    @DisplayName("test find top by project id and reviewed success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindTopByProjectIdAndReviewedThenPresentation() {
        createPresentation();
        assertFalse(presentationRepository.findTopByProjectIdAndReviewed(presentation.getId(), true).isEmpty());
    }*/

    @Test
    @DisplayName("test find by project id and reviewed ")
    void givenIdWhenFindByProjectIdAndReviewedThenEmpty() {
        assertTrue(presentationRepository.findByProjectIdAndReviewed(123123L, true).isEmpty());
    }
    /*@Test
    @DisplayName("test find by project id and reviewed success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindByProjectIdAndReviewedThenPresentation() {
        createPresentation();
        assertFalse(presentationRepository.findByProjectIdAndReviewed(presentation.getId(), true).isEmpty());
    }*/

    @Test
    @DisplayName("test find top by project id order by date desc")
    void givenIdWhenFindTopByProjectIdOrderByDateDescThenEmpty() {
        assertNull(presentationRepository.findTopByProjectIdOrderByDateDesc(123123L));
    }
    /*@Test
    @DisplayName("test find top by project id order by date desc success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenIdWhenFindTopByProjectIdOrderByDateDescThenPresentation() {
        createPresentation();
        assertNotNull(presentationRepository.findTopByProjectIdOrderByDateDesc(presentation.getId()));
    }*/

    @Test
    @DisplayName("test delete by id")
    void givenIdWhenDeleteByIdThenEmpty() {
        presentationRepository.save(new Presentation());
        presentationRepository.deleteById(1L);
    }
}

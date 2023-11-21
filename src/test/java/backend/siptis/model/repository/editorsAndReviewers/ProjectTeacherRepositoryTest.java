package backend.siptis.model.repository.editorsAndReviewers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProjectTeacherRepositoryTest {
    @Autowired
    private ProjectTeacherRepository projectTeacherRepository;

    @Test
    @DisplayName("test for get list of project teacher find by teacher id not accepted and reviewed ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue_thenEmpty(){
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(123l).isEmpty());
    }
    @Test
    @DisplayName("test for get list of project teacher find by teacher id not accepted and not reviewed ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse_thenEmpty(){
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(123l).isEmpty());
    }
    @Test
    @DisplayName("test for get list of project teacher find by teacher id  accepted  ")
    void givenBadId_whenFindByTeacherIdAndAcceptedIsTrue_thenEmpty(){
        assertTrue(projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(123l).isEmpty());
    }
    @Test
    @DisplayName("test for get list of project teacher find by teacher id  accepted  ")
    void givenBadIds_whenFindByTeacherIdAndProjectId_thenNull(){
        assertNull(projectTeacherRepository.findByTeacherIdAndProjectId(123l, 123l));
    }
}

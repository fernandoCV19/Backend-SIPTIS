package backend.siptis.model.repository.user_data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserCareerRepositoryTest {

    @Autowired
    private UserCareerRepository userCareerRepository;

    @Test
    @DisplayName("Test for list of careers")
    void givenUserCareer_whenFindAll_thenUserCareerList() {
        assertFalse(userCareerRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist user career by name")
    void givenUserCareerName_ExistsByName_thenTrue() {
        assertTrue(userCareerRepository.existsByName("INFORMATICA"));
    }

    @Test
    @DisplayName("Test for verify if exist user career by non existing name")
    void givenUserCareerName_ExistsByName_thenFalse() {
        assertFalse(userCareerRepository.existsByName("USER CAREER EXAMPLE"));
    }

    @Test
    @DisplayName("Test for find user career by name")
    void givenUserCareerName_FindUserCareerByName_thenUserCareerObject() {
        assertNotNull(userCareerRepository.findUserCareerByName("INFORMATICA"));
    }

    @Test
    @DisplayName("Test for find user career by non existing name")
    void givenUserCareerName_FindUserCareerByName_thenNull() {
        assertNull(userCareerRepository.findUserCareerByName("USER CAREER EXAMPLE"));
    }
}

package backend.siptis.model.repository.user_data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserAreaRepositoryTest {
    @Autowired
    private UserAreaRepository userAreaRepository;

    @Test
    @DisplayName("Test for list of areas")
    void givenUserAreas_whenFindAll_thenUserAreasList() {
        assertTrue(userAreaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist user area by non existing id")
    void givenId_ExistsUserAreaById_thenFalse() {
        assertFalse(userAreaRepository.existsUserAreaById(123456L));
    }

    @Test
    @DisplayName("Test for verify if exist user area by non existing name")
    void givenName_ExistsUserAreaByName_thenFalse() {
        assertFalse(userAreaRepository.existsUserAreaByName("USER AREA EXAMPLE"));
    }
}

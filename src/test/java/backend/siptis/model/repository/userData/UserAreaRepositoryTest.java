package backend.siptis.model.repository.userData;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserAreaRepositoryTest {
    @Autowired
    private UserAreaRepository userAreaRepository;

    @Test
    @DisplayName("Test for list of areas")
    public void givenUserAreas_whenFindAll_thenUserAreasList(){
        assertFalse(userAreaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist user area by name")
    public void givenUserAreas_ExistsUserAreaByName_thenTrue(){
        assertTrue(userAreaRepository.existsUserAreaByName("USER AREA 1"));
    }
    @Test
    @DisplayName("Test for verify if exist user area by non existing name")
    public void givenUserAreas_ExistsUserAreaByName_thenUFalse(){
        assertFalse(userAreaRepository.existsUserAreaByName("USER AREA EXAMPLE"));
    }
}

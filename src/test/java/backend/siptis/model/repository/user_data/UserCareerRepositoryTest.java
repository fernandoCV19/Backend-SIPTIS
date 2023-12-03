package backend.siptis.model.repository.user_data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserCareerRepositoryTest {

    @Autowired
    private UserCareerRepository userCareerRepository;

    @Test
    @DisplayName("Test for list of careers")
    void givenUserCareer_whenFindAll_thenEmptyUserCareerList() {
        assertTrue(userCareerRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for list of careers success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserCareer_whenFindAll_thenUserCareerList() {
        assertFalse(userCareerRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist user career by name")
    void givenUserCareerName_ExistsByName_thenFalse() {
        assertFalse(userCareerRepository.existsByName("wrong name"));
    }

    @Test
    @DisplayName("Test for verify if exist user career by name success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserCareerName_ExistsByName_thenTrue() {
        assertTrue(userCareerRepository.existsByName("INFORMATICA"));
    }

    @Test
    @DisplayName("Test for find user career by name")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserCareerName_FindByName_thenUserCareerObject() {
        assertFalse(userCareerRepository.findByName("INFORMATICA").isEmpty());
    }

    @Test
    @DisplayName("Test for find user career by non existing name")
    void givenUserCareerName_FindByName_thenNull() {
        assertTrue(userCareerRepository.findByName("USER CAREER EXAMPLE").isEmpty());
    }

    @Test
    @DisplayName("Test for find user career by name")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserCareerName_FindUserCareerByName_thenUserCareerObject() {
        assertNotNull(userCareerRepository.findUserCareerByName("INFORMATICA"));
    }

    @Test
    @DisplayName("Test for find user career by non existing name")
    void givenUserCareerName_FindUserCareerByName_thenNull() {
        assertNull(userCareerRepository.findUserCareerByName("USER CAREER EXAMPLE"));
    }
}

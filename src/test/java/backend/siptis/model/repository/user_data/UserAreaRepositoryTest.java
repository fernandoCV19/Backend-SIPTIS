package backend.siptis.model.repository.user_data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserAreaRepositoryTest {
    @Autowired
    private UserAreaRepository userAreaRepository;

    @Test
    @DisplayName("Test for list of areas")
    void givenUserAreas_whenFindAll_thenEmptyUserAreasList() {
        assertTrue(userAreaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for list of areas success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserAreas_whenFindAll_thenUserAreasList() {
        assertFalse(userAreaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist user area by non existing id")
    void givenId_ExistsUserAreaById_thenFalse() {
        assertFalse(userAreaRepository.existsUserAreaById(123456L));
    }

    @Test
    @DisplayName("Test for verify if exist user area by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenId_ExistsUserAreaById_thenTrue() {
        assertTrue(userAreaRepository.existsUserAreaById(100L));
    }

    @Test
    @DisplayName("Test for verify if exist user area by non existing name")
    void givenName_ExistsUserAreaByName_thenFalse() {
        assertFalse(userAreaRepository.existsUserAreaByName("USER AREA EXAMPLE"));
    }

    @Test
    @DisplayName("Test for verify if exist user area by non existing name success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenName_ExistsUserAreaByName_thenTrue() {
        assertTrue(userAreaRepository.existsUserAreaByName("REDES"));
    }
}

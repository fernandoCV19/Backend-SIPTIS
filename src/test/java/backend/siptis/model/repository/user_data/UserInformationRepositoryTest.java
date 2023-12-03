package backend.siptis.model.repository.user_data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserInformationRepositoryTest {
    @Autowired
    private UserInformationRepository userInformationRepository;

    @Test
    @DisplayName("Test for verify if exist user information by ci")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserInformationCi_whenExistByCi_thenTrue() {
        assertTrue(userInformationRepository.existsByCi("9364073"));
    }
    @Test
    @DisplayName("Test for verify if exist user information by non existing ci")
    void givenUserInformationCi_whenExistByCi_thenFalse() {
        assertFalse(userInformationRepository.existsByCi("9364073"));
    }

    @Test
    @DisplayName("Test for verify if exist user information by codSIS")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserInformationCodSIS_whenExistByCodSIS_thenTrue() {
        assertTrue(userInformationRepository.existsByCodSIS("201900520"));
    }
    @Test
    @DisplayName("Test for verify if exist user information by non existing codSIS")
    void givenUserInformationCodSIS_whenExistByCodSIS_thenFalse() {
        assertFalse(userInformationRepository.existsByCodSIS("201900520"));
    }

    @Test
    @DisplayName("Test for verify if exist user information by non existing codSIS")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserInformationCodSIS_whenFindById_thenTrue() {
        assertFalse(userInformationRepository.findById(100L).isEmpty());
    }
    @Test
    @DisplayName("Test for verify if exist user information by non existing codSIS")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserInformationCodSIS_whenFindById_thenFalse() {
        assertTrue(userInformationRepository.findById(12334L).isEmpty());
    }

    @Test
    @DisplayName("Test for get names of teachers in charge of project")
    void givenUsersAndProject_whenGetTeachersNames_thenEmptyStringList() {
        List<String> names = userInformationRepository.getTeachersNames(1L);
        assertTrue(names.isEmpty());
    }
    @Test
    @DisplayName("Test for get names of teachers in charge of project")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUsersAndProject_whenGetTeachersNames_thenStringList() {
        List<String> names = userInformationRepository.getTeachersNames(100L);
        assertFalse(names.isEmpty());
    }

    @Test
    @DisplayName("Test for get names of tutors in charge of project")
    void givenUsersAndProject_whenGetTutorsNames_thenEmptyStringList() {
        List<String> names = userInformationRepository.getTutorsNames(1L);
        assertTrue(names.isEmpty());
    }
    @Test
    @DisplayName("Test for get names of tutors in charge of project success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUsersAndProject_whenGetTutorsNames_thenStringList() {
        List<String> names = userInformationRepository.getTutorsNames(101L);
        assertFalse(names.isEmpty());
    }

}

package backend.siptis.model.repository.userData;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserInformationRepositoryTest {
    @Autowired
    private  UserInformationRepository userInformationRepository;

    @Test
    @DisplayName("Test for verify if exist user information by ci")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUserInformationCi_whenExistByCi_thenTrue(){
        assertTrue(userInformationRepository.existsByCi("912601"));
    }
    @Test
    @DisplayName("Test for verify if exist user information by non existing ci")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUserInformationCi_whenExistByCi_thenFalse(){
        assertFalse(userInformationRepository.existsByCi("11111"));
    }

    @Test
    @DisplayName("Test for verify if exist user information by codSIS")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUserInformationCodSIS_whenExistByCodSIS_thenTrue(){
        assertTrue(userInformationRepository.existsByCodSIS("200547236"));
    }

    @Test
    @DisplayName("Test for verify if exist user information by non existing codSIS")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUserInformationCodSIS_whenExistByCodSIS_thenFalse(){
        assertFalse(userInformationRepository.existsByCodSIS("11111"));
    }

    @Test
    @DisplayName("Test for verify if exist user information by non existing codSIS")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUserInformationCodSIS_whenFindById_thenFalse(){
        assertFalse(userInformationRepository.findById(1L).isEmpty());
    }


    @Test
    @DisplayName("Test for get names of teachers in charge of project")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUsersAndProject_whenGetTeachersNames_thenStringList(){
        List<String> names = userInformationRepository.getTeachersNames(1l);
        assertNotNull(names);
        assertEquals(1, names.size());
    }

    @Test
    @DisplayName("Test for get names of tutors   in charge of project")
    @Sql(scripts = {"/custom_imports/create_projects_and_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void givenUsersAndProject_whenGetTutorsNames_thenStringList(){
        List<String> names = userInformationRepository.getTutorsNames(1l);
        assertNotNull(names);
        assertEquals(1, names.size());
    }


}

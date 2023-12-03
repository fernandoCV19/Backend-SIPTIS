package backend.siptis.model.repository.auth;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SiptisUserRepositoryTest {
    @Autowired
    private SiptisUserRepository siptisUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    private SiptisUser siptisUser;

    @BeforeEach
    void createSiptisUser() {
        siptisUser = new SiptisUser();
        siptisUser.setEmail("exampleEmail@gmail.com");
        siptisUser.setPassword("12121212");
        Role role = roleRepository.findRoleByName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        siptisUser.setRoles(roles);
    }

    private SiptisUser createNewUser() {
        SiptisUser siptisUser2 = new SiptisUser();
        siptisUser2.setEmail("exampleEmail2@gmail.com");
        siptisUser2.setPassword("12121212");
        siptisUser2.setTokenPassword("abcd1234");
        return siptisUser2;
    }

    @Test
    @DisplayName("Test for get empty user list")
    void givenNoSiptisUser_whenFindAll_thenEmptyList() {
        List<SiptisUser> userList = siptisUserRepository.findAll();
        assertTrue(userList.isEmpty());
    }
    @Test
    @DisplayName("Test for get all user list")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUserList_whenFindAll_thenSiptisUserList() {
        siptisUserRepository.save(siptisUser);
        siptisUserRepository.save(createNewUser());
        List<SiptisUser> userList = siptisUserRepository.findAll();
        assertFalse(userList.isEmpty());
    }

    @Test
    @DisplayName("Test for find user using email")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUserEmail_whenFindOneByEmail_thenSiptisUserObject() {
        siptisUserRepository.save(siptisUser);
        SiptisUser getUser = siptisUserRepository.findOneByEmail("dilan.est@mail.com").get();
        assertNotNull(getUser);
    }
    @Test
    @DisplayName("Test for find user using wrong email")
    void givenSiptisUserEmail_whenFindOneByEmail_thenNull() {
        assertTrue(siptisUserRepository.findOneByEmail("example2Email@gmail.com").isEmpty());
    }

    @Test
    @DisplayName("Test for find user using token password")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUser_whenFindByTokenPassword_thenSiptisUserObject() {
        siptisUserRepository.save(createNewUser());
        SiptisUser getUser = siptisUserRepository.findByTokenPassword("abcd1234").get();
        assertNotNull(getUser);
    }
    @Test
    @DisplayName("Test for find user using wrong token password")
    void givenSiptisUser_whenFindByTokenPassword_thenNull() {
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.findByTokenPassword("1212121212").isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist user by email")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUserEmail_whenExistSiptisUserByEmail_thenTrue() {
        assertTrue(siptisUserRepository.existsByEmail("dilan.est@mail.com"));
    }
    @Test
    @DisplayName("Test for verify if exist user by not existing email")
    void givenNoSiptisUser_whenExistSiptisUserByEmail_thenFalse() {
        assertFalse(siptisUserRepository.existsByEmail("exampleEmail@gmail.com"));
    }

    @Test
    @DisplayName("Test for verify if exist users with role name")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenAdminSiptisUser_whenExistsByRolesName_thenTrue() {
        assertTrue(siptisUserRepository.existsByRolesName("ADMIN"));
    }
    @Test
    @DisplayName("Test for verify if exist users with not existing role name")
    void givenStudentSiptisUser_whenExistsByRolesName_thenFalse() {
        siptisUserRepository.save(siptisUser);
        assertFalse(siptisUserRepository.existsByRolesName("STUDENT"));
    }


    @Test
    @DisplayName("Test find one not existing user with role name")
    void givenNoSiptisUser_whenFindOneByRolesName_thenNull() {
        assertTrue(siptisUserRepository.findOneByRolesName("STUDENT").isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist non existing user by id")
    void givenSiptisUser_whenExistById_thenFalse() {
        siptisUserRepository.save(siptisUser);
        assertFalse(siptisUserRepository.existsById(123456789L));
    }
    @Test
    @DisplayName("Test for verify if exist user using id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUserId_whenExistById_thenTrue() {
        assertTrue(siptisUserRepository.existsById(101L));
    }

    @Test
    @DisplayName("Test for verify if exist user using token password")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUser_whenExistByTokenPassword_thenTrue() {
        siptisUserRepository.save(createNewUser());
        assertTrue(siptisUserRepository.existsByTokenPassword("abcd1234"));
    }
    @Test
    @DisplayName("Test for verify if exist user token password")
    void givenSiptisUser_whenExistByTokenPassword_thenFalse() {
        siptisUserRepository.save(siptisUser);
        assertFalse(siptisUserRepository.existsByTokenPassword("1212121212"));
    }

    @Test
    @DisplayName("Test for verify if exist non existing user by id")
    void givenSiptisUser_whenFindById_thenNull() {
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.findById(123456789L).isEmpty());
    }
    @Test
    @DisplayName("Test for find user using id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUserId_whenFindById_thenSiptisUserObject() {
        SiptisUser getUser = siptisUserRepository.findById(101L).get();
        assertNotNull(getUser);
    }

    @Test
    @DisplayName("Test find not existing user with role name")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenNoSiptisUser_whenFindByRolesName_theNotNull() {
        assertFalse(siptisUserRepository.findByRolesName("ADMIN").isEmpty());
    }
    @Test
    @DisplayName("Test find not existing user with role name")
    void givenNoSiptisUser_whenFindByRolesName_thenNull() {
        assertTrue(siptisUserRepository.findByRolesName("STUDENT").isEmpty());
    }

    @Test
    @DisplayName("Test find not existing user with role name")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenNoSiptisUser_whenFindByRoles_NameOrderByUserInformation_LastNamesAsc_theNotEmpty() {
        siptisUserRepository.save(siptisUser);
        assertFalse(siptisUserRepository.findByRoles_NameOrderByUserInformation_LastNamesAsc("ADMIN").isEmpty());
    }
    @Test
    @DisplayName("Test find not existing user with role name")
    void givenNoSiptisUser_whenFindByRoles_NameOrderByUserInformation_LastNamesAsc_thenEmpty() {
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.findByRoles_NameOrderByUserInformation_LastNamesAsc("STUDENT").isEmpty());
    }

    @Test
    @DisplayName("Test for verify if one exist user by id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUser_whenFindOneById_thenSiptisUserObject() {
        assertNotNull(siptisUserRepository.findOneById(100L));
    }
    @Test
    @DisplayName("Test for verify if one exist user by id null")
    void givenSiptisUser_whenFindOneById_thenNull() {
        assertTrue(siptisUserRepository.findOneById(123123213213L).isEmpty());
    }

    @Test
    @DisplayName("Test for find all personal activities")
    void givenSiptisUser_whenFindAllPersonalActivities_thenSiptisUserObject() {
        assertNotNull(siptisUserRepository.findAllPersonalActivities(123L, LocalDate.now(), Pageable.ofSize(12)));
    }
    @Test
    @DisplayName("Test for find all personal activities")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUser_whenFindAllPersonalActivities_thenNull() {
        assertTrue(siptisUserRepository.findAllPersonalActivities(100L, LocalDate.now(), Pageable.ofSize(2)).isEmpty());
    }

    @Test
    @DisplayName("Test for find project by id")
    void givenSiptisUser_whenFindProjectById_thenEmpty() {
        assertTrue(siptisUserRepository.findProjectById(123L).isEmpty());
    }
    @Test
    @DisplayName("Test for find project by id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUser_whenFindProjectById_thenNotEmpty() {
        assertFalse(siptisUserRepository.findProjectById(100L).isEmpty());
    }

    @Test
    @DisplayName("Test for search user list")
    void givenRoleAndPageable_whenSearchUserList_thenNotNull() {
        assertNotNull(siptisUserRepository.searchUserList("", "TUTOR", Pageable.ofSize(2)));
    }

/*
    @Test
    @DisplayName("Test for verify if one exist user by id")
    void givenSiptisUser_whenFindOneById_thenSiptisUserObject() {
        siptisUserRepository.save(siptisUser);
        assertNotNull(siptisUserRepository.findOneById(siptisUser.getId()).get());
    }

    @Test
    @DisplayName("Test for find all personal activities")
    void givenSiptisUser_whenFindAllPersonalActivities_thenSiptisUserObject() {
        assertNotNull(siptisUserRepository.findAllPersonalActivities(123L, LocalDate.now(), Pageable.ofSize(12)));
    }

    @Test
    @DisplayName("Test for find project by id")
    void givenSiptisUser_whenFindProjectById_thenNotNull() {
        assertTrue(siptisUserRepository.findProjectById(123L).isEmpty());
    }

    @Test
    @DisplayName("Test for search user list")
    void givenRoleAndPageable_whenSearchUserList_thenNotNull() {
        assertNotNull(siptisUserRepository.searchUserList("", "TUTOR", Pageable.ofSize(2)));
    }

    @Test
    @DisplayName("Test for search normal user list")
    void givenPageable_whenSearchNormalUserList_thenNotNull() {
        assertNotNull(siptisUserRepository.searchNormalUserList("", Pageable.ofSize(2)));
    }

    @Test
    @DisplayName("Test for search admin list")
    void givenPageable_whenSearchAdminList_thenNotNull() {
        assertNotNull(siptisUserRepository.searchAdminList("", Pageable.ofSize(2)));
    }

    @Test
    @DisplayName("test for get number students in career")
    void givenSiptisUser_whenetNumberOfStudentsInCareer_ThenNotNull() {
        assertNotNull(siptisUserRepository.getNumberOfStudentsInCareer(1L));
    }

    @Test
    @DisplayName("test for get number students by year and career")
    void givenSiptisUser_whenetNumberOfStudentsByYearAndCareer_ThenNotNull() {
        assertNotNull(siptisUserRepository.getNumberOfStudentsByYearAndCareer(1L));
    }
*/
}

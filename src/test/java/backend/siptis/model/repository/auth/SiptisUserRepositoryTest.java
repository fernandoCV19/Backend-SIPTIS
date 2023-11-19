package backend.siptis.model.repository.auth;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SiptisUserRepositoryTest {
    @Autowired
    private SiptisUserRepository siptisUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    private SiptisUser siptisUser;

    @BeforeEach
    public void createSiptisUser(){
        siptisUser = new SiptisUser();
        siptisUser.setEmail("exampleEmail@gmail.com");
        siptisUser.setPassword("12121212");
        Role role = roleRepository.findRoleByName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        siptisUser.setRoles(roles);
    }
    private SiptisUser createNewUser(){
        SiptisUser siptisUser2 = new SiptisUser();
        siptisUser2.setEmail("exampleEmail2@gmail.com");
        siptisUser2.setPassword("12121212");
        siptisUser2.setTokenPassword("abcd1234");
        return siptisUser2;
    }

    @Test
    @DisplayName("Test for get empty user list")
    public void givenNoSiptisUser_whenFindAll_thenEmptyList(){
        List<SiptisUser> userList = siptisUserRepository.findAll();
        assertTrue(userList.isEmpty());
    }
    @Test
    @DisplayName("Test for get all user list")
    public void givenSiptisUserList_whenFindAll_thenSiptisUserList(){
        siptisUserRepository.save(siptisUser);
        siptisUserRepository.save(createNewUser());
        List<SiptisUser> userList = siptisUserRepository.findAll();
        assertFalse(userList.isEmpty());
        assertEquals(2, userList.size());
    }

    @Test
    @DisplayName("Test for find user using email")
    public void givenSiptisUserEmail_whenFindOneByEmail_thenSiptisUserObject(){
        siptisUserRepository.save(siptisUser);
        SiptisUser getUser = siptisUserRepository.findOneByEmail("exampleEmail@gmail.com").get();
        assertNotNull(getUser);
    }
    @Test
    @DisplayName("Test for find user using wrong email")
    public void givenSiptisUserEmail_whenFindOneByEmail_thenNull(){
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.findOneByEmail("example2Email@gmail.com").isEmpty());
    }

    @Test
    @DisplayName("Test for find user using token password")
    public void givenSiptisUser_whenFindByTokenPassword_thenSiptisUserObject(){
        siptisUserRepository.save(createNewUser());
        SiptisUser getUser = siptisUserRepository.findByTokenPassword("abcd1234").get();
        assertNotNull(getUser);
    }
    @Test
    @DisplayName("Test for find user using wrong token password")
    public void givenSiptisUser_whenFindByTokenPassword_thenNull(){
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.findByTokenPassword("1212121212").isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist user by email")
    public void givenSiptisUserEmail_whenExistSiptisUserByEmail_thenTrue(){
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.existsByEmail("exampleEmail@gmail.com"));
    }
    @Test
    @DisplayName("Test for verify if exist user by not existing email")
    public void givenNoSiptisUser_whenExistSiptisUserByEmail_thenFalse(){
        assertFalse(siptisUserRepository.existsByEmail("exampleEmail@gmail.com"));
    }

    @Test
    @DisplayName("Test for verify if exist users with role name")
    public void givenAdminSiptisUser_whenExistsByRolesName_thenTrue(){
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.existsByRolesName("ADMIN"));
    }
    @Test
    @DisplayName("Test for verify if exist users with not existing role name")
    public void givenStudentSiptisUser_whenExistsByRolesName_thenFalse(){
        siptisUserRepository.save(siptisUser);
        assertFalse(siptisUserRepository.existsByRolesName("STUDENT"));
    }

    @Test
    @DisplayName("Test find user with role name")
    public void givenAdminSiptisUser_whenFindOneByRolesName_thenSiptisUserObject(){
        siptisUserRepository.save(siptisUser);
        assertNotNull(siptisUserRepository.findOneByRolesName("ADMIN").get());
    }
    @Test
    @DisplayName("Test find not existing user with role name")
    public void givenNoSiptisUser_whenFindOneByRolesName_thenNull(){
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.findOneByRolesName("STUDENT").isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist user by id")
    public void givenSiptisUser_whenFindById_thenSiptisUserObject(){
        siptisUserRepository.save(siptisUser);
        assertNotNull(siptisUserRepository.findById(siptisUser.getId()).get());
    }
    @Test
    @DisplayName("Test for verify if exist non existing user by id")
    public void givenSiptisUser_whenFindById_thenNull(){
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.findById(123456789L).isEmpty());
    }

    @Test
    @DisplayName("Test for find user using id")
    public void givenSiptisUserId_whenFindById_thenSiptisUserObject(){
        siptisUserRepository.save(siptisUser);
        SiptisUser getUser = siptisUserRepository.findById(siptisUser.getId()).get();
        assertNotNull(getUser);
    }

    @Test
    @DisplayName("Test for verify if one exist user by id")
    public void givenSiptisUser_whenFindOneById_thenSiptisUserObject(){
        siptisUserRepository.save(siptisUser);
        assertNotNull(siptisUserRepository.findOneById(siptisUser.getId()).get());
    }
    @Test
    @DisplayName("Test for verify if exist non existing user by id")
    public void givenSiptisUser_existsById_thenNull(){
        siptisUserRepository.save(siptisUser);
        assertTrue(siptisUserRepository.findById(123456789L).isEmpty());
    }
    @Test
    @DisplayName("Test for verify if exist user using token password")
    public void givenSiptisUser_whenExistByTokenPassword_thenTrue(){
        siptisUserRepository.save(createNewUser());
        assertTrue(siptisUserRepository.existsByTokenPassword("abcd1234"));
    }
    @Test
    @DisplayName("Test for verify if exist user token password")
    public void givenSiptisUser_whenExistByTokenPassword_thenFalse(){
        siptisUserRepository.save(siptisUser);
        assertFalse(siptisUserRepository.existsByTokenPassword("1212121212"));
    }


}

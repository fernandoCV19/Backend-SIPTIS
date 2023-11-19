package backend.siptis.model.repository.auth;

import backend.siptis.auth.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("test for get allowed roles")
    public void givenRoles_whenGetallowedRoles_thenRolesList(){
        List<Role> roles = roleRepository.getAllowedRoles();
        assertNotNull(roles);
        assertEquals(4, roles.size() );
    }

    @Test
    @DisplayName("Test for get role by name.")
    public void givenRoleName_whenFindRoleByName_thenRoleObject(){
        assertNotNull(roleRepository.findRoleByName("ADMIN"));
    }
    @Test
    @DisplayName("Test for get role by non existing name.")
    public void givenRoleName_whenFindRoleByName_thenNull(){
        assertNull(roleRepository.findRoleByName("ADMIN123"));
    }

    @Test
    @DisplayName("Test for get role by id.")
    public void givenRoleId_whenFindRoleById_thenRoleObject(){
        assertNotNull(roleRepository.findRoleById(1L));
    }
    @Test
    @DisplayName("Test for get role by non existing id.")
    public void givenRoleId_whenFindRoleById_thenNull(){
        assertNull(roleRepository.findRoleById(1234546576L));
    }

    @Test
    @DisplayName("Test for verify if exist role by name.")
    public void givenRoleName_whenExistRoleByName_thenTrue(){
        assertTrue(roleRepository.existsRoleByName("ADMIN"));
    }
    @Test
    @DisplayName("Test for verify if exist role by non existing name.")
    public void givenRoleName_whenExistRoleByName_thenFalse(){
        assertFalse(roleRepository.existsRoleByName("ADMIN123"));
    }

    @Test
    @DisplayName("Test for verify if exist role by id.")
    public void givenRoleId_whenExistRoleById_thenTrue(){
        assertTrue(roleRepository.existsRoleById(1l));
    }
    @Test
    @DisplayName("Test for verify if exist role by non existing id.")
    public void givenRoleId_whenExistRoleById_thenFalse(){
        assertFalse(roleRepository.existsRoleById(1235467878l));
    }
}

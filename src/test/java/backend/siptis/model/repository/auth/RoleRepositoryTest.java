package backend.siptis.model.repository.auth;

import backend.siptis.auth.entity.Role;
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
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("test for get allowed roles")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenRoles_whenGetAllowedRoles_thenRolesList() {
        List<Role> roles = roleRepository.getAllowedRoles();
        assertNotNull(roles);
        assertEquals(4, roles.size());
    }

    @Test
    @DisplayName("test for get empty allowed roles list")
    void givenRoles_whenGetAllowedRoles_thenEmptyRolesList() {
        List<Role> roles = roleRepository.getAllowedRoles();
        assertNotNull(roles);
        assertEquals(0, roles.size());
    }

    @Test
    @DisplayName("Test for get role by name.")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenRoleName_whenFindRoleByName_thenRoleObject() {
        assertNotNull(roleRepository.findRoleByName("ADMIN"));
    }

    @Test
    @DisplayName("Test for get role by non existing name.")
    void givenRoleName_whenFindRoleByName_thenNull() {
        assertNull(roleRepository.findRoleByName("ADMIN123"));
    }

    @Test
    @DisplayName("Test for get role by id.")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenRoleId_whenFindRoleById_thenRoleObject() {
        assertNotNull(roleRepository.findRoleById(101L));
    }

    @Test
    @DisplayName("Test for get role by non existing id.")
    void givenRoleId_whenFindRoleById_thenNull() {
        assertNull(roleRepository.findRoleById(1234546576L));
    }

    @Test
    @DisplayName("Test for verify if exist role by name.")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenRoleName_whenExistRoleByName_thenTrue() {
        assertTrue(roleRepository.existsRoleByName("ADMIN"));
    }

    @Test
    @DisplayName("Test for verify if exist role by non existing name.")
    void givenRoleName_whenExistRoleByName_thenFalse() {
        assertFalse(roleRepository.existsRoleByName("ADMIN123"));
    }

    @Test
    @DisplayName("Test for verify if exist role by id.")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenRoleId_whenExistRoleById_thenTrue() {
        assertTrue(roleRepository.existsRoleById(101L));
    }

    @Test
    @DisplayName("Test for verify if exist role by non existing id.")
    void givenRoleId_whenExistRoleById_thenFalse() {
        assertFalse(roleRepository.existsRoleById(1235467878L));
    }
}

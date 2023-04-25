package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.SiptisUser;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserRepositoryTest {

    private final SiptisUserRepository siptisUserRepository;

    @Autowired
    SiptisUserRepositoryTest(SiptisUserRepository siptisUserRepository) {
        this.siptisUserRepository = siptisUserRepository;
    }

    @Test
    void findByRoleWithARoleThatExistReturnAListWithElements() {
        List<SiptisUser> res = siptisUserRepository.findByRolesName("TRIBUNAL");
        assertTrue(res.size() > 0);
    }

    @Test
    void findByRoleWithARoleThatDoesNotExistReturnAEmptyList() {
        List<SiptisUser> res = siptisUserRepository.findByRolesName("EMPTY");
        assertTrue(res.isEmpty());
    }

    @Test
    void findByRoleWithARoleThatExistReturnAListWithUsersThatHasThatRole() {
        List<SiptisUser> res = siptisUserRepository.findByRolesName("TRIBUNAL");
        boolean verify = res.stream().allMatch(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("TRIBUNAL")));
        assertTrue(verify);
    }
}
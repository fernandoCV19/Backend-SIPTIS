package backend.siptis.separateTests.model.repository;


import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.service.userData.SiptisUserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SiptisUserRepositoryTest {


    private final SiptisUserRepository siptisUserRepository;
    private final SiptisUserService siptisUserService;
    private AdminRegisterDTO adminDto = new AdminRegisterDTO();

    @Autowired
    SiptisUserRepositoryTest(SiptisUserRepository siptisUserRepository, SiptisUserService service) {
        this.siptisUserRepository = siptisUserRepository;
        this.siptisUserService = service;
        adminDto.setEmail("admin@gmail.com");
        adminDto.setPassword("123456789");
    }

    @Test
    void getEmptyAllUserList() {
        List<SiptisUser> answer = siptisUserRepository.findAll();
        assertEquals(answer.size(), 0);
    }

    @Test
    void getNotEmptyAllUserList() {
        siptisUserService.registerAdmin(adminDto);
        List<SiptisUser> answer = siptisUserRepository.findAll();
        assertEquals(answer.size(), 1);
    }

    @Test
    void findNonExistingUserWithEmail() {
        Optional<SiptisUser> answer = siptisUserRepository.findOneByEmail("admin@gmail.com");
        SiptisUser user = null;
        try {
            user = answer.get();
        } catch (Exception e) {
        }
        assertNull(user);
    }

    @Test
    void findExistingUserWithEmail() {
        siptisUserService.registerAdmin(adminDto);
        Optional<SiptisUser> answer = siptisUserRepository.findOneByEmail("admin@gmail.com");
        SiptisUser user = null;
        try {
            user = answer.get();
        } catch (Exception e) {
        }
        assertNotNull(answer.get());
    }

    @Test
    void findExistingUserWithEmailCheckEmail() {
        siptisUserService.registerAdmin(adminDto);
        SiptisUser user = siptisUserRepository.findOneByEmail("admin@gmail.com").get();
        assertEquals(user.getEmail(), "admin@gmail.com");
    }

    @Test
    void checkIfExistNonExistingUserWithEmail() {
        boolean answer = siptisUserRepository.existsByEmail("admin@gmail.com");
        assertFalse(answer);
    }

    @Test
    void checkIfExistExistingUserWithEmail() {
        siptisUserService.registerAdmin(adminDto);
        boolean answer = siptisUserRepository.existsByEmail("admin@gmail.com");
        assertTrue(answer);
    }

    @Test
    void checkIfExistNonExistingUserWithId() {
        boolean answer = siptisUserRepository.existsById(1L);
        assertFalse(answer);
    }

    @Test
    void checkIfExistExistingUserWithId() {
        siptisUserService.registerAdmin(adminDto);
        SiptisUser user = siptisUserRepository.findOneByEmail("admin@gmail.com").get();
        boolean answer = siptisUserRepository.existsById(user.getId());
        assertTrue(answer);
    }

    @Test
    void findEmptyListByRolesName() {
        List<SiptisUser> list = siptisUserRepository.findByRolesName("ADMIN");
        assertEquals(list.size(), 0);
    }

    @Test
    void findNotEmptyListByRolesName() {
        siptisUserService.registerAdmin(adminDto);
        List<SiptisUser> list = siptisUserRepository.findByRolesName("ADMIN");
        assertEquals(list.size(), 1);
    }


}

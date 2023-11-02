package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterAdminDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterStudentDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SiptisUserServiceTest {

    private final SiptisUserService siptisUserService;
    private RegisterAdminDTO adminDTO = new RegisterAdminDTO();
    private RegisterStudentDTO studentDTO = new RegisterStudentDTO();

    @Autowired
    public SiptisUserServiceTest(SiptisUserService siptisUserService){
        this.siptisUserService = siptisUserService;
    }

    private ServiceAnswer registerAdmin(){
        adminDTO.setEmail("admin@gmail.com");
        adminDTO.setPassword("12121212");
        return siptisUserService.registerAdmin(adminDTO);
    }
    private ServiceAnswer registerStudent(){
        studentDTO.setEmail("student@gmail.com");
        studentDTO.setNames("Name1 Name2");
        studentDTO.setLastnames("Lastname1 Lastname2");
        studentDTO.setCi("123456");
        studentDTO.setCelNumber("77774444");
        studentDTO.setCodSIS("202300123");
        studentDTO.setBirthDate(new Date());
        studentDTO.setCareer("INFORMATICA");
        return siptisUserService.registerStudent(studentDTO);
    }

    @Test
    public void registerAdminSuccessTest(){
        ServiceAnswer answer = registerAdmin();
        assertEquals(ServiceMessage.SUCCESSFUL_USER_REGISTER, answer.getServiceMessage());
    }

    @Test
    public void registerSecondAdminSuccessTest(){
        registerAdmin();
        adminDTO.setEmail("admin2@gmail.com");
        ServiceAnswer answer = siptisUserService.registerAdmin(adminDTO);
        assertEquals(ServiceMessage.SUCCESSFUL_USER_REGISTER, answer.getServiceMessage());
    }

    @Test
    public void registerAdminFailedAlreadyExistingEmailTest(){
        registerAdmin();
        ServiceAnswer answer = siptisUserService.registerAdmin(adminDTO);
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    public void registerStudentSuccessTest(){
        ServiceAnswer answer = registerStudent();
        assertEquals(ServiceMessage.SUCCESSFUL_USER_REGISTER, answer.getServiceMessage());
    }

    @Test
    public void registerStudentFailedAlreadyExistingEmailTest(){
        registerStudent();
        ServiceAnswer answer = siptisUserService.registerStudent(studentDTO);
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, answer.getServiceMessage());
    }
}

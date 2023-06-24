package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.service.records.EmailService;
import backend.siptis.service.userData.registerUser.RegisterUserService;
import backend.siptis.service.userData.userAuthentication.UserAuthService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SiptisUserServiceTest {

    private SiptisUserService service;
    private UserAuthService authService;
    private EmailService emailService;
    private RegisterUserService registerUserService;

    private StudentRegisterDTO createStudent(){
        String email = "maury.vargasl@gmail.com";
        String password = "mavl";
        String names = "maury";
        String lastnames = "vargas";
        String celNumber = "123123123";
        String ci = "232323";
        Date birthDate = new Date();
        String codSIS = "201900188";
        int career = 1;
        StudentRegisterDTO studentDTO = new StudentRegisterDTO();
        studentDTO.setEmail(email);
        studentDTO.setPassword(password);
        studentDTO.setNames(names);
        studentDTO.setLastnames(lastnames);
        studentDTO.setCelNumber(celNumber);
        studentDTO.setCi(ci);
        studentDTO.setBirthDate(birthDate);
        studentDTO.setCodSIS(codSIS);
        studentDTO.setCareer(career);
        return studentDTO;
    }

    private EditStudentInformationDTO editStudent(){
        String email = "maury.vargasl@gmail.com";
        String names = "maury";
        String lastnames = "vargas";
        String celNumber = "123123123";
        String ci = "232323";
        Date birthDate = new Date();
        String codSIS = "201900188";
        Long career = 1L;
        EditStudentInformationDTO studentDTO = new EditStudentInformationDTO();
        studentDTO.setEmail(email);
        studentDTO.setNames(names);
        studentDTO.setLastnames(lastnames);
        studentDTO.setCelNumber(celNumber);
        studentDTO.setCi(ci);
        studentDTO.setBirthDate(birthDate);
        studentDTO.setCodSIS(codSIS);
        studentDTO.setCareer(career);
        return studentDTO;
    }

    private AdminRegisterDTO createAdmin(){
        String email = "admin@gmail.com";
        String password = "mavl";
        AdminRegisterDTO adminDTO = new AdminRegisterDTO();
        adminDTO.setEmail(email);
        adminDTO.setPassword(password);

        return adminDTO;
    }

    @Autowired
    SiptisUserServiceTest(
            SiptisUserService service, UserAuthService authService,
            EmailService emailService) {
        this.service = service;
        this.authService = authService;
        this.emailService = emailService;
    }

    @Test
    void getByEmailThatNoExistTest(){
        StudentRegisterDTO studentDTO = createStudent();
        registerUserService.registerStudent(studentDTO);
        Boolean answer = service.existsByEmail("fake@gmail.com");
        assertFalse(answer);
    }

    @Test
    void getByEmailThatExistTest(){
        StudentRegisterDTO studentDTO = createStudent();
        registerUserService.registerStudent(studentDTO);
        Boolean answer = service.existsByEmail("maury.vargasl@gmail.com");
        assertTrue(answer);
    }

    @Test
    void editStudentInformationIdNoExist(){
        StudentRegisterDTO studentDTO = createStudent();
        registerUserService.registerStudent(studentDTO);
        LogInDTO loginDTO = new LogInDTO();
        loginDTO.setEmail("maury.vargasl@gmail.com");
        loginDTO.setPassword("mavl");
        ServiceAnswer answer = authService.logIn(loginDTO);
        String token = (String) answer.getData();
        Long id = authService.getIdFromToken(token);
        EditStudentInformationDTO editInfoDTO = editStudent();
        ServiceAnswer answer2 = service.editStudentInformation(id, editInfoDTO);
        assertEquals(ServiceMessage.OK, answer2.getServiceMessage());

    }
}

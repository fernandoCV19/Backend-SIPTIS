package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserAuthServiceTest {

    private UserAuthService service;


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

    private AdminRegisterDTO createAdmin(){
        String email = "admin@gmail.com";
        String password = "mavl";
        AdminRegisterDTO adminDTO = new AdminRegisterDTO();
        adminDTO.setEmail(email);
        adminDTO.setPassword(password);

        return adminDTO;
    }


    @Autowired
    UserAuthServiceTest(UserAuthService service) {
        this.service = service;
    }

    @Test
    void findAllTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        ServiceAnswer answer = service.findAll();
        assertEquals(ServiceMessage.OK,answer.getServiceMessage());
    }

    @Test
    void findAllEmptyTest(){
        ServiceAnswer answer = service.findAll();
        assertEquals(ServiceMessage.OK,answer.getServiceMessage());
    }
    @Test
    void registerNewStudentTest(){

        StudentRegisterDTO studentDTO = createStudent();
        ServiceAnswer answer = service.registerStudent(studentDTO);
        assertEquals(ServiceMessage.OK,answer.getServiceMessage());
    }

    @Test
    void registerStudentEmailAlreadyExistTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        StudentRegisterDTO studentDTO2 = createStudent();
        ServiceAnswer answer2 = service.registerStudent(studentDTO2);
        assertEquals(ServiceMessage.ERROR_REGISTRO_CUENTA_EMAIL,answer2.getServiceMessage());
    }

    @Test
    void registerStudentCiAlreadyExistTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        StudentRegisterDTO studentDTO2 = createStudent();
        studentDTO2.setEmail("new.email@gmail.com");
        ServiceAnswer answer2 = service.registerStudent(studentDTO2);
        assertEquals(ServiceMessage.ERROR_REGISTRO_CUENTA_CI,answer2.getServiceMessage());
    }

    @Test
    void registerStudentCodSISAlreadyExistTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        StudentRegisterDTO studentDTO2 = createStudent();
        studentDTO2.setEmail("new.email2@gmail.com");
        studentDTO2.setCi("newCi");
        ServiceAnswer answer2 = service.registerStudent(studentDTO2);
        assertEquals(ServiceMessage.ERROR_REGISTRO_CUENTA_CODSIS,answer2.getServiceMessage());
    }

    @Test
    void registerNewAdminTest(){

        AdminRegisterDTO adminDTO = createAdmin();
        ServiceAnswer answer = service.registerAdmin(adminDTO);
        assertEquals(ServiceMessage.OK,answer.getServiceMessage());
    }

    @Test
    void registerAdminEmailAlreadyExistTest(){
        AdminRegisterDTO adminDTO = createAdmin();
        service.registerAdmin(adminDTO);
        AdminRegisterDTO adminDTO2 = createAdmin();
        ServiceAnswer answer = service.registerAdmin(adminDTO2);
        assertEquals(ServiceMessage.ERROR_REGISTRO_CUENTA_EMAIL,answer.getServiceMessage());
    }

    @Test
    void userInfoUserExistTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        LogInDTO loginDTO = new LogInDTO();
        loginDTO.setEmail("maury.vargasl@gmail.com");
        loginDTO.setPassword("mavl");
        ServiceAnswer answer = service.logIn(loginDTO);
        String token = (String) answer.getData();
        Long id = service.getIdFromToken(token);
        ServiceAnswer answer2 = service.userInfo(id);
        assertEquals(ServiceMessage.OK, answer2.getServiceMessage());
    }

    @Test
    void userInfoUserNotExistTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        ServiceAnswer answer = service.userInfo(2L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    void loginTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        LogInDTO loginDTO = new LogInDTO();
        loginDTO.setEmail("maury.vargasl@gmail.com");
        loginDTO.setPassword("mavl");
        ServiceAnswer answer = service.logIn(loginDTO);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    void loginWrongEmailTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        LogInDTO loginDTO = new LogInDTO();
        loginDTO.setEmail("fake@gmail.com");
        loginDTO.setPassword("mavl");
        ServiceAnswer answer = service.logIn(loginDTO);
        assertEquals(ServiceMessage.ERROR_INICIO_SESION_EMAIL, answer.getServiceMessage());
    }

    @Test
    void loginWrongPasswordTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        LogInDTO loginDTO = new LogInDTO();
        loginDTO.setEmail("maury.vargasl@gmail.com");
        loginDTO.setPassword("mavl1");
        ServiceAnswer answer = service.logIn(loginDTO);
        assertEquals(ServiceMessage.ERROR_INICIO_SESION_PASSWORD, answer.getServiceMessage());
    }

    @Test
    void getIdFromTokenTest(){
        StudentRegisterDTO studentDTO = createStudent();
        service.registerStudent(studentDTO);
        LogInDTO loginDTO = new LogInDTO();
        loginDTO.setEmail("maury.vargasl@gmail.com");
        loginDTO.setPassword("mavl");
        ServiceAnswer answer = service.logIn(loginDTO);
        String token = (String) answer.getData();
        Long id = service.getIdFromToken(token);
        assertEquals(3L, id);
    }
}

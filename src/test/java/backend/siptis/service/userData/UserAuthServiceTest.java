package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
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

    @Autowired
    UserAuthServiceTest(UserAuthService service) {
        this.service = service;
    }

    @Test
    void registerNewStudentTest(){
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
        ServiceAnswer answer = service.registerStudent(studentDTO);
        assertEquals(ServiceMessage.OK,answer.getServiceMessage());
    }
}

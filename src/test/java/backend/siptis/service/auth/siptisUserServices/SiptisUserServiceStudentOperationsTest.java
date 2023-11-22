package backend.siptis.service.auth.siptisUserServices;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterStudentDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceStudentOperationsTest {
    @Autowired
    private SiptisUserServiceStudentOperations siptisUserServiceStudentOperations;
    private RegisterStudentDTO registerStudentDTO;

    private void createRegisterStudentDTO(){
        registerStudentDTO = new RegisterStudentDTO();
        registerStudentDTO.setEmail("estudiante1@gmail.com");
    }
    @Test
    @DisplayName("test for register student")
    @Sql(scripts = {"/custom_imports/create_users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenWhenThenRegisterStudentServiceMessageEMAIL_ALREADY_EXIST(){
        createRegisterStudentDTO();
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, siptisUserServiceStudentOperations.registerStudent(registerStudentDTO).getServiceMessage());
    }
    @Test
    @DisplayName("test gest number of students by year and career")
    void givenCareerIdWhenGetNumberOfStudentsByYearAndCareerThenServiceMessageOK(){
        assertEquals(ServiceMessage.OK, siptisUserServiceStudentOperations.getNumberOfStudentsByYearAndCareer(1L).getServiceMessage());
    }
    @Test
    @DisplayName("test gest number of students by career")
    void givenCareerIdWhenGetNumberOfStudentsByCareerThenServiceMessageOK(){
        assertEquals(ServiceMessage.OK, siptisUserServiceStudentOperations.getNumberStudentsCareer(1L).getServiceMessage());
    }
    @Test
    @DisplayName("test get student career by id")
    void givenUserIdWhenThenGetStudentCareerByIdServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceStudentOperations.getStudentCareerById(1234567L).getServiceMessage());
    }

}

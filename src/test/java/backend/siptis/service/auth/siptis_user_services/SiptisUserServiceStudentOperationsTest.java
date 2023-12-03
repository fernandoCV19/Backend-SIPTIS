package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.RegisterStudentDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SiptisUserServiceStudentOperationsTest {
    @Autowired
    private SiptisUserServiceStudentOperations siptisUserServiceStudentOperations;
    private RegisterStudentDTO registerStudentDTO;

    private void createRegisterStudentDTO() {
        registerStudentDTO = new RegisterStudentDTO();
        registerStudentDTO.setEmail("estudiante1@gmail.com");
        registerStudentDTO.setNames("userName");
        registerStudentDTO.setLastnames("userLastName");
        registerStudentDTO.setCelNumber("3428324");
        registerStudentDTO.setBirthDate(new Date());
        registerStudentDTO.setCi("4565656");
        registerStudentDTO.setCodSIS("23232323");
        registerStudentDTO.setCareer("INFORMATICA");
    }

    @Test
    @DisplayName("test for register student")
    void givenWhenThenRegisterStudentServiceMessageERROR() {
        createRegisterStudentDTO();
        assertEquals(ServiceMessage.ERROR, siptisUserServiceStudentOperations.registerStudent(registerStudentDTO).getServiceMessage());
    }

    @Test
    @DisplayName("test for register student success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenWhenThenRegisterStudentServiceMessageSUCCESSFUL_REGISTER() {
        createRegisterStudentDTO();
        assertEquals(ServiceMessage.SUCCESSFUL_REGISTER, siptisUserServiceStudentOperations.registerStudent(registerStudentDTO).getServiceMessage());
    }

    @Test
    @DisplayName("test gest number of students by year and career error")
    void givenWrongCareerNameWhenGetNumberOfStudentsByYearAndCareerThenServiceMessageERROR() {
        assertEquals(ServiceMessage.ERROR, siptisUserServiceStudentOperations.getNumberOfStudentsByYearAndCareer("INFOR MATICA").getServiceMessage());
    }

    @Test
    @DisplayName("test gest number of students by year and career success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenWrongCareerNameWhenGetNumberOfStudentsByYearAndCareerThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceStudentOperations.getNumberOfStudentsByYearAndCareer("INFORMATICA").getServiceMessage());
    }

    @Test
    @DisplayName("test get number of students by career error")
    void givenWrongCareerNameWhenGetNumberOfStudentsByCareerThenServiceMessageERROR() {
        assertEquals(ServiceMessage.ERROR, siptisUserServiceStudentOperations.getNumberStudentsCareer("INFORM ATICA").getServiceMessage());
    }

    @Test
    @DisplayName("test get number of students by career success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenWrongCareerNameWhenGetNumberOfStudentsByCareerThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceStudentOperations.getNumberStudentsCareer("INFORMATICA").getServiceMessage());
    }

    @Test
    @DisplayName("test get student career by id")
    void givenUserIdWhenThenGetStudentCareerByIdServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceStudentOperations.getStudentCareerById(1234567L).getServiceMessage());
    }

    @Test
    @DisplayName("test get student career by id")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserIdWhenThenGetStudentCareerByIdServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceStudentOperations.getStudentCareerById(100L).getServiceMessage());
    }


}

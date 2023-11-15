package backend.siptis.service.userData;

import backend.siptis.commons.Roles;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.pjo.dto.userDataDTO.*;
import backend.siptis.service.auth.SiptisUserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SiptisUserServiceTest {

    private final SiptisUserService siptisUserService;
    private final LogInDTO logInDTO = new LogInDTO();
    private final RegisterAdminDTO adminDTO = new RegisterAdminDTO();
    private final RegisterStudentDTO studentDTO = new RegisterStudentDTO();
    private final RegisterUserDTO userDTO = new RegisterUserDTO();
    private final RolesListDTO rolesListDTO = new RolesListDTO();
    private final UserEditInformationDTO userEditInformationDTO = new UserEditInformationDTO();
    private final AdminEditUserInformationDTO adminEditUserInformationDTO = new AdminEditUserInformationDTO();
    private final AdminEditStudentInformationDTO adminEditStudentInformationDTO = new AdminEditStudentInformationDTO();

    @Autowired
    public SiptisUserServiceTest(SiptisUserService siptisUserService) {
        this.siptisUserService = siptisUserService;
    }

    private ServiceAnswer registerAdmin() {
        adminDTO.setEmail("admin@gmail.com");
        adminDTO.setPassword("12121212");
        return siptisUserService.registerAdmin(adminDTO);
    }

    private ServiceAnswer registerStudent() {
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

    private ServiceAnswer registerStudent2() {
        studentDTO.setEmail("student2@gmail.com");
        studentDTO.setNames("Name1 Name2");
        studentDTO.setLastnames("Lastname1 Lastname2");
        studentDTO.setCi("4444444");
        studentDTO.setCelNumber("77774444");
        studentDTO.setCodSIS("202300124");
        studentDTO.setBirthDate(new Date());
        studentDTO.setCareer("SISTEMAS");
        return siptisUserService.registerStudent(studentDTO);
    }

    private ServiceAnswer registerGeneralUser() {
        userDTO.setEmail("student@gmail.com");
        userDTO.setNames("Name1 Name2");
        userDTO.setLastnames("Lastname1 Lastname2");
        userDTO.setCi("77843322");
        userDTO.setCelNumber("77774444");
        userDTO.setBirthDate(new Date());
        return siptisUserService.registerUser(userDTO);
    }

    private ServiceAnswer userEditInfo() {
        userEditInformationDTO.setBirthDate(new Date());
        userEditInformationDTO.setEmail("newmail@gmail.com");
        userEditInformationDTO.setCelNumber("6755556667");
        return siptisUserService.userEditPersonalInformation(1L, userEditInformationDTO);
    }

    private ServiceAnswer adminEditStudentInfo() {
//        adminEditStudentInformationDTO.setNames("Name Updated");
//        adminEditStudentInformationDTO.setLastnames("Last name Updated");
//        adminEditStudentInformationDTO.setEmail("newmail@gmail.com");
//        adminEditStudentInformationDTO.setCi("54555543");
//        adminEditStudentInformationDTO.setCelNumber("77743874");
//        adminEditStudentInformationDTO.setCodSIS("202400188");
//        adminEditStudentInformationDTO.setBirthDate(new Date());
//        return siptisUserService.adminEditStudentInformation(1L, adminEditStudentInformationDTO);
        return null;
    }

    private ServiceAnswer updateRoles(Long id) {
        List<Long> roles = List.of(4L, 5L);
        rolesListDTO.setRoles(roles);
        return siptisUserService.updateRoles(id, rolesListDTO);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void logInAdminSuccessTest() {
        registerAdmin();
        logInDTO.setEmail("admin@gmail.com");
        logInDTO.setPassword("12121212");
        ServiceAnswer answer = siptisUserService.logIn(logInDTO);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void logInAdminFailWrongEmailTest() {
        registerAdmin();
        logInDTO.setEmail("admin123@gmail.com");
        logInDTO.setPassword("12121212");
        ServiceAnswer answer = siptisUserService.logIn(logInDTO);
        assertEquals(ServiceMessage.ERROR_BAD_CREDENTIALS, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void logInAdminFailWrongPasswordTest() {
        registerAdmin();
        logInDTO.setEmail("admin@gmail.com");
        logInDTO.setPassword("123123");
        ServiceAnswer answer = siptisUserService.logIn(logInDTO);
        assertEquals(ServiceMessage.ERROR_BAD_CREDENTIALS, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerAdminSuccessTest() {
        ServiceAnswer answer = registerAdmin();
        assertEquals(ServiceMessage.SUCCESSFUL_REGISTER, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerSecondAdminSuccessTest() {
        registerAdmin();
        adminDTO.setEmail("admin2@gmail.com");
        ServiceAnswer answer = siptisUserService.registerAdmin(adminDTO);
        assertEquals(ServiceMessage.SUCCESSFUL_REGISTER, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerGeneralUserSuccessTest() {
        ServiceAnswer answer = registerGeneralUser();
        assertEquals(ServiceMessage.SUCCESSFUL_REGISTER, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerStudentSuccessTest() {
        ServiceAnswer answer = registerStudent();
        assertEquals(ServiceMessage.SUCCESSFUL_REGISTER, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerAdminFailedAlreadyExistingEmailTest() {
        registerAdmin();
        ServiceAnswer answer = siptisUserService.registerAdmin(adminDTO);
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerStudentFailedAlreadyExistingEmailTest() {
        registerStudent();
        ServiceAnswer answer = siptisUserService.registerStudent(studentDTO);
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerStudentFailedAlreadyExistingCiTest() {
        registerStudent();
        studentDTO.setEmail("newStudent@gmail.com");
        ServiceAnswer answer = siptisUserService.registerStudent(studentDTO);
        assertEquals(ServiceMessage.CI_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerStudentFailedAlreadyExistingCodSISTest() {
        registerStudent();
        studentDTO.setEmail("newStudent@gmail.com");
        studentDTO.setCi("123123123");
        ServiceAnswer answer = siptisUserService.registerStudent(studentDTO);
        assertEquals(ServiceMessage.COD_SIS_ALREADY_EXIST, answer.getServiceMessage());
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteUserSuccessTest() {
        registerAdmin();
        ServiceAnswer answer = siptisUserService.deleteUser(1L);
        assertEquals(ServiceMessage.USER_DELETED, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteUserFailedNotFoundUserTest() {
        ServiceAnswer answer = siptisUserService.deleteUser(1L);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Sql(scripts = {"/custom_imports/siptisUserTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deleteUserFailedUserCannotBeDeletedTest() {
        ServiceAnswer answer = siptisUserService.deleteUser(1L);
        assertEquals(ServiceMessage.ERROR_CANNOT_DELETE_USER, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getUserPersonalInformationSuccessTest() {
        registerStudent();
        ServiceAnswer answer = siptisUserService.getUserPersonalInformation(1L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getUserPersonalInformationFailNotFoundUserTest() {
        ServiceAnswer answer = siptisUserService.getUserPersonalInformation(1L);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getUserPersonalInformationFailNotFoundUserInformationTest() {
        registerAdmin();
        ServiceAnswer answer = siptisUserService.getUserPersonalInformation(1L);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getStudentCareerByIdSuccessTest() {
        registerStudent();
        ServiceAnswer answer = siptisUserService.getStudentCareerById(1L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getStudentCareerByIdFailNotFoundUserTest() {
        ServiceAnswer answer = siptisUserService.getStudentCareerById(1L);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getStudentCareerByIdSuccessNotCareersTest() {
        registerAdmin();
        ServiceAnswer answer = siptisUserService.getStudentCareerById(1L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getUserAreasByIdSuccessTest() {
        registerGeneralUser();
        ServiceAnswer answer = siptisUserService.getUserAreasById(1L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getUserAreasByIdFailNotFoundUserTest() {
        ServiceAnswer answer = siptisUserService.getUserAreasById(1L);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updateTokenFailNotFoundTokenTest() {
        String token = "0816a7f9-1aa5-411a-9f83-b97622e94669";
        ServiceAnswer answer = siptisUserService.updateToken(token);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Sql(scripts = {"/custom_imports/siptisUserTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void updateTokenFailExpiredTokenTest() {
        String token = "0816a7f9-1aa5-411a-9f83-b97622e94669";
        ServiceAnswer answer = siptisUserService.updateToken(token);
        assertEquals(ServiceMessage.EXPIRED_REFRESH_TOKEN, answer.getServiceMessage());
    }
    //UPDATE TOKEN

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getRolesByIdSuccessTest() {
        registerAdmin();
        ServiceAnswer answer = siptisUserService.getRolesById(1L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getRolesByIdFailNotFoundTest() {
        ServiceAnswer answer = siptisUserService.getRolesById(1L);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updateRolesSuccessTest() {
        registerGeneralUser();
        ServiceAnswer answer = updateRoles(1L);
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updateRolesFailStudentUserTest() {
        registerStudent();
        ServiceAnswer answer = updateRoles(1L);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updateRolesFailAdminUserTest() {
        registerAdmin();
        ServiceAnswer answer = updateRoles(1L);
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updateRolesFailRoleNotFoundTest() {
        registerGeneralUser();
        rolesListDTO.setRoles(List.of(4L, 123L, 5L));
        ServiceAnswer answer = siptisUserService.updateRoles(1L, rolesListDTO);
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void userEditPersonalInformationSuccessTest() {
        registerStudent();
        ServiceAnswer answer = userEditInfo();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void userEditPersonalInformationFailUserNotFoundTest() {
        registerStudent();
        ServiceAnswer answer = siptisUserService.userEditPersonalInformation(123L, userEditInformationDTO);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void userEditPersonalInformationFailEmailAlreadyExistTest() {
        registerStudent();
        registerAdmin();
        userEditInfo();
        userEditInformationDTO.setEmail("admin@gmail.com");
        ServiceAnswer answer = siptisUserService.userEditPersonalInformation(1L, userEditInformationDTO);
        assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void setAdminEditStudentInformationSuccessTest() {
        registerStudent();
        ServiceAnswer answer = adminEditStudentInfo();
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void setAdminEditStudentInformationFailUserNotFoundTest() {
        ServiceAnswer answer = adminEditStudentInfo();
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    /*
        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void setAdminEditStudentInformationFailEmailAlreadyExistTest() {
            registerStudent();
            registerAdmin();
            adminEditStudentInfo();
            adminEditStudentInformationDTO.setEmail("admin@gmail.com");
            ServiceAnswer answer = siptisUserService.adminEditStudentInformation(1L, adminEditStudentInformationDTO);
            assertEquals(ServiceMessage.EMAIL_ALREADY_EXIST, answer.getServiceMessage());
        }

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void adminEditStudentInformationFailCodSISAlreadyExistTest() {
            registerStudent();
            registerStudent2();
            adminEditStudentInfo();
            adminEditStudentInformationDTO.setCodSIS("202300124");
            ServiceAnswer answer = siptisUserService.adminEditStudentInformation(1L, adminEditStudentInformationDTO);
            assertEquals(ServiceMessage.COD_SIS_ALREADY_EXIST, answer.getServiceMessage());
        }

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void adminEditStudentInformationFailCiAlreadyExistTest() {
            registerStudent();
            registerStudent2();
            adminEditStudentInfo();
            adminEditStudentInformationDTO.setCi("4444444");
            ServiceAnswer answer = siptisUserService.adminEditStudentInformation(1L, adminEditStudentInformationDTO);
            assertEquals(ServiceMessage.CI_ALREADY_EXIST, answer.getServiceMessage());
        }
    */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerUserAsCareerDirectorSuccessTest() {
        registerGeneralUser();
        ServiceAnswer answer = siptisUserService.registerUserAsCareerDirector(1L, Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerUserAsCareerDirectorFailUserNotFoundTest() {
        registerGeneralUser();
        ServiceAnswer answer = siptisUserService.registerUserAsCareerDirector(1123L, Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerUserAsCareerDirectorFailCannotAssignStudentTest() {
        registerStudent();
        ServiceAnswer answer = siptisUserService.registerUserAsCareerDirector(1L, Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.CANNOT_ASSIGN_ROLE, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerUserAsCareerDirectorFailCannotAssignAdminTest() {
        registerAdmin();
        ServiceAnswer answer = siptisUserService.registerUserAsCareerDirector(1L, Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.CANNOT_ASSIGN_ROLE, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void registerUserAsCareerDirectorFailDirectorAlreadyExistTest() {
        registerGeneralUser();
        siptisUserService.registerUserAsCareerDirector(1L, Roles.INF_DIRECTOR.toString());
        ServiceAnswer answer = siptisUserService.registerUserAsCareerDirector(1L, Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.DIRECTOR_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getDirectorPersonalInformationSuccessTest() {
        registerGeneralUser();
        siptisUserService.registerUserAsCareerDirector(1L, Roles.INF_DIRECTOR.toString());
        ServiceAnswer answer = siptisUserService.getDirectorPersonalInformation(Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getDirectorPersonalInformationFailDirectorNotFoundTest() {
        ServiceAnswer answer = siptisUserService.getDirectorPersonalInformation(Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void removeDirectorSuccessTest() {
        registerGeneralUser();
        siptisUserService.registerUserAsCareerDirector(1L, Roles.INF_DIRECTOR.toString());
        ServiceAnswer answer = siptisUserService.removeDirectorRole(Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.OK, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void removeDirectorFailNotFoundTest() {
        ServiceAnswer answer = siptisUserService.removeDirectorRole(Roles.INF_DIRECTOR.toString());
        assertEquals(ServiceMessage.NOT_FOUND, answer.getServiceMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void existCareerDirectorSuccessTest() {
        registerGeneralUser();
        siptisUserService.registerUserAsCareerDirector(1L, Roles.INF_DIRECTOR.toString());
        boolean answer = siptisUserService.existCareerDirector(Roles.INF_DIRECTOR.toString());
        assertTrue(answer);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void existCareerDirectorFailNotFounTest() {
        boolean answer = siptisUserService.existCareerDirector(Roles.INF_DIRECTOR.toString());
        assertFalse(answer);
    }

}

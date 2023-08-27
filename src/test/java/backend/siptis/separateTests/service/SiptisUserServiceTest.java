package backend.siptis.separateTests.service;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterSpecialUserDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
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


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SiptisUserServiceTest {

    private final SiptisUserService siptisUserService;
    private final SiptisUserRepository siptisUserRepository;
    private static AdminRegisterDTO adminDTO = new AdminRegisterDTO();
    private static RegisterStudentDTO studentDTO = new RegisterStudentDTO();
    private static RegisterUserDTO teacherDTO = new RegisterUserDTO();
    private static RegisterSpecialUserDTO specialUserDTO = new RegisterSpecialUserDTO();

    @Autowired
    public SiptisUserServiceTest(SiptisUserService siptisUserService, SiptisUserRepository siptisUserRepository){
        this.siptisUserService = siptisUserService;
        this.siptisUserRepository = siptisUserRepository;

        adminDTO.setEmail("admin@gmail.com");
        adminDTO.setPassword("12121212");

        studentDTO.setNames("Estudiante 1");
        studentDTO.setLastnames("Prueba 1");
        studentDTO.setEmail("student@gmail.com");
        studentDTO.setPassword("12121212");
        studentDTO.setCelNumber("777666777");
        // studentDTO.setBirthDate(new SimpleDateFormat("2022-10-31"));
        studentDTO.setCi("123123123");
        studentDTO.setCodSIS("201900188");
        studentDTO.setCareer("INFORMATICA");

        teacherDTO.setNames("Docente 1");
        teacherDTO.setLastnames("Prueba 1");
        teacherDTO.setEmail("teacher@gmail.com");
        teacherDTO.setPassword("12121212");
        teacherDTO.setCelNumber("777666777");
        // teacherDTO.setBirthDate(new SimpleDateFormat("2022-10-31"));
        teacherDTO.setCi("2333234");
        teacherDTO.setCodSIS("20070031");


        specialUserDTO.setNames("SpecialUser 1");
        specialUserDTO.setLastnames("Prueba 1");
        specialUserDTO.setEmail("specialUser@gmail.com");
        specialUserDTO.setPassword("12121212");
        specialUserDTO.setCelNumber("777666777");
        // specialUserDTO.setBirthDate(new SimpleDateFormat("2022-10-31"));
        specialUserDTO.setCi("55699963");

    }

/*
    @Test
    void findById() {
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertNotNull(activityVO);
    }
    */
    @Test
    void registerStudentTest(){
        ServiceAnswer answer = siptisUserService.registerStudent(studentDTO);
        assertEquals(answer.getServiceMessage(), ServiceMessage.OK);
    }

    @Test
    void checkIfExistRegisteredStudentTest(){
        ServiceAnswer answer = siptisUserService.registerStudent(studentDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("student@gmail.com").get();
        assertNotNull(user);
    }
    @Test
    void checkRegisteredStudentInformationCiTest(){
        siptisUserService.registerStudent(studentDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("student@gmail.com").get();
        UserInformation information = user.getUserInformation();
        assertEquals(information.getCi(), "123123123");
    }
    @Test
    void checkRegisteredStudentInformationCodSisTest(){
        ServiceAnswer answer = siptisUserService.registerStudent(studentDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("student@gmail.com").get();
        UserInformation information = user.getUserInformation();
        assertEquals(information.getCodSIS(), "201900188");
    }
    @Test
    void checkRegisteredStudentCareerTest(){
        siptisUserService.registerStudent(studentDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("student@gmail.com").get();
        Set<UserCareer> career = user.getCareer();
        assertEquals(career.size(), 1);
    }


    @Test
    void registerSpecialUserTest(){
        ServiceAnswer answer = siptisUserService.registerSpecialUser(specialUserDTO);
        assertEquals(answer.getServiceMessage(), ServiceMessage.OK);
    }
    @Test
    void checkIfExistRegisteredSpecialUserTest(){
        siptisUserService.registerSpecialUser(specialUserDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("specialUser@gmail.com").get();
        assertNotNull(user);
    }
    @Test
    void checkRegisteredSpecialUserInformationCiTest(){
        siptisUserService.registerSpecialUser(specialUserDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("specialUser@gmail.com").get();
        UserInformation information = user.getUserInformation();
        assertEquals(information.getCi(), "55699963");
    }
    @Test
    void checkRegisteredSpecialUserInformationCodSisTest(){
        siptisUserService.registerSpecialUser(specialUserDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("specialUser@gmail.com").get();
        UserInformation information = user.getUserInformation();
        assertNull(information.getCodSIS());
    }


    @Test
    void registerTeacherTest(){
        ServiceAnswer answer = siptisUserService.registerTeacher(teacherDTO);
        assertEquals(answer.getServiceMessage(), ServiceMessage.OK);
    }
    @Test
    void checkIfExistRegisteredTeacherTest(){
        siptisUserService.registerTeacher(teacherDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("teacher@gmail.com").get();
        assertNotNull(user);
    }
    @Test
    void checkRegisteredTeacherInformationCiTest(){
        siptisUserService.registerTeacher(teacherDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("teacher@gmail.com").get();
        UserInformation information = user.getUserInformation();
        assertEquals(information.getCi(), "2333234");
    }
    @Test
    void checkRegisteredTeacherInformationCodSisTest(){
        siptisUserService.registerTeacher(teacherDTO);
        SiptisUser user = siptisUserRepository.findOneByEmail("teacher@gmail.com").get();
        UserInformation information = user.getUserInformation();
        assertEquals(information.getCodSIS(), "20070031");
    }

    @Test
    void loginNonExistingUserTest(){

    }



}

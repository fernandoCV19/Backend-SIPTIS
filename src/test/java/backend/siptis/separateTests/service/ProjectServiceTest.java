package backend.siptis.separateTests.service;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.projectManagement.NewProjectDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.service.projectManagement.ProjectService;
import backend.siptis.service.userData.SiptisUserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ProjectServiceTest {

    private final ProjectService projectService;
    private final SiptisUserService siptisUserService;
    private final SiptisUserRepository siptisUserRepository;
    private static RegisterUserDTO teacherDTO = new RegisterUserDTO();
    private static NewProjectDTO newProjectDTO = new NewProjectDTO();
    private Long studentId;
    private Long teacherId;

    @Autowired
    public ProjectServiceTest(ProjectService projectService,
                              SiptisUserService siptisUserService, SiptisUserRepository siptisUserRepository){
        this.projectService = projectService;
        this.siptisUserService = siptisUserService;
        this.siptisUserRepository = siptisUserRepository;
/*
        studentDTO.setNames("Estudiante 1");
        studentDTO.setLastnames("Prueba 1");
        studentDTO.setEmail("student@gmail.com");
        studentDTO.setPassword("12121212");
        studentDTO.setCelNumber("777666777");
        // studentDTO.setBirthDate(new SimpleDateFormat("2022-10-31"));
        studentDTO.setCi("123123123");
        studentDTO.setCodSIS("201900188");
        studentDTO.setCareer("INFORMATICA");
*/
        teacherDTO.setNames("Docente 1");
        teacherDTO.setLastnames("Prueba 1");
        teacherDTO.setEmail("teacher@gmail.com");
        teacherDTO.setPassword("12121212");
        teacherDTO.setCelNumber("777666777");
        // teacherDTO.setBirthDate(new SimpleDateFormat("2022-10-31"));
        teacherDTO.setCi("2333234");
        teacherDTO.setCodSIS("20070031");
/*
        siptisUserService.registerStudent(studentDTO);
        siptisUserService.registerTeacher(teacherDTO);

        SiptisUser user = siptisUserRepository.findOneByEmail("student@gmail.com").get();
        studentId = user.getId();

        user = siptisUserRepository.findOneByEmail("teacher@gmail.com").get();
        teacherId = user.getId();

        newProjectDTO.setName("Proyecto Prueba Test");
        newProjectDTO.setModalityId(1l);
        ArrayList<Long> studentsId = new ArrayList<>();
        studentsId.add(studentId);
        ArrayList<Long> tutorsId = new ArrayList<>();
        studentsId.add(teacherId);
        newProjectDTO.setStudentsId(studentsId);
        newProjectDTO.setTutorsId(tutorsId);*/
    }

    @Test
    void getEmptyProjectListTest(){
        ServiceAnswer answer = projectService.getProjects();
        List<Project> projects = (List<Project>) answer.getData();
        assertEquals(0, 0);
    }

    @Test
    void createProjectTest(){
        projectService.createProject(newProjectDTO);ServiceAnswer answer = projectService.getProjects();
        List<Project> projects = (List<Project>) answer.getData();
        assertEquals(projects.size(), 1);
    }

    @Test
    void getNotEmptyProjectListTest(){
        ServiceAnswer answer = projectService.getProjects();
        List<Project> projects = (List<Project>) answer.getData();
        assertEquals(projects.size(), 0);
    }
}

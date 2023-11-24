package backend.siptis.service.projectManagement.project;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.project_management.NewProjectDTO;
import backend.siptis.service.project_management.project.ProjectServiceCreate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Role;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectServiceCreateTest {

    private final ProjectServiceCreate projectServiceCreate;
    private NewProjectDTO NEW_PROJECT_DTO = new NewProjectDTO();

    @BeforeEach
    void setUp() {
        NEW_PROJECT_DTO.setName("Test Project");
        NEW_PROJECT_DTO.setModalityId(102L);
        List<Long> studentsId = new ArrayList<>();
        studentsId.add(101L);
        studentsId.add(102L);
        NEW_PROJECT_DTO.setStudentsId(studentsId);
        List<Long> tutorsId = new ArrayList<>();
        tutorsId.add(130L);
        tutorsId.add(131L);
        NEW_PROJECT_DTO.setTutorsId(tutorsId);
        List<Long> supervisorsId = new ArrayList<>();
        supervisorsId.add(140L);
        supervisorsId.add(141L);
        NEW_PROJECT_DTO.setSupervisorsId(supervisorsId);
        List<Long> teachersId = new ArrayList<>();
        teachersId.add(120L);
        teachersId.add(121L);
        NEW_PROJECT_DTO.setTeachersId(teachersId);
        List<Long> areasId = new ArrayList<>();
        areasId.add(100L);
        areasId.add(101L);
        NEW_PROJECT_DTO.setAreasId(areasId);
        List<Long> subAreasId = new ArrayList<>();
        subAreasId.add(100L);
        subAreasId.add(101L);
        NEW_PROJECT_DTO.setSubAreasId(subAreasId);
    }
    @Autowired
    public ProjectServiceCreateTest(ProjectServiceCreate projectServiceCreate) {
        this.projectServiceCreate = projectServiceCreate;
    }

    @Test
    @Rollback
    void givenNewProjectDTOWhenCreateProjectThenServiceMessageOk() {
        assertEquals(ServiceMessage.SUCCESSFUL_PROJECT_REGISTER, projectServiceCreate.createProject(NEW_PROJECT_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenInvalidModalityIdWhenCreateProjectThenServiceMessageModalityDoesNotExist() {
        NEW_PROJECT_DTO.setModalityId(999L);
        assertEquals(ServiceMessage.MODALITY_DOES_NOT_EXIST, projectServiceCreate.createProject(NEW_PROJECT_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenInvalidStudentsIdsWhenCreateProjectThenServiceMessageInvalidProjectStudentsValue() {
        NEW_PROJECT_DTO.setStudentsId(List.of(999L));
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, projectServiceCreate.createProject(NEW_PROJECT_DTO).getServiceMessage());
    }
}
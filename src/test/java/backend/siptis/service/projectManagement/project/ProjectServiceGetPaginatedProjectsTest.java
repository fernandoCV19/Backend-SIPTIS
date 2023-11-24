package backend.siptis.service.projectManagement.project;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectServiceGetPaginatedProjectsTest {

    private ProjectServiceGetPaginatedProjects projectServiceGetPaginatedProjects;

    @Autowired
    public ProjectServiceGetPaginatedProjectsTest(ProjectServiceGetPaginatedProjects projectServiceGetPaginatedProjects) {
        this.projectServiceGetPaginatedProjects = projectServiceGetPaginatedProjects;
    }

    @Test
    void whenGetPaginatedCompletedProjectsThenServiceOk() {
        assertEquals(ServiceMessage.OK, projectServiceGetPaginatedProjects.getPaginatedCompletedProjects(0, 10).getServiceMessage());
    }

    @Test
    void whenGetPaginatedCompletedProjectsByNameThenServiceOk() {
        assertEquals(ServiceMessage.NO_PROJECTS, projectServiceGetPaginatedProjects.getPaginatedCompletedProjectsByName(0, 10, "Proyecto de Desarrollo de Aplicación Web").getServiceMessage());
    }

    @Test
    void whenGetPaginatedCompletedProjectsByModalityThenServiceOk() {
        assertEquals(ServiceMessage.OK, projectServiceGetPaginatedProjects.getPaginatedCompletedProjectsByModality(0, 10, "TESIS").getServiceMessage());
    }

    @Test
    void whenGetPaginatedCompletedProjectsByAreaThenServiceMessageOk() {
        assertEquals(ServiceMessage.NO_PROJECTS, projectServiceGetPaginatedProjects.getPaginatedCompletedProjectsByArea(0, 10, "INGENIRIA DE SOFTWARE").getServiceMessage());
    }

    @Test
    void whenGetPaginatedCompletedProjectsBySubAreaThen() {
        assertEquals(ServiceMessage.NO_PROJECTS, projectServiceGetPaginatedProjects.getPaginatedCompletedProjectsBySubArea(0, 10, "DISEÑO DE SOFTWARE").getServiceMessage());
    }
}
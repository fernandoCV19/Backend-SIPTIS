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
class ProjectServiceGetNumberOfProjectsTest {

    private ProjectServiceGetNumberOfProjects projectServiceGetNumberOfProjects;

    @Autowired
    public ProjectServiceGetNumberOfProjectsTest(ProjectServiceGetNumberOfProjects projectServiceGetNumberOfProjects) {
        this.projectServiceGetNumberOfProjects = projectServiceGetNumberOfProjects;
    }
    @Test
    void whenGetNumberOfProjectsByModalityAndCareerThenServiceMessageOk() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberOfProjectsByModalityAndCareer(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void whenGetNumberOfProjectsByAreaAndCareerThenServiceMessageOk() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberOfProjectsByAreaAndCareer(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void whenGetNumberOfProjectsBySubAreaAndCareerServiceMessageOk() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberOfProjectsBySubAreaAndCareer(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void whenGetNumberProjectsByPeriodAndCareerThenServiceMessageOk() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberProjectsByPeriodAndCareer(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }

    @Test
    void whenGetNumberProjectsByCareerThenServiceMessageOk() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberProjectsByCareer(100L).getServiceMessage();
        assertEquals(ServiceMessage.OK, message);
    }
}
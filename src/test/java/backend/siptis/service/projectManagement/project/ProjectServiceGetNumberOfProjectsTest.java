package backend.siptis.service.projectManagement.project;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.project_management.project.ProjectServiceGetNumberOfProjects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectServiceGetNumberOfProjectsTest {

    private final ProjectServiceGetNumberOfProjects projectServiceGetNumberOfProjects;

    @Autowired
    public ProjectServiceGetNumberOfProjectsTest(ProjectServiceGetNumberOfProjects projectServiceGetNumberOfProjects) {
        this.projectServiceGetNumberOfProjects = projectServiceGetNumberOfProjects;
    }

    @Test
    void whenGetNumberOfProjectsByModalityAndCareerThenServiceMessageOk() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberOfProjectsByModalityAndCareer("INFOR MATICA").getServiceMessage();
        assertEquals(ServiceMessage.ERROR, message);
    }

    @Test
    void givenWrongCareerNamewhenGetNumberOfProjectsByAreaAndCareerThenServiceMessageERROR() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberOfProjectsByAreaAndCareer("INFOR MATICA").getServiceMessage();
        assertEquals(ServiceMessage.ERROR, message);
    }

    @Test
    void givenWrongCareerNamewhenGetNumberOfProjectsBySubAreaAndCareerServiceMessageERROR() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberOfProjectsBySubAreaAndCareer("INFOR MATICA").getServiceMessage();
        assertEquals(ServiceMessage.ERROR, message);
    }

    @Test
    void givenWrongCareerNamewhenGetNumberProjectsByPeriodAndCareerThenServiceMessageERROR() {
        ServiceAnswer answer = projectServiceGetNumberOfProjects.getNumberProjectsByPeriodAndCareer("INFOR MATICA");
        assertEquals(ServiceMessage.ERROR, answer.getServiceMessage());
    }

    @Test
    void givenWrongCareerNamewhenGetNumberProjectsByCareerThenServiceMessageERROR() {
        ServiceMessage message = projectServiceGetNumberOfProjects.getNumberProjectsByCareer("INFOR MATICA").getServiceMessage();
        assertEquals(ServiceMessage.ERROR, message);
    }
}
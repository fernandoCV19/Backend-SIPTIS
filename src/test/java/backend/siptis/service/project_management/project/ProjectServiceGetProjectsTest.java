package backend.siptis.service.project_management.project;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectServiceGetProjectsTest {

    private final ProjectServiceGetProjects projectsServiceGetProjects;

    @Autowired
    public ProjectServiceGetProjectsTest(ProjectServiceGetProjects projectsServiceGetProjects) {
        this.projectsServiceGetProjects = projectsServiceGetProjects;
    }

    @Test
    void whenGetProjectsThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, projectsServiceGetProjects.getProjects().getServiceMessage());
    }


    @Test
    void givenAProjectNameAnsPageableObjectWhenGetProjectsListThenServiceMessageOk() {
        ServiceMessage sm = projectsServiceGetProjects.getProjectsList("Proyecto", Pageable.ofSize(3)).getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void givenValidTribunalIdWhenGetProjectsToDefenseOrDefendedThenSericeOk() {
        ServiceMessage sm = projectsServiceGetProjects.getProjectsToDefenseOrDefended(150L).getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void givenInvalidTribunalIdWhenGetProjectsToDefenseOrDefendedThenServiceMessageUserDoesNotExist() {
        ServiceMessage sm = projectsServiceGetProjects.getProjectsToDefenseOrDefended(1L).getServiceMessage();
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, sm);
    }

    @Test
    void whenGetProjectsWithoutAndWithTribunalsThenServiceMessageOk() {
        ServiceMessage sm = projectsServiceGetProjects.getProjectsWithoutAndWithTribunals().getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }

    @Test
    void whenGetProjectsWithoutAndWithDefensePlaceThenServiceMessageOk() {
        ServiceMessage sm = projectsServiceGetProjects.getProjectsWithoutAndWithDefensePlace().getServiceMessage();
        assertEquals(ServiceMessage.OK, sm);
    }
}
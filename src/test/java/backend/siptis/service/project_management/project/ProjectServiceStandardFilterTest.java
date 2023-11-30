package backend.siptis.service.project_management.project;

import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectServiceStandardFilterTest {

    private final ProjectServiceStandardFilter projectServiceStandardFilter;

    @Autowired
    public ProjectServiceStandardFilterTest(ProjectServiceStandardFilter projectServiceStandardFilter) {
        this.projectServiceStandardFilter = projectServiceStandardFilter;
    }

    @Test
    void givenValidFilterWhenGetProjectsWithStandardFilterThenServiceMessageOk() {
        ServiceMessage sm = projectServiceStandardFilter.getProjectsWithStandardFilter(0, 10, "Proyecto", "2021-1").getServiceMessage();
        assertEquals(ServiceMessage.NO_PROJECTS, sm);
    }
}
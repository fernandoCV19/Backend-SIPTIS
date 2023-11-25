package backend.siptis.service.projectManagement.project;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.project_management.project.ProjectServiceAdvancedFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectServiceAdvancedFilterTest {

    private final ProjectServiceAdvancedFilter projectServiceAdvancedFilter;

    @Autowired
    public ProjectServiceAdvancedFilterTest(ProjectServiceAdvancedFilter projectServiceAdvancedFilter) {
        this.projectServiceAdvancedFilter = projectServiceAdvancedFilter;
    }


    @Test
    void givenAllInvalidParametersWhenGetProjectsWithAdvancedFilterThenServiceMessageOk() {
        assertEquals(ServiceMessage.NO_PROJECTS, projectServiceAdvancedFilter.getProjectsWithAdvancedFilter(
                0, 10, "name", "period", "modality", "area",
                "subarea", "student", "tutor").getServiceMessage());
    }

    //TODO: Fix this test
    @Test
    void givenAllValidParametersWhenGetProjectsWithAdvancedFilterThenServiceMessageOk() {
        assertEquals(ServiceMessage.NO_PROJECTS, projectServiceAdvancedFilter.getProjectsWithAdvancedFilter(
                0, 10, "", "", "ADSCRIPCION", "",
                "", "", "").getServiceMessage());
    }
}
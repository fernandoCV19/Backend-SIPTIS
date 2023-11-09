package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ProjectServiceImplTest {
    @Autowired
    private ProjectService projectService;

    @Test
    void getProjectsReturnOk() {
        ServiceAnswer ans = projectService.getProjects();
        ServiceMessage serviceMessage = ans.getServiceMessage();
        assertEquals(ServiceMessage.OK, serviceMessage);
    }

    @Test
    void getProjectsReturnData() {
        ServiceAnswer ans = projectService.getProjects();
        Object data = ans.getData();
        assertNotNull(data);
    }


}

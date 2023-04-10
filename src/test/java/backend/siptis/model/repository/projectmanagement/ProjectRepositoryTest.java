package backend.siptis.model.repository.projectmanagement;

import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ProjectRepositoryTest {
    @Autowired
     private ProjectRepository projectRepository;

    @Test
    public void testfindByIdReturnNotNull(){
        Optional<Project> ans = projectRepository.findById(1L);
        assertNotNull(ans);
    }

    @Test
    public void testfindByIdReturnAnElement(){
        Optional<Project> ans = projectRepository.findById(1L);
        assertFalse(ans.isEmpty());
    }

    @Test
    public void testfindByIdReturnObjectActualId(){
        Optional<Project> ans = projectRepository.findById(1L);
        Project fAns = ans.get();
        assertEquals(fAns.getId(),1L);
    }


}

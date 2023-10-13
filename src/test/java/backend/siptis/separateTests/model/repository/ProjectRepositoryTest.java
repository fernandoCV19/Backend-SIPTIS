package backend.siptis.separateTests.model.repository;

import backend.siptis.model.repository.projectManagement.ProjectRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ProjectRepositoryTest {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectRepositoryTest(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    @Test
    void CheckIfExistNonExistingUserTest() {
        assertFalse(projectRepository.existsByName("example"));
    }

}

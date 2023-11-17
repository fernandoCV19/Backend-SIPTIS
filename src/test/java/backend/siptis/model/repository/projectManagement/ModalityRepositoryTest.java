package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Modality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class ModalityRepositoryTest {

    @Autowired
    private ModalityRepository modalityRepository;


    @Test
    @DisplayName("test")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Sql(scripts = {"/custom_imports/modalityTest.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void checkIfExist(){
        Modality modality = new Modality();
        modality.setName("TESIS");
        // modalityRepository.save(modality);
        assertTrue(modalityRepository.existsById(1l));
    }
}

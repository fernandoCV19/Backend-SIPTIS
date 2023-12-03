package backend.siptis.model.repository.project_management;

import backend.siptis.model.entity.project_management.Modality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ModalityRepositoryTest {

    @Autowired
    private ModalityRepository modalityRepository;
    private Modality modality;

    @BeforeEach
    void createPlaceToDefense() {
        modality = new Modality();
        modality.setName("Modality Name");
    }

    @Test
    @DisplayName("Test for find modality by id")
    void givenModalityId_whenFindById_thenNull() {
        assertTrue(modalityRepository.findById(1525054L).isEmpty());
    }

    @Test
    @DisplayName("Test for find modality by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenModalityId_whenFindById_thenModalityObject() {
        assertNotNull(modalityRepository.findById(101L));
    }


    @Test
    @DisplayName("Test for find modality object by id")
    void givenModalityId_whenFindModalityById_thenNull() {
        assertNull(modalityRepository.findModalityById(14564L));
    }

    @Test
    @DisplayName("Test for find modality object by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenModalityId_whenFindModalityById_thenModalityObject() {
        assertNotNull(modalityRepository.findModalityById(101L));
    }


}

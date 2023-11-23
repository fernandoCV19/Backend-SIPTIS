package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Modality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
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
    void givenModalityId_whenFindById_thenmodalityObject() {
        assertFalse(modalityRepository.findById(1L).isEmpty());
    }

    @Test
    @DisplayName("Test for find modality object by id")
    void givenModalityId_whenFindModalityById_thenModalityObject() {
        assertNotNull(modalityRepository.findModalityById(1L));
    }


}

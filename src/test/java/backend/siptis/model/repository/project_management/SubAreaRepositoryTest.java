package backend.siptis.model.repository.project_management;

import backend.siptis.model.entity.project_management.SubArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SubAreaRepositoryTest {

    @Autowired
    private SubAreaRepository subAreaRepository;
    private SubArea subArea;

    @BeforeEach
    void createPlaceToDefense() {
        subArea = new SubArea();
        subArea.setName("SUBAREA1");
    }

    @Test
    @DisplayName("Test for list of subareas")
    void givenSubAreas_whenFindAll_thenEmptySubAreasList() {
        assertTrue(subAreaRepository.findAll().isEmpty());
    }
    @Test
    @DisplayName("Test for list of subareas")
    void givenSubAreas_whenFindAll_thenSubAreasList() {
        subAreaRepository.save(subArea);
        assertFalse(subAreaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for exist subarea by id")
    void givenSubAreaId_existsSubAreaById_thenNULL() {
        assertFalse(subAreaRepository.existsSubAreaById(1234567L));
    }
    @Test
    @DisplayName("Test for exist subarea by id")
    void givenSubAreaId_existsSubAreaById_thenUserCareerObject() {
        subAreaRepository.save(subArea);
        assertTrue(subAreaRepository.existsSubAreaById(subArea.getId()));
    }

    @Test
    @DisplayName("Test for verify if exist subarea by name")
    void givenSubAreaName_ExistsBySubAreaName_thenFalse() {
        subAreaRepository.save(subArea);
        assertFalse(subAreaRepository.existsSubAreaByName("wrong name"));
    }
    @Test
    @DisplayName("Test for verify if exist subarea by name success")
    void givenSubAreaName_ExistsBySubAreaName_thenTrue() {
        subAreaRepository.save(subArea);
        assertTrue(subAreaRepository.existsSubAreaByName("SUBAREA1"));
    }

    @Test
    @DisplayName("Test for find subarea by name")
    void givenSubAreaId_ExistsById_thenUserCareerObject() {
        subAreaRepository.save(subArea);
        assertNotNull(subAreaRepository.findById(subArea.getId()).get());
    }

    @Test
    @DisplayName("Test for find subarea by non existing name")
    void givenSubAreaId_ExistsById_thenNull() {
        assertTrue(subAreaRepository.findById(12345667809L).isEmpty());
    }
}

package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.SubArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class SubAreaRepositoryTest {

    @Autowired
    private SubAreaRepository subAreaRepository;
    private SubArea subArea;

    @BeforeEach
    public void createPlaceToDefense(){
        subArea = new SubArea();
        subArea.setName("SUBAREA1");
    }

    @Test
    @DisplayName("Test for list of subareas")
    public void givenSubAreas_whenFindAll_thenSubAreasList(){
        subAreaRepository.save(subArea);
        assertFalse(subAreaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist subarea by name")
    public void givenSubAreaName_ExistsBySubAreaName_thenTrue(){
        subAreaRepository.save(subArea);
        assertTrue(subAreaRepository.existsSubAreaByName("SUBAREA1"));
    }
    @Test
    @DisplayName("Test for verify if exist subarea by non existing name")
    public void givenSubAreaName_ExistsBySubAreaName_thenFalse(){
        assertFalse(subAreaRepository.existsSubAreaByName("SUB AREA EXAMPLE"));
    }

    @Test
    @DisplayName("Test for find subarea by name")
    public void givenSubAreaId_ExistsById_thenUserCareerObject(){
        subAreaRepository.save(subArea);
        assertNotNull(subAreaRepository.findById(subArea.getId()).get());
    }
    @Test
    @DisplayName("Test for find subarea by non existing name")
    public void givenSubAreaId_ExistsById_thenNull(){
        assertTrue(subAreaRepository.findById(12345667809L).isEmpty());
    }
}

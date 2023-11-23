package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Area;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AreaRepositoryTest {

    @Autowired
    private AreaRepository areaRepository;
    private Area area;

    @BeforeEach
    void startArea() {
        area = new Area();
        area.setName("AREA_1");
    }

    @Test
    @DisplayName("Test for list of areas")
    void givenAreas_whenFindAll_thenAreasList() {
        areaRepository.save(area);
        assertFalse(areaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist area by id")
    void givenAreaId_ExistsByAreaId_thenFalse() {
        areaRepository.save(area);
        assertFalse(areaRepository.existsAreaById(123456L));
    }

    @Test
    @DisplayName("Test for verify if exist area by name")
    void givenAreaName_ExistsByAreaName_thenTrue() {
        areaRepository.save(area);
        assertTrue(areaRepository.existsAreaByName(area.getName()));
    }

    @Test
    @DisplayName("Test for verify if exist subarea by non existing name")
    void givenAreaName_ExistsByAreaName_thenFalse() {
        assertFalse(areaRepository.existsAreaByName("SUB AREA EXAMPLE"));
    }

    @Test
    @DisplayName("Test for find area by name")
    void givenSubAreaId_ExistsById_thenUserCareerObject() {
        areaRepository.save(area);
        assertNotNull(areaRepository.findById(area.getId()).get());
    }

    @Test
    @DisplayName("Test for find area by non existing name")
    void givenSubAreaId_ExistsById_thenNull() {
        assertTrue(areaRepository.findById(12345667809L).isEmpty());
    }

}

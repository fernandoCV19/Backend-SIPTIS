package backend.siptis.model.repository.project_management;

import backend.siptis.model.entity.project_management.Area;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AreaRepositoryTest {

    @Autowired
    private AreaRepository areaRepository;
    private Area area;

    void startArea() {
        area = new Area();
        area.setName("AREA_1");
    }

    @Test
    @DisplayName("Test for list of areas")
    void givenAreas_whenFindAll_thenEmptyAreasList() {
        assertTrue(areaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for list of areas success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenAreas_whenFindAll_thenAreasList() {
        assertFalse(areaRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Test for verify if exist area by id")
    void givenAreaId_ExistsByAreaId_thenFalse() {
        assertFalse(areaRepository.existsAreaById(123456L));
    }

    @Test
    @DisplayName("Test for verify if exist area by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenAreaId_ExistsByAreaId_thenTrue() {
        assertTrue(areaRepository.existsAreaById(101L));
    }

    @Test
    @DisplayName("Test for verify if exist area by name")
    void givenAreaName_ExistsByAreaName_thenFalse() {
        startArea();
        assertFalse(areaRepository.existsAreaByName(area.getName()));
    }

    @Test
    @DisplayName("Test for verify if exist area by name success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenAreaName_ExistsByAreaName_thenTrue() {
        startArea();
        areaRepository.save(area);
        assertTrue(areaRepository.existsAreaByName(area.getName()));
    }


    @Test
    @DisplayName("Test for find area by name")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSubAreaId_FindById_thenAreaObject() {
        assertNotNull(areaRepository.findById(101L).get());
    }

    @Test
    @DisplayName("Test for find area by non existing name")
    void givenSubAreaId_FindById_thenNull() {
        assertTrue(areaRepository.findById(12345667809L).isEmpty());
    }

}

package backend.siptis.model.repository.defense_management;

import backend.siptis.model.entity.defense_management.PlaceToDefense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PlaceToDefenseRepositoryTest {

    @Autowired
    private PlaceToDefenseRepository placeToDefenseRepository;
    private PlaceToDefense placeToDefense;

    @BeforeEach
    void createPlaceToDefense() {
        placeToDefense = new PlaceToDefense();
        placeToDefense.setName("place to defense test");
    }

    @Test
    @DisplayName("Test for find place to defense by id null")
    void givenPlaceToDefenseId_whenFindById_thenNull() {
        assertTrue(placeToDefenseRepository.findById(123123123L).isEmpty());
    }
    @Test
    @DisplayName("Test for find place to defense by id success")
    void givenPlaceToDefenseId_whenFindById_thenPlaceToDefenseObject() {
        placeToDefenseRepository.save(placeToDefense);
        assertFalse(placeToDefenseRepository.findById(placeToDefense.getId()).isEmpty());
    }
}

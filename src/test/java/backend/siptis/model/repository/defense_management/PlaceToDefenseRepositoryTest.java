package backend.siptis.model.repository.defense_management;

import backend.siptis.model.entity.defense_management.PlaceToDefense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
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
    @DisplayName("Test for find place to defense by id")
    void givenPlaceToDefenseId_whenFindById_thenPlaceToDefenseObject() {
        placeToDefenseRepository.save(placeToDefense);
        assertFalse(placeToDefenseRepository.findById(placeToDefense.getId()).isEmpty());
    }
}

package backend.siptis.model.repository.defenseManagement;
import backend.siptis.model.entity.defenseManagement.PlaceToDefense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class PlaceToDefenseRepositoryTest {

    @Autowired
    private PlaceToDefenseRepository placeToDefenseRepository;
    private PlaceToDefense placeToDefense;

    @BeforeEach
    public void createPlaceToDefense(){
        placeToDefense = new PlaceToDefense();
        placeToDefense.setName("place to defense test");
    }

    @Test
    @DisplayName("Test for find place to defense by id")
    public void givenPlaceToDefenseId_whenFindById_thenPlaceToDefenseObject(){
        placeToDefenseRepository.save(placeToDefense);
        assertFalse(placeToDefenseRepository.findById(placeToDefense.getId()).isEmpty());
    }
}

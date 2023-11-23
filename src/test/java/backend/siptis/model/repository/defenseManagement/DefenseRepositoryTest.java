package backend.siptis.model.repository.defenseManagement;

import backend.siptis.model.entity.defenseManagement.Defense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DefenseRepositoryTest {

    @Autowired
    private DefenseRepository defenseRepository;
    private Defense defense;

    @BeforeEach
    void createDefense(){
        defense = new Defense();
        defense.setSubstituteName("defense test");
    }

    @Test
    @DisplayName("Test for find defense by id")
    void givenDefenseId_whenFindById_thenDefenseObject(){
        defenseRepository.save(defense);
        assertFalse(defenseRepository.findById(defense.getId()).isEmpty());
    }
}

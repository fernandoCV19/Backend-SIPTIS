package backend.siptis.model.repository.defense_management;

import backend.siptis.model.entity.defense_management.Defense;
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
class DefenseRepositoryTest {

    @Autowired
    private DefenseRepository defenseRepository;
    private Defense defense;

    @BeforeEach
    void createDefense() {
        defense = new Defense();
        defense.setSubstituteName("defense test");
    }

    @Test
    @DisplayName("Test for find defense by id false")
    void givenDefenseId_whenFindById_thenEmpty() {
        assertTrue(defenseRepository.findById(123l).isEmpty());
    }
    @Test
    @DisplayName("Test for find defense by id success")
    void givenDefenseId_whenFindById_thenDefenseObject() {
        defenseRepository.save(defense);
        assertFalse(defenseRepository.findById(defense.getId()).isEmpty());
    }
}

package backend.siptis.service.defense;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.defenseManagement.PlaceToDefenseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PlaceToDefenseServiceImplTest {

    private final PlaceToDefenseService placeToDefenseService;

    @Autowired
    PlaceToDefenseServiceImplTest(PlaceToDefenseService placeToDefenseService) {
        this.placeToDefenseService = placeToDefenseService;
    }

    @Test
    @DisplayName("Test for get reserved dates")
    void whenGetReservedDatesThenServiceAnswerOk() {
        assertEquals(ServiceMessage.OK, placeToDefenseService.getReservedDates().getServiceMessage());
    }

    @Test
    @DisplayName("Test for get available places")
    void whenGetAvailablePlacesThenServiceAnswerOK() {
        assertEquals(ServiceMessage.OK, placeToDefenseService.getAvailablePlaces().getServiceMessage());
    }
}
package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.vo.projectManagement.PlaceToDefenseVO;
import backend.siptis.service.defenseManagement.PlaceToDefenseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class PlaceToDefenseServiceTest {

    private final PlaceToDefenseService placeToDefenseService;

    @Autowired
    PlaceToDefenseServiceTest(PlaceToDefenseService placeToDefenseService) {
        this.placeToDefenseService = placeToDefenseService;
    }

    @Test
    void getReservedDatesReturnMessageOk() {
        ServiceAnswer query = placeToDefenseService.getReservedDates();
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getReservedDatesReturnNotNullData() {
        ServiceAnswer query = placeToDefenseService.getReservedDates();
        assertNotNull(query.getData());
    }

    @Test
    void getReservedDatesReturnAListWithAllReservedPlaces() {
        ServiceAnswer query = placeToDefenseService.getReservedDates();
        HashMap<String, List<Date>> reservedDates = (HashMap<String, List<Date>>) query.getData();
        assertEquals(2, reservedDates.keySet().size());
    }


    @Test
    void getReservedDatesReturnAListThatNoContainsPlacesThatHasNotBeenReserved() {
        ServiceAnswer query = placeToDefenseService.getReservedDates();
        HashMap<String, List<Date>> reservedDates = (HashMap<String, List<Date>>) query.getData();
        assertFalse(reservedDates.containsKey("Aula 692B:Edificio nuevo"));
    }

    @Test
    void getAvailablePlacesReturnMessageOk() {
        ServiceAnswer query = placeToDefenseService.getAvailablePlaces();
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getAvailablePlacesReturnNotNullData() {
        ServiceAnswer query = placeToDefenseService.getAvailablePlaces();
        List<PlaceToDefenseVO> res = (List<PlaceToDefenseVO>) query.getData();
        assertNotNull(res);
    }

    @Test
    void getAvailablePlacesReturnAListWithAllAvailablePlaces() {
        ServiceAnswer query = placeToDefenseService.getAvailablePlaces();
        List<PlaceToDefenseVO> res = (List<PlaceToDefenseVO>) query.getData();
        assertEquals(3, res.size());
    }
}
package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.projectManagement.PlaceToDefenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/placesToDefense")
@RequiredArgsConstructor
@CrossOrigin
public class PlaceToDefenseController {

    private final PlaceToDefenseService placeToDefenseService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAvailablePlacesToDefense() {
        ServiceAnswer serviceAnswer = placeToDefenseService.getAvailablePlaces();
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, HttpStatus.OK);
    }

    @GetMapping("/reservedDates")
    public ResponseEntity<?> getReservedDates() {
        ServiceAnswer serviceAnswer = placeToDefenseService.getReservedDates();
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, HttpStatus.OK);
    }
}

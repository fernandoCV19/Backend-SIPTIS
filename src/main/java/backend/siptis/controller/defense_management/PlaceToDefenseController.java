package backend.siptis.controller.defense_management;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.defense_management.PlaceToDefenseService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = ControllerConstants.PlaceToDefense.TAG_NAME, description = ControllerConstants.PlaceToDefense.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.PlaceToDefense.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class PlaceToDefenseController {

    private final PlaceToDefenseService placeToDefenseService;

    @Operation(summary = "Get all available places to defense")
    @GetMapping("/all")
    public ResponseEntity<ControllerAnswer> getAllAvailablePlacesToDefense() {
        ServiceAnswer serviceAnswer = placeToDefenseService.getAvailablePlaces();
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, HttpStatus.OK);
    }

    @Operation(summary = "Get all reserved dates")
    @GetMapping("/reservedDates")
    public ResponseEntity<ControllerAnswer> getReservedDates() {
        ServiceAnswer serviceAnswer = placeToDefenseService.getReservedDates();
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, HttpStatus.OK);
    }
}

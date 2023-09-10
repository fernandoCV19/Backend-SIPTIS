package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.projectManagement.ParametersOfReservationsDTO;
import backend.siptis.service.projectManagement.DefenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/defense")
@CrossOrigin
public class DefenseController {

    @Autowired
    private DefenseService defenseService;

    @GetMapping("/reservations")
    public ResponseEntity<?> getPlaceReservationsByMonth(@RequestBody ParametersOfReservationsDTO params){
        ServiceAnswer serviceAnswer = defenseService.getPlaceReservationsAndDirectorByMonth(params.getPlaceId(), params.getMonth(), params.getDirectorId());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if(serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)){
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

}

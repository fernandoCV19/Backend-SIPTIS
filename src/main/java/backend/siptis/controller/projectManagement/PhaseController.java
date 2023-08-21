package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.projectManagement.PhaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/phase")
public class PhaseController {
    private final PhaseService phaseService;

    @GetMapping()
    public ResponseEntity<?> findAll(){

        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(phaseService.findAllPhases())
                .message("Phase found").build(), null, 200);
    }
    @GetMapping("/{idPhase}")
    public ResponseEntity<?> findById(@PathVariable int idPhase){
        Long id = Long.valueOf(idPhase);
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(phaseService.findPhaseById(id))
                .message("Phase found").build(), null, 200);
    }

    private ResponseEntity<?> createResponse(ServiceAnswer serviceAnswer){
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        if(serviceMessage == ServiceMessage.NOT_FOUND)
            return new ResponseEntity<>(
                    ControllerAnswer.builder()
                            .data(null)
                            .message("NOT FOUND").build(), null, 404);
        else if (serviceMessage == ServiceMessage.OK)
            return new ResponseEntity<>(
                    ControllerAnswer.builder()
                            .data(serviceAnswer.getData())
                            .message("OK").build(), null, 200);
        else return new ResponseEntity<>(
                    ControllerAnswer.builder()
                            .data(null)
                            .message("SERVER ERROR").build(), null, 500);
    }

}

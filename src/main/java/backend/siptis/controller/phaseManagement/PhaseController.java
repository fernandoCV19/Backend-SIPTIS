package backend.siptis.controller.phaseManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.service.phaseManagement.PhaseService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(ControllerConstants.Phase.BASE_PATH)
@CrossOrigin
public class PhaseController {
    private final PhaseService phaseService;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private final String phaseFound = "Phase found";

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(phaseService.findAllPhases())
                .message(phaseFound).build(), null, 200);
    }

    @GetMapping("/{idPhase}")
    public ResponseEntity<?> findById(@PathVariable int idPhase) {
        Long id = Long.valueOf(idPhase);

        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(phaseService.findPhaseByUserId(id))
                .message(phaseFound).build(), null, 200);
    }

    @GetMapping("/modality/{idModality}")
    public ResponseEntity<?> findByModalityId(@PathVariable int idModality) {
        Long id = Long.valueOf(idModality);

        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(phaseService.findPhaseByModalityId(id))
                .message(phaseFound).build(), null, 200);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getPhasesByUserId(@RequestHeader(name = "Authorization") String token) {
        Long idL = siptisUserServiceTokenOperations.getIdFromToken(token);
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(createResponse(phaseService.getPhasesByUserId(idL)))
                .message(phaseFound).build(), null, 200);

    }

    private ResponseEntity<?> createResponse(ServiceAnswer serviceAnswer) {
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        if (serviceMessage == ServiceMessage.NOT_FOUND)
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

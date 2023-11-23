package backend.siptis.controller.phase_management;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceTokenOperations;
import backend.siptis.service.phase_management.PhaseService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Phase.TAG_NAME, description = ControllerConstants.Phase.TAG_DESCRIPTION)
@RestController
@AllArgsConstructor
@RequestMapping(ControllerConstants.Phase.BASE_PATH)
@CrossOrigin
public class PhaseController {
    private static final String phaseFound = "Phase found";
    private final PhaseService phaseService;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;


    @Operation(summary = "Get all phases")
    @GetMapping("")
    public ResponseEntity<ControllerAnswer> findAll() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(phaseService.findAllPhases())
                .message(phaseFound).build(), null, 200);
    }

    @Operation(summary = "Get a phase by id")
    @GetMapping("/{idPhase}")
    public ResponseEntity<ControllerAnswer> findById(@PathVariable int idPhase) {
        Long id = Long.valueOf(idPhase);

        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(phaseService.findPhaseByUserId(id))
                .message(phaseFound).build(), null, 200);
    }

    @Operation(summary = "Get phases by modality id")
    @GetMapping("/modality/{idModality}")
    public ResponseEntity<ControllerAnswer> findByModalityId(@PathVariable int idModality) {
        Long id = Long.valueOf(idModality);

        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(phaseService.findPhaseByModalityId(id))
                .message(phaseFound).build(), null, 200);
    }

    @Operation(summary = "Get all phases by user token")
    @GetMapping("/user")
    public ResponseEntity<ControllerAnswer> getPhasesByUserId(@RequestHeader(name = "Authorization") String token) {
        Long idL = siptisUserServiceTokenOperations.getIdFromToken(token);
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(createResponse(phaseService.getPhasesByUserId(idL)))
                .message(phaseFound).build(), null, 200);

    }

    private ResponseEntity<ControllerAnswer> createResponse(ServiceAnswer serviceAnswer) {
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

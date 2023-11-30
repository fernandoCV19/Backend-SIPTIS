package backend.siptis.controller.defense_management;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.project_management.DefenseDTO;
import backend.siptis.service.defense_management.DefenseService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Defense.TAG_NAME, description = ControllerConstants.Defense.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Defense.BASE_PATH)
@CrossOrigin
@RequiredArgsConstructor
public class DefenseController {

    private final DefenseService defenseService;

    @Operation(summary = "Get all defenses by month")
    @GetMapping("/defensesByMonth/{month}")
    public ResponseEntity<ControllerAnswer> getPlaceReservationsByMonth(@PathVariable("month") Integer month) {
        ServiceAnswer serviceAnswer = defenseService.getDefenseByMonth(month);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Create a new defense")
    @PostMapping("/createDefense")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> createDefense(@RequestBody DefenseDTO defenseDTO) {
        ServiceAnswer serviceAnswer = defenseService.registerDefense(defenseDTO);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Remove a defense")
    @DeleteMapping("/removeDefense/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> removeDefenseFromAProject(@PathVariable("id") Long projectId) {
        ServiceAnswer serviceAnswer = defenseService.removeDefense(projectId);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}

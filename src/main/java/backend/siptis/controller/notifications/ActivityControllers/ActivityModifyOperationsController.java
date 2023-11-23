package backend.siptis.controller.notifications.ActivityControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.notifications.ActivityDTO;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.service.notifications.activityServices.ActivityServiceModifyOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Activity.TAG_NAME, description = ControllerConstants.Activity.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Activity.BASE_PATH)
@AllArgsConstructor
@CrossOrigin
public class ActivityModifyOperationsController {

    private final ActivityServiceModifyOperations activityServiceModifyOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;

    @Operation(summary = "Create activity")
    @PostMapping("/create")
    public ResponseEntity<ControllerAnswer> saveActivity(@RequestHeader(name = "Authorization") String token,
                                          @RequestBody ActivityDTO activityDTO) {

        Long idL = siptisUserServiceTokenOperations.getIdFromToken(token);
        Long idP = siptisUserServiceGeneralUserOperations.getProjectById(idL);
        activityDTO.setIdProject(idP);
        return createResponse(activityServiceModifyOperations.persistActivity(activityDTO));
    }

    @Operation(summary = "Update activity")
    @PutMapping("/{id}")
    public ResponseEntity<ControllerAnswer> updateActivity(@PathVariable int id, @RequestBody ActivityDTO activityDTO) {
        return createResponse(activityServiceModifyOperations.update(activityDTO, id));
    }
    @Operation(summary = "Delete activity")
    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerAnswer> deleteActivity(@PathVariable int id) {
        return createResponse(activityServiceModifyOperations.delete(id));
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

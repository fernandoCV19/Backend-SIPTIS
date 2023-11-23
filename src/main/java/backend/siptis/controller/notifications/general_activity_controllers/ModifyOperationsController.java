package backend.siptis.controller.notifications.general_activity_controllers;


import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.notifications.GeneralActivityDTO;
import backend.siptis.service.notifications.general_activity_services.GeneralActivityServiceModifyOperations;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Activity.TAG_NAME, description = ControllerConstants.GeneralActivity.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.GeneralActivity.BASE_PATH)
@CrossOrigin
@RequiredArgsConstructor
public class ModifyOperationsController {

    private final GeneralActivityServiceModifyOperations generalActivityServiceModifyOperations;

    @Operation(summary = "Create general activity")
    @PostMapping("/create")
    public ResponseEntity<ControllerAnswer> persistGeneralActivity(@RequestBody GeneralActivityDTO generalActivityDTO) {
        return createResponse(generalActivityServiceModifyOperations.persistGeneralActivity(generalActivityDTO));
    }

    @Operation(summary = "Update general activity")
    @PutMapping("/{id}")
    public ResponseEntity<ControllerAnswer> updateGeneralActivity(@RequestBody GeneralActivityDTO generalActivityDTO, @PathVariable long id) {
        return createResponse(generalActivityServiceModifyOperations.update(generalActivityDTO, id));
    }

    @Operation(summary = "Delete general activity")
    @DeleteMapping("/{id}")
    public ResponseEntity<ControllerAnswer> deleteGeneralActivity(@PathVariable long id) {
        return createResponse(generalActivityServiceModifyOperations.delete(id));
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

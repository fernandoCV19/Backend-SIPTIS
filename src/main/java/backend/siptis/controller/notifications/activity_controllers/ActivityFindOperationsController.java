package backend.siptis.controller.notifications.activity_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.service.notifications.activity_services.ActivityServiceFindOperations;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = ControllerConstants.Activity.TAG_NAME, description = ControllerConstants.Activity.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Activity.BASE_PATH)
@AllArgsConstructor
@CrossOrigin
public class ActivityFindOperationsController {

    private final ActivityServiceFindOperations activityServiceFindOperations;

    @Operation(summary = "Find all activities")
    @GetMapping()
    public ResponseEntity<ControllerAnswer> findAll() {
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(activityServiceFindOperations.findAllVO())
                        .message("Activity found").build(), null, 200);
    }
}

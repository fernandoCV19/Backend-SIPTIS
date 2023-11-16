package backend.siptis.controller.notifications.ActivityControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.service.notifications.activityServices.ActivityServiceFindOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConstants.Activity.BASE_PATH)
@AllArgsConstructor
@CrossOrigin
public class ActivityFindOperationsController {

    private final ActivityServiceFindOperations activityServiceFindOperations;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(activityServiceFindOperations.findAllVO())
                        .message("Activity found").build(), null, 200);
    }
}

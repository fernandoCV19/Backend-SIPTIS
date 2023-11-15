package backend.siptis.controller.notifications;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.notifications.ActivityDTO;
import backend.siptis.service.notifications.ActivityService;
import backend.siptis.service.auth.SiptisUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/activity")
@AllArgsConstructor
@CrossOrigin
public class ActivityController {
    private final ActivityService activityService;
    private final SiptisUserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> saveActivity(@RequestHeader(name = "Authorization") String token,
                                          @RequestBody ActivityDTO activityDTO) {

        Long idL = userService.getIdFromToken(token);
        Long idP = userService.getProjectById(idL);
        activityDTO.setIdProject(idP);
        return createResponse(activityService.persistActivity(activityDTO));
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(activityService.findAllVO())
                        .message("Activity found").build(), null, 200);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable int id, @RequestBody ActivityDTO activityDTO) {
        System.out.println(activityDTO);
        return createResponse(activityService.update(activityDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable int id) {
        return createResponse(activityService.delete(id));
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

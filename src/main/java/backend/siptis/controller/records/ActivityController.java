package backend.siptis.controller.records;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.records.ActivityDTO;
import backend.siptis.service.records.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;
    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }
    @PostMapping()
    public ResponseEntity<?> saveActivity(@RequestBody ActivityDTO activityDTO){
        return createResponse(activityService.persistActivity(activityDTO));
    }
    @GetMapping()
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(activityService.findAllVO())
                        .message("Activity found").build(), null, 200);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable int id, @RequestBody ActivityDTO activityDTO){
        return createResponse(activityService.update(activityDTO, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable int id){
        return createResponse(activityService.delete(id));
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

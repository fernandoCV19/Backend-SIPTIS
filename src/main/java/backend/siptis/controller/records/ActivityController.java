package backend.siptis.controller.records;

import backend.siptis.commons.ControllerAnswer;
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
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(activityService.persistActivity(activityDTO))
                        .message("Activity saved").build(), null, 200);

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
        activityService.update(activityDTO, id);
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .message("Activity updated").build(), null, 200);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable int id){
        activityService.delete(id);
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .message("Activity deleted").build(), null, 200);
    }
}

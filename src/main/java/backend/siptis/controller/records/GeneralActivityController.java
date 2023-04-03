package backend.siptis.controller.records;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.model.pjo.dto.records.GeneralActivityDTO;
import backend.siptis.service.records.GeneralActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/general-activity")
public class GeneralActivityController {
    private final GeneralActivityService generalActivityService;
    @Autowired
    public GeneralActivityController(GeneralActivityService generalActivityService) {
        this.generalActivityService = generalActivityService;
    }
    @PostMapping()
    public ResponseEntity<?> persistGeneralActivity(@RequestBody GeneralActivityDTO generalActivityDTO) {
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(generalActivityService.persistGeneralActivity(generalActivityDTO))
                        .message("General activity persisted successfully")
                        .build(), null, 200);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGeneralActivity(@RequestBody GeneralActivityDTO generalActivityDTO, long id) {
        generalActivityService.update(generalActivityDTO, id);
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .message("General activity updated successfully")
                        .build(), null, 200);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGeneralActivity(long id) {
        generalActivityService.delete(id);
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .message("General activity deleted successfully")
                        .build(), null, 200);
    }
    @GetMapping()
    public ResponseEntity<?> findAllGeneralActivity() {
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(generalActivityService.findAllVO())
                        .message("General activities found successfully")
                        .build(), null, 200);
    }
}

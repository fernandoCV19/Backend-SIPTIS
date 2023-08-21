package backend.siptis.controller.notifications;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.notifications.GeneralActivityDTO;
import backend.siptis.service.notifications.GeneralActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/general-activity")
@CrossOrigin
public class GeneralActivityController {
    private final GeneralActivityService generalActivityService;
    @Autowired
    public GeneralActivityController(GeneralActivityService generalActivityService) {
        this.generalActivityService = generalActivityService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> persistGeneralActivity(@RequestBody GeneralActivityDTO generalActivityDTO) {
        return createResponse(generalActivityService.persistGeneralActivity(generalActivityDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGeneralActivity(@RequestBody GeneralActivityDTO generalActivityDTO, @PathVariable long id) {
        return createResponse(generalActivityService.update(generalActivityDTO, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGeneralActivity(@PathVariable long id) {
        return createResponse(generalActivityService.delete(id));
    }

    @GetMapping()
    public ResponseEntity<?> findAllGeneralActivit(Pageable pageable) {
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(generalActivityService.findAllVO(pageable))
                        .message("General activities found successfully")
                        .build(), null, 200);
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

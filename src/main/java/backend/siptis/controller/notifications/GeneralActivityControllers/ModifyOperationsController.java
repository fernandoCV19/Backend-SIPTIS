package backend.siptis.controller.notifications.GeneralActivityControllers;


import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.notifications.GeneralActivityDTO;
import backend.siptis.service.notifications.generalActivityServices.GeneralActivityServiceModifyOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.GeneralActivity.BASE_PATH)
@CrossOrigin
@RequiredArgsConstructor
public class ModifyOperationsController {

    private final GeneralActivityServiceModifyOperations generalActivityServiceModifyOperations;

    @PostMapping("/create")
    public ResponseEntity<?> persistGeneralActivity(@RequestBody GeneralActivityDTO generalActivityDTO) {
        return createResponse(generalActivityServiceModifyOperations.persistGeneralActivity(generalActivityDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGeneralActivity(@RequestBody GeneralActivityDTO generalActivityDTO, @PathVariable long id) {
        return createResponse(generalActivityServiceModifyOperations.update(generalActivityDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGeneralActivity(@PathVariable long id) {
        return createResponse(generalActivityServiceModifyOperations.delete(id));
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

package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.projectManagement.ModalityService;
import backend.siptis.service.userData.SiptisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modality")
@CrossOrigin
public class ModalityController {

    @Autowired
    private ModalityService modalityService;
    private SiptisUserService userService;



    @GetMapping("")
    ResponseEntity<?> getAllModalities() {
        return createResponseEntity(modalityService.getAllModalities());
    }


    @GetMapping("/user")
    public ResponseEntity<?> getModalityByUser(@RequestHeader(name = "Authorization") String token){
        Long idL = userService.getIdFromToken(token);
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(createResponseEntity(modalityService.getModalityByUser(idL)))
                .build(), null, 200);
    }
    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage mensajeServicio = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (mensajeServicio == ServiceMessage.NOT_FOUND || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

}

package backend.siptis.controller.userData.GeneralInformation;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.userData.GeneralInformation.GeneralInformationService;
import backend.siptis.service.userData.userAuthentication.UserAuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siptis")
@AllArgsConstructor
@CrossOrigin
public class GeneralInformationController {

    @Autowired
    private final GeneralInformationService generalInformationService;

    @GetMapping("/getAllCareers")
    public ResponseEntity<?> getAllCareers(){
        ServiceAnswer answerService = generalInformationService.getAllCareers();
        return createResponse(answerService);
    }

    @GetMapping("/getAllAreas")
    public ResponseEntity<?> getAllAreas(){
        ServiceAnswer answerService = generalInformationService.getAllUserAreas();
        return createResponse(answerService);
    }

    private ResponseEntity<?> createResponse(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(messageService == ServiceMessage.OK){
            httpStatus = HttpStatus.OK;
        }
        if(messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
